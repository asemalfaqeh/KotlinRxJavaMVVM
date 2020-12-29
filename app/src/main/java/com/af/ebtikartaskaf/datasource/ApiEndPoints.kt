package com.af.ebtikartaskaf.datasource

import com.af.ebtikartaskaf.model.PopularPersonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
/// Api End Points ////
interface ApiEndPoints {

    @GET("person/popular/")
    fun getAllPopularPersons(@Query("page") page: Int): Observable<PopularPersonResponse?>?

}