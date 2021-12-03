package dev.mike.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mike.domain.repositories.CharacterDetailsRepository
import dev.mike.domain.repositories.CharactersRepository
import dev.mike.domain.usecases.GetCharacterDetailsUsecase
import dev.mike.domain.usecases.GetCharactersUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DomainModule {

    @Provides
    @Singleton
    fun providesCharactersUseCase(charactersRepository: CharactersRepository) = GetCharactersUseCase(charactersRepository)

    @Provides
    @Singleton
    fun providesCharacterDetailsUseCase(characterDetailsRepository: CharacterDetailsRepository) = GetCharacterDetailsUsecase(characterDetailsRepository)
}
