package com.putri.watchaapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

		@field:SerializedName("page")
		val page: Int,

		@field:SerializedName("results")
		val results: List<TvShowResultsItem>

)

data class TvShowResultsItem(

		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("poster_path")
		val posterPath: String,

		@field:SerializedName("vote_average")
		val voteAverage: Double,

		@field:SerializedName("overview")
		val overview: String,

		@field:SerializedName("first_air_date")
		val firstAirDate: String

		)
