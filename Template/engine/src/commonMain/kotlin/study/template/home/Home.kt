package study.template.home

import com.badoo.reaktive.subject.publish.PublishSubject
import study.template.AppDataName
import study.template.AppDataVersion
import study.template.arch.Arch

interface Home : Arch {

    sealed class Intention : Arch.Intention {
        object Load : Intention()
    }

    class Interactor : Arch.Interactor

    class Storage : Arch.Storage {
        val output = PublishSubject<State>()
    }

    data class State(
        val appDataName: AppDataName,
        val appDataVersion: AppDataVersion
    ) : Arch.State

    interface UseCase : Arch.UseCase

    interface Result : Arch.Result

    interface Reducer : Arch.Reducer

    interface Presenter : Arch.Presenter

    interface Model : Arch.Model

    interface View : Arch.View

    interface SideEffect : Arch.SideEffect

}
