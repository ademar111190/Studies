package study.template.home

import com.badoo.reaktive.observable.*
import com.badoo.reaktive.subject.publish.PublishSubject
import study.template.AppData
import study.template.AppDataName
import study.template.AppDataVersion
import study.template.arch.Arch
import study.template.home.Home.Intention.Load
import study.template.home.Home.Result.Loading
import study.template.home.Home.Result.Success
import study.template.home.Home.State.Filled
import study.template.home.Home.State.Initial
import kotlin.jvm.Synchronized

interface Home : Arch {

    sealed class Intention : Arch.Intention {
        object Load : Intention()
    }

    class Interactor(
        view: View,
        private val loadAppData: LoadAppData
    ) : Arch.Interactor {

        val output = PublishSubject<Result>()

        init {
            view.output.flatMap(::handle).subscribe(onNext = output::onNext)
        }

        private fun handle(intention: Intention) = when (intention) {
            is Load -> loadAppData.invoke()
        }

    }

    object Storage : Arch.Storage {
        var state: State = Initial
            @Synchronized set
    }

    sealed class State : Arch.State {
        object Initial : State()
        data class Filled(
            val appDataName: AppDataName,
            val appDataVersion: AppDataVersion
        ) : State()
    }

    object LoadAppData : Arch.UseCase {
        operator fun invoke() = observable<Result> {
            it.onNext(Loading)
            val appDataName = AppData.name()
            val appDataVersion = AppData.version()
            it.onNext(Success(appDataName, appDataVersion))
            it.onComplete()
        }
    }

    sealed class Result : Arch.Result {
        object Loading : Result()
        data class Success(
            val appDataName: AppDataName,
            val appDataVersion: AppDataVersion
        ) : Result()
    }

    class Reducer(
        interactor: Interactor,
        private val storage: Storage
    ) : Arch.Reducer {

        val output = PublishSubject<State>()

        init {
            interactor.output.map(::handle).subscribe(onNext = output::onNext)
        }

        private fun handle(result: Result) = when (result) {
            is Loading -> when (val currentState = storage.state) {
                is Initial -> Initial
                is Filled -> currentState
            }
            is Success -> Filled(
                result.appDataName,
                result.appDataVersion
            )
        }

    }

    interface Presenter : Arch.Presenter

    interface Model : Arch.Model

    interface View : Arch.View {
        val output: Observable<Intention>
    }

    interface SideEffect : Arch.SideEffect

}
