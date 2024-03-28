package pl.kkapps.railways.data.keywords

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import pl.kkapps.railways.data.base.utils.networkBound
import pl.kkapps.railways.data.keywords.local.KeywordsLocalRepository
import pl.kkapps.railways.data.keywords.mapper.KeywordsLocalDtoToEntityMapper
import pl.kkapps.railways.data.keywords.mapper.KeywordsRemoteResultToLocalDtoMapper
import pl.kkapps.railways.data.keywords.remote.KeywordsRemoteRepository
import pl.kkapps.railways.domain.data.StationKeywordsDao
import pl.kkapps.railways.domain.model.StationKeywords

internal class StationKeywordsDaoImpl @Inject constructor(
    private val keywordsRemoteRepository: KeywordsRemoteRepository,
    private val keywordsLocalRepository: KeywordsLocalRepository,
    private val keywordsRemoteResultToLocalDtoMapper: KeywordsRemoteResultToLocalDtoMapper,
    private val keywordsLocalDtoToEntityMapper: KeywordsLocalDtoToEntityMapper,
) : StationKeywordsDao {

    override fun getStationKeywords(): Flow<List<StationKeywords>> =
        networkBound(
            localQuery = {
                val stationsDto = keywordsLocalRepository.getKeywords()
                keywordsLocalDtoToEntityMapper.map(stationsDto)
            },
            remoteFetch = {
                keywordsRemoteRepository.getStationKeywords()
            },
            saveRemoteResult = { remoteResult ->
                val keywords = remoteResult.map { keywordsRemoteResultToLocalDtoMapper.map(it) }
                keywordsLocalRepository.saveKeywords(keywords)
            },
            onError = { }
        )
}