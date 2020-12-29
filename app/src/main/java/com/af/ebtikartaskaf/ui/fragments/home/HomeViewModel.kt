package com.af.ebtikartaskaf.ui.fragments.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.af.ebtikartaskaf.datasource.ApiEndPoints
import com.af.ebtikartaskaf.model.dto.PersonDto
import com.af.ebtikartaskaf.model.PopularPersonResponse
import com.af.ebtikartaskaf.model.PopularPersonResponse.Results
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class HomeViewModel @ViewModelInject constructor(private val apiEndPoints: ApiEndPoints) : ViewModel() {
   // RxJava2 //
    // TODO: Implement the ViewModel //
    private val disposable = CompositeDisposable()
    private val listMutableLiveData = MutableLiveData<List<PersonDto>>()

    val popularPersonsViewModel: LiveData<List<PersonDto>> get() {
            val personDtos = ArrayList<PersonDto>()
            val personObservable = apiEndPoints.getAllPopularPersons(1)
            personObservable?.subscribeOn(Schedulers.io())?.flatMap(Function {
                personResponse: PopularPersonResponse? ->
                Observable.fromArray(*personResponse!!.results!!.toTypedArray()) } as
                            Function<PopularPersonResponse?,
                            Observable<Results>>)?.
                    observeOn(AndroidSchedulers.mainThread())
                    ?.subscribeWith(object : DisposableObserver<Results?>() {

                        override fun onNext(popularPersonResponse: Results) {
                            val personDto = PersonDto()
                            personDto.personName = popularPersonResponse.name
                            personDto.personImagePath = popularPersonResponse.profile_path + ""
                            personDtos.add(personDto)
                        }

                        override fun onError(e: Throwable) {
                            Log.d(TAG, "onError: " + e.message)
                        }

                        override fun onComplete() {
                            listMutableLiveData.postValue(personDtos)
                        }

                    })?.let { disposable.add(it) }

            return listMutableLiveData
        }

    fun clearDisposables() {
        disposable.clear()
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}