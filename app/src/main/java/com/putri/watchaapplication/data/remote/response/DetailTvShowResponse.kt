package com.putri.watchaapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTvShowResponse(

		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("poster_path")
		val posterPath: String,

		@field:SerializedName("overview")
		val overview: String,

		@field:SerializedName("first_air_date")
		val firstAirDate: String,

		@field:SerializedName("genres")
		val genres: List<TvShowGenresItem>

)

data class TvShowGenresItem(

	@field:SerializedName("name")
	val name: String
)
