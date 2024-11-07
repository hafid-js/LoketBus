package com.hafidtech.loketbus.ui.main.home.choice.bus

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PilihBusPresenter (private val view: PilihBusContract.View): PilihBusContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }
    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun getBusList(busRequest: BusRequest) {
        val disposable = HttpClient.getInstance().getApi()!!.getBusList(
            busRequest.tipe,
            busRequest.penumpang.toString(),
            busRequest.date,
            busRequest.dari,
            busRequest.tujuan

        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onBusSuccess(it1) }
                    } else {
                        view.onBusFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onBusFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable?.add(disposable)
        
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}