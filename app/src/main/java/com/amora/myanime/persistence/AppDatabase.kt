package com.amora.myanime.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amora.myanime.model.AnimeItem
import com.amora.myanime.model.ImagesTypeConverter

@Database(entities = [AnimeItem::class], version = 1, exportSchema = true)
@TypeConverters(ImagesTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
	abstract fun posterDao(): PosterDao
}