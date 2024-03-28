package pl.kkapps.railways.data.keywords.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.kkapps.railways.data.keywords.StationKeywordsDaoImpl
import pl.kkapps.railways.data.keywords.local.KeywordsLocalRepository
import pl.kkapps.railways.data.keywords.local.KeywordsLocalRepositoryImpl
import pl.kkapps.railways.data.keywords.remote.KeywordsRemoteRepository
import pl.kkapps.railways.data.keywords.remote.KeywordsRemoteRepositoryImpl
import pl.kkapps.railways.domain.data.StationKeywordsDao

@Module
@InstallIn(SingletonComponent::class)
internal interface StationKeywordsModule {

    @Binds
    fun bindStationKeywordDao(impl: StationKeywordsDaoImpl): StationKeywordsDao

    @Binds
    fun bindKeywordsRemoteRepository(impl: KeywordsRemoteRepositoryImpl): KeywordsRemoteRepository

    @Binds
    fun bindKeywordsLocalRepository(impl: KeywordsLocalRepositoryImpl): KeywordsLocalRepository
}