package pl.kkapps.railways.data.keywords.local

import javax.inject.Inject
import pl.kkapps.railways.data.base.realm.access.RealmOperations

internal class KeywordsLocalRepositoryImpl @Inject constructor(
    private val realmOperations: RealmOperations,
) : KeywordsLocalRepository {

    override suspend fun saveKeywords(keywords: List<KeywordLocalDto>) =
        realmOperations.executeTransaction {
            keywords.forEach { insertOrUpdate(it) }
        }

    override suspend fun getKeywords(): List<KeywordLocalDto> = realmOperations.readAll {
        where(KeywordLocalDto::class.java)
    }
}