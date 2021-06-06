package com.putri.watchaapplication.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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

		@field:SerializedName("vote_average")
		val voteAverage: Double

) : Parcelable
