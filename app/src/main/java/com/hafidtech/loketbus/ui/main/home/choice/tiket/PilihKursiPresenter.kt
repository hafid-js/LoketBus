package com.hafidtech.loketbus.ui.main.home.choice.tiket

import com.bagicode.bagicodebaseutils.utils.Helpers.getErrorBodyMessage
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PilihKursiPresenter (private val view: PilihKursiContract.View): PilihKursiContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }
    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun getKursiList(id : String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.getKursiList(
            id
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.codeStatus.equals("200")) {
                        it.data?.let { it1 -> view.onKursiSuccess(it1) }
                    } else {
                        view.onKursiFailed(it.codeMessage.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onKursiFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable?.add(disposable)
        
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}