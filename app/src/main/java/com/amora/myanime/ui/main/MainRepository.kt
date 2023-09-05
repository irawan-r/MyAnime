package com.amora.myanime.ui.main

import com.amora.myanime.model.AnimeItem
import com.amora.myanime.network.AnimeService
import com.amora.myanime.persistence.PosterDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val animeService: AnimeService,
    private val posterDao: PosterDao
) {

    init {
        Timber.d("Injection MainRepository")
    }

    fun loadAnimePosters(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val posters: List<AnimeItem> = posterDao.getPosterList()

        if (posters.isEmpty()) {
            animeService.getSeasonNow()
                .suspendOnSuccess {
                    posterDao.insertPosterList(data.data)
                    emit(data.data)
                }
                .onFailure { onError(message()) }
        } else {
            emit(posters)
        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}