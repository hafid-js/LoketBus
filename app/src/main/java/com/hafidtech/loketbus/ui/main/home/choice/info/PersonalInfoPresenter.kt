package com.hafidtech.loketbus.ui.main.home.choice.info

import android.view.View
import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.request.CheckoutRequest
import com.hafidtech.loketbus.ui.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PersonalInfoPresenter (private val view: PersonalInfoContract.View): PersonalInfoContract.Presenter {

    private val mCompositeDispossable : CompositeDisposable?

    init {
        this.mCompositeDispossable = CompositeDisposable()
    }

    override fun setCheckoutBooking(checkoutRequest: CheckoutRequest, viewPars : View) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.setBooking(
            checkoutRequest
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onCheckoutBookingSuccess(it1.toString(), viewPars) }
                    } else {
                        view.onCheckoutFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onCheckoutFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDispossable?.add(disposable)
    }

    override fun setCheckoutUpdate(idTiket:String, statusPembayaran:String, viewPars : View) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()!!.setBookingUpdate(
            idTiket, statusPembayaran
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.codeStatus.equals("200")) {
                        view.onCheckoutUpdateSuccess(it.data.toString(), viewPars)
                    } else {
                        view.onCheckoutUpdateFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onCheckoutUpdateFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDispossable?.add(disposable)
    }

    override fun subscribe() { }

    override fun unSubscribe() {
        mCompositeDispossable!!.clear()
    }
}