package com.af.ebtikartaskaf.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PopularPersonResponse {
    @Expose
    @SerializedName("total_results")
    var total_results = 0

    @Expose
    @SerializedName("total_pages")
    var total_pages = 0

    @Expose
    @SerializedName("results")
    var results: List<Results>? = null

    @Expose
    @SerializedName("page")
    var page = 0

    class Results {
        @Expose
        @SerializedName("profile_path")
        var profile_path: String? = null

        @Expose
        @SerializedName("popularity")
        var popularity = 0.0

        @Expose
        @SerializedName("name")
        var name: String? = null

        @Expose
        @SerializedName("known_for_department")
        var known_for_department: String? = null

        @Expose
        @SerializedName("known_for")
        var known_for: List<Known_for>? = null

        @Expose
        @SerializedName("id")
        var id = 0

        @Expose
        @SerializedName("gender")
        var gender = 0

        @Expose
        @SerializedName("adult")
        var adult = false

    }

    class Known_for {
        @Expose
        @SerializedName("vote_count")
        var vote_count = 0

        @Expose
        @SerializedName("vote_average")
        var vote_average = 0.0

        @Expose
        @SerializedName("video")
        var video = false

        @Expose
        @SerializedName("title")
        var title: String? = null

        @Expose
        @SerializedName("release_date")
        var release_date: String? = null

        @Expose
        @SerializedName("poster_path")
        var poster_path: String? = null

        @Expose
        @SerializedName("overview")
        var overview: String? = null

        @Expose
        @SerializedName("original_title")
        var original_title: String? = null

        @Expose
        @SerializedName("original_language")
        var original_language: String? = null

        @Expose
        @SerializedName("media_type")
        var media_type: String? = null

        @Expose
        @SerializedName("id")
        var id = 0

        @Expose
        @SerializedName("genre_ids")
        var genre_ids: List<Int>? = null

        @Expose
        @SerializedName("backdrop_path")
        var backdrop_path: String? = null

        @Expose
        @SerializedName("adult")
        var adult = false

    }
}