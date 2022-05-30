package dev.mike.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mike.domain.repositories.CharacterDetailsRepository
import dev.mike.domain.repositories.CharactersRepository
import dev.mike.network.ApiService
import dev.mike.repository.datasources.CharacterDetailsRepositoryImpl
import dev.mike.repository.datasources.CharactersRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterRepositoryModule {

    @Singleton
    @Provides
    fun providesCharacterRepository(apiService: ApiService): CharactersRepository = CharactersRepositoryImpl(apiService)




    @Singleton
    @Provides
    fun providesCharacterDetailsRepository(apiService: ApiService): CharacterDetailsRepository =
        CharacterDetailsRepositoryImpl(apiService)
}
