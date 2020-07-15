package com.example.rma_ispit.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rma_ispit.data.models.Resource
import com.example.rma_ispit.data.repositories.WeatherRepository
import com.example.rma_ispit.presentation.contract.MainContract
import com.example.rma_ispit.presentation.view.states.WeatherState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(
    private val weatherRepository : WeatherRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val weatherState : MutableLiveData<WeatherState> = MutableLiveData()


    override fun fetchAll(city: String, days: Int) {
        val subscription = weatherRepository
            .fetchAll(city, days)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it){
                        is Resource.Loading -> weatherState.value = WeatherState.Loading
                        is Resource.Success -> weatherState.value = WeatherState.DataFetched
                        is Resource.Error -> weatherState.value = WeatherState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    weatherState.value = WeatherState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAll() {
        val subscription = weatherRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    weatherState.value = WeatherState.Success(it)
                },
                {
                    weatherState.value = WeatherState.Error("Error getting from DB")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }


}