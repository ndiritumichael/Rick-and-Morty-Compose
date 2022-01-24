package dev.mike.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mike.domain.repositories.episodes.SingleEpisodeRepository
import dev.mike.network.ApiService
import dev.mike.repository.datasources.episodes.EpisodesRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object EpisodesRepositoryModule {

/*    @Singleton
    @Provides
    fun providesAllEpisodes(apiService: ApiService) = EpisodesRepositoryImpl(apiService)*/



    @Singleton
    @Provides
    fun providesEpisodeList(apiService: ApiService) :SingleEpisodeRepository = EpisodesRepositoryImpl(apiService)


}