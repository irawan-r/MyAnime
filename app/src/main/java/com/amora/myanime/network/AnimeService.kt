package com.amora.myanime.network

import com.amora.myanime.model.AnimeResponses
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeService {
	@GET("seasons/now")
	suspend fun getSeasonNow(
		@Query("page") page: Int? = 1
	): ApiResponse<AnimeResponses>
}