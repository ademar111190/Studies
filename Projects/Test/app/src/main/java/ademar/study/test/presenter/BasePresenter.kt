package ademar.study.test.presenter

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<View : LoadDataView> {

    protected var view: View? = null
    protected val subscriptions = CompositeDisposable()

    fun onAttachView(v: View?) {
        view = v
    }

    fun onDetachView() {
        view = null
        subscriptions.dispose()
    }

}
