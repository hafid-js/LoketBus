package com.hafidtech.loketbus.ui.auth.signin

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.hafidtech.loketbus.ui.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SigninPresenter (private val view: SigninContract.View): SigninContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }
    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun setSignin(email: String, pass: String) {
        val disposable = HttpClient.getInstance().getApi()!!.setLogin(
            email,
            pass
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onSigninSuccess(it1) }
                    } else {
                        view.onSigninFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onSigninFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable?.add(disposable)
        
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}