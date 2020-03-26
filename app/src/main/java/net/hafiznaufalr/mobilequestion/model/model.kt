package net.hafiznaufalr.mobilequestion.model

import com.google.gson.annotations.SerializedName

class Model {
    data class MovieResponse(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<Movie>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
    )

    data class Movie(
        @SerializedName("id")
        val id: Int,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String
    )

    data class ReviewResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<Review>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
    )
    data class Review(
        @SerializedName("author")
        val author: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("url")
        val url: String
    )
}