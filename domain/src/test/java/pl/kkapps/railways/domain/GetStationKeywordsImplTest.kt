package pl.kkapps.railways.domain

import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pl.kkapps.railways.domain.data.StationKeywordsDao
import pl.kkapps.railways.domain.feature.keywords.GetStationKeywordsImpl
import pl.kkapps.railways.domain.model.StationKeywords

class GetStationKeywordsImplTest {

    private val keywords = List(10) { createStationKeywords(id = it) }
    private val stationKeywordsDao: StationKeywordsDao = mock()
    private val getStationKeywords = GetStationKeywordsImpl(stationKeywordsDao)

    private fun invoke() = getStationKeywords()

    private fun createStationKeywords(
        id: Int = 123,
        keyword: String = "Pruszków",
        stationId: Int = 987
    ) = StationKeywords(
        id = id,
        keyword = keyword,
        stationId = stationId
    )

    @Test
    fun invoke_returnsKeywordsFromDao() = runTest {
        mockKeywordsDaoEmits(keywords)
        invoke().assertValue(keywords)
    }

    private fun mockKeywordsDaoEmits(keywords: List<StationKeywords>) {
        whenever(stationKeywordsDao.getStationKeywords()) doReturn flowOf(keywords)
    }

    @Test
    fun invoke_returnsEmptyList_whenKeywordsDaoReturnsEmpty() = runTest {
        mockKeywordsDaoEmits(emptyList())
        invoke().assertValue(emptyList())
    }

    @Test
    fun invoke_returnsError_whenKeywordsDaoReturnsError() = runTest {
        mockKeywordsDaoEmitsError()
        assertFails { invoke().collect() }
    }

    private fun mockKeywordsDaoEmitsError(error: Throwable = Error()) = error.also {
        whenever(stationKeywordsDao.getStationKeywords()) doReturn flow { throw it }
    }
}

infix fun <T> T.shouldEqual(expected: T?) = this.run { assertEquals(expected, this) }

suspend inline fun <reified T> Flow<T>.assertValue(vararg value: T) =
    toList() shouldEqual value.toList()
