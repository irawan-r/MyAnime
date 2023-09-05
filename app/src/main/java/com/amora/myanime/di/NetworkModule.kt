package com.amora.myanime.di

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.amora.myanime.network.AnimeService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://api.jikan.moe/v4/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideOkHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(RequestInterceptor())
			.build()
	}

	@Provides
	@Singleton
	fun provideImageLoader(
		@ApplicationContext context: Context,
		okHttpClient: OkHttpClient
	): ImageLoader {
		return ImageLoader.Builder(context)
			.okHttpClient { okHttpClient }
			.components {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
					add(ImageDecoderDecoder.Factory())
				} else {
					add(GifDecoder.Factory())
				}
			}
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.client(okHttpClient)
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
			.build()
	}

	@Provides
	@Singleton
	fun provideAnimeService(retrofit: Retrofit): AnimeService {
		return retrofit.create(AnimeService::class.java)
	}
}