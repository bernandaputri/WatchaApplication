package com.putri.watchaapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

		@field:SerializedName("page")
		val page: Int,

		@field:SerializedName("results")
		val results: List<MovieResultsItem>

)

data class MovieResultsItem(
		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("title")
		val title: String,

		@field:SerializedName("poster_path")
		val posterPath: String,

		@field:SerializedName("vote_average")
		val voteAverage: Double,

		@field:SerializedName("overview")
		val overview: String,

		@field:SerializedName("release_date")
		val releaseDate: String

		)
