package com.hafidtech.loketbus.ui.main.mybooking

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyBookingPresenter (private val view: MyBookingContract.View): MyBookingContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }
    override fun subscribe() {

    }

    override fun getMyBookingList(idUser: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.getMyBookingList(
           idUser

        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onMyBookingSuccess(it1) }
                    } else {
                        view.onMyBookingFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onMyBookingFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable?.add(disposable)
        
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}