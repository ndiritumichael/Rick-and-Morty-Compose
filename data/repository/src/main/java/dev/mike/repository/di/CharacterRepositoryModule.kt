package dev.mike.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mike.domain.repositories.CharacterDetailsRepository
import dev.mike.domain.repositories.CharactersRepository
import dev.mike.repository.datasources.CharacterDetailsRepositoryImpl
import dev.mike.repository.datasources.CharactersRepositoryImpl
import dev.mike.repository.datasources.episodes.EpisodesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class CharacterRepositoryModule {

    @Binds
    abstract fun providesCharacterRepository(impl: CharactersRepositoryImpl): CharactersRepository
    @Binds
    abstract fun providesCharacterDetailsRepository(impl: CharacterDetailsRepositoryImpl): CharacterDetailsRepository
}
