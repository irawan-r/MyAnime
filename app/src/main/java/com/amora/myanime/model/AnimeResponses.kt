package com.amora.myanime.model

import com.google.gson.annotations.SerializedName

data class AnimeResponses(

	@field:SerializedName("pagination")
	val pagination: Pagination,

	@field:SerializedName("data")
	val data: List<AnimeItem>
)

data class Pagination(

	@field:SerializedName("has_next_page")
	val hasNextPage: Boolean? = null,

	@field:SerializedName("last_visible_page")
	val lastVisiblePage: Int? = null,

	@field:SerializedName("items")
	val items: Items? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class Items(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null
)