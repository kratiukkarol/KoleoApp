package pl.kkapps.railways.data.keywords.local

internal interface KeywordsLocalRepository {

    suspend fun saveKeywords(keywords: List<KeywordLocalDto>)

    suspend fun getKeywords(): List<KeywordLocalDto>
}