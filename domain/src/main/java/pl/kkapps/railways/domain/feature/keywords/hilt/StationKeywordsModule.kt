package pl.kkapps.railways.domain.feature.keywords.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.kkapps.railways.domain.feature.keywords.GetStationKeywords
import pl.kkapps.railways.domain.feature.keywords.GetStationKeywordsImpl

@Module
@InstallIn(SingletonComponent::class)
interface StationKeywordsModule {

    @Binds
    fun bindGetStationKeywords(impl: GetStationKeywordsImpl): GetStationKeywords
}