package com.amora.myanime.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class ImagesTypeConverter {

	private val gson = Gson()

	@TypeConverter
	fun fromImages(images: Images): String {
		return gson.toJson(images.webp?.imageUrl)
	}

	@TypeConverter
	fun toImages(imagesString: String): Images {
		val imageUrl = gson.fromJson(imagesString, String::class.java)
		return Images(Webp(imageUrl))
	}
}
