package pl.kkapps.railways.ui.home

import com.airbnb.mvrx.withState
import kotlin.test.assertEquals
import org.junit.Test

class AppScreenViewModelTest_onClearSearch : AppScreenViewModelSetUp() {

    @Test
    fun setsNullKeywordsQueryInState() {
        val viewModel = createViewModel(keywordsQuery = "query")

        viewModel.onClearSearch()

        withState(viewModel) {
            assertEquals(null, it.keywordsQuery)
        }
    }
}