package dev.mike.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mike.domain.repositories.episodes.SingleEpisodeRepository
import dev.mike.repository.datasources.episodes.EpisodesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class EpisodesRepositoryModule {

/*    @Singleton
    @Provides
    fun providesAllEpisodes(apiService: ApiService) = EpisodesRepositoryImpl(apiService)*/

    @Binds
    abstract fun providesEpisodeList(impl: EpisodesRepositoryImpl): SingleEpisodeRepository
}
