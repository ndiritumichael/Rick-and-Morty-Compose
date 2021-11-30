package dev.mike.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mike.domain.repositories.CharactersRepository
import dev.mike.network.ApiService
import dev.mike.repository.datasources.CharactersRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesCharacterRepository(apiService: ApiService): CharactersRepository = CharactersRepositoryImpl(apiService)
}
