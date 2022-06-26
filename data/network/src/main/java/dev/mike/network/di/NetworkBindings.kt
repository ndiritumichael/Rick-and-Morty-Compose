package dev.mike.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mike.network.data.RemoteCharactersRepositoryImpl
import dev.mike.network.data.RemoteEpisodesImpl
import dev.mike.network.source.IEpisodesRepository
import dev.mike.network.source.IRemoteCharactersRepository

@Module

@InstallIn(SingletonComponent::class)
abstract class NetworkBindings {

    @Binds
    abstract fun bindCharacters(impl: RemoteCharactersRepositoryImpl): IRemoteCharactersRepository

    @Binds
    abstract fun bindEpisodes(impl: RemoteEpisodesImpl): IEpisodesRepository
}
