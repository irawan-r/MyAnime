package com.amora.myanime.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity
@TypeConverters(ImagesTypeConverter::class)
@Immutable
data class AnimeItem(

	@field:SerializedName("mal_id")
	@PrimaryKey val id: Long,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("year")
	val release: String,

	@field:SerializedName("duration")
	val duration: String,

	@field:SerializedName("synopsis")
	val synopsis: String,

	@field:SerializedName("images")
	val posterUrl: Images?,
) {
	companion object {
		fun mock() = AnimeItem(
			id = 0,
			title = "Demon Slayer",
			release = "2023",
			duration = "26 min/eps",
			synopsis = "Swordsmith Village Arc of Kimetsu no Yaiba.",
			posterUrl = Images(Webp("https://cdn.myanimelist.net/images/anime/1765/135099l.webp"))
		)
	}
}

data class Webp(
	@field:SerializedName("large_image_url")
	val imageUrl: String? = null
)

data class Images(
	@field:SerializedName("webp")
	val webp: Webp? = null,
)
