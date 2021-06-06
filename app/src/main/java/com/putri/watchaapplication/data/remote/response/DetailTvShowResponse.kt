package com.putri.watchaapplication.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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

		@field:SerializedName("vote_average")
		val voteAverage: Double

) : Parcelable
