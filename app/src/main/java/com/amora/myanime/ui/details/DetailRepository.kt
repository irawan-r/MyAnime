package com.amora.myanime.ui.details

import androidx.annotation.WorkerThread
import com.amora.myanime.persistence.PosterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
	private val posterDao: PosterDao
) {
	@WorkerThread
	fun getPosterById(id: Long) = flow {
		val poster = posterDao.getPoster(id)
		emit(poster)
	}.flowOn(Dispatchers.IO)
}