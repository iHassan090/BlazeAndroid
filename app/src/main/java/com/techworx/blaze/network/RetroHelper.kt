package com.techworx.blaze.network

import androidx.lifecycle.MutableLiveData
import com.techworx.blaze.helpers.Constants
import com.techworx.blaze.models.KeyValue
import com.techworx.blaze.network.EndPoints.SEND_OTP
import com.techworx.blaze.network.data.LoginData
import com.techworx.blaze.network.response.Response
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RetroHelper {
    var mInstance: RetroHelper? = null
    var retroInstance: RetroService? = null

    fun getInstance(): RetroHelper {
        if (mInstance == null)
            mInstance = RetroHelper()
        return mInstance!!
    }

    init {
        retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    fun sendApiCall(
        endPoint: String,
        payload: Any,
        progressObserver: MutableLiveData<KeyValue>,
        responseObserver: MutableLiveData<KeyValue>,
        errorObserver: MutableLiveData<KeyValue>
    ) {
        getMethod(endPoint, payload)!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                getResponseObserverRx(
                    progressObserver,
                    responseObserver,
                    errorObserver,
                    endPoint,
                    payload
                )
            )
    }

    private fun getResponseObserverRx(
        progressObserver: MutableLiveData<KeyValue>,
        responseObserver: MutableLiveData<KeyValue>,
        errorObserver: MutableLiveData<KeyValue>,
        endPoint: String,
        payload: Any
    ): Observer<Response> {
        return object : Observer<Response> {
            override fun onNext(t: Response) {
                println("Request Response: $endPoint --> ${Constants.convertObjectToJSON(t)}")
                if (t.returnType!!.equals("success", ignoreCase = true))
                    responseObserver.postValue(
                        KeyValue(
                            endPoint,
                            ResponseParser.parseResponse(endPoint, t)
                        )
                    )
                else
                    errorObserver.postValue(KeyValue(endPoint, t.returnMessage!!))
            }

            override fun onError(e: Throwable) {
                println("Request Error: $endPoint --> ${e.message}")
                errorObserver.postValue(KeyValue(endPoint, e.message!!))
            }

            override fun onComplete() {
                progressObserver.postValue(KeyValue(endPoint, false))
            }

            override fun onSubscribe(d: Disposable) {
                progressObserver.postValue(KeyValue(endPoint, true))
                println("Request URL: ${RetroInstance.baseURL}/$endPoint")
                println("Request Payload: $endPoint --> ${Constants.convertObjectToJSON(payload)}")
            }
        }
    }


    private fun getMethod(endPoint: String, payload: Any): Observable<Response>? {
        return when (endPoint) {
            SEND_OTP -> retroInstance!!.login(payload as LoginData)
            else -> null
        }
    }
}