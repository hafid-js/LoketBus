package com.hafidtech.loketbus.ui.auth.signup

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.hafidtech.loketbus.ui.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignupPresenter (private val view: SignupContract.View): SignupContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }
    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun setSignup(email: String, pass: String, username: String) {
        val disposable = HttpClient.getInstance().getApi().setRegister(
            email,
            pass,
            username
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.codeStatus.equals("200")) {
                        view.onSignupSuccess(it.codeMessage.toString())
                    } else {
                        view.onSignupFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onSignupFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable?.add(disposable)
        
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}