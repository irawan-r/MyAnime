package com.amora.myanime.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amora.myanime.model.AnimeItem

@Dao
interface PosterDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertPosterList(poster: List<AnimeItem>)

	@Query("SELECT * FROM AnimeItem WHERE id = :id_")
	suspend fun getPoster(id_: Long): AnimeItem?

	@Query("SELECT * FROM AnimeItem")
	suspend fun getPosterList(): List<AnimeItem>
}