package com.putri.watchaapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(

		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("title")
		val title: String,

		@field:SerializedName("poster_path")
		val posterPath: String,

		@field:SerializedName("overview")
		val overview: String,

		@field:SerializedName("release_date")
		val releaseDate: String,

		@field:SerializedName("genres")
		val genres: List<MovieGenresItem>
)

data class MovieGenresItem(

	@field:SerializedName("name")
	val name: String
)
