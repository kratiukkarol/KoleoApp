package pl.kkapps.railways.ui

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AppScreenViewModel @AssistedInject constructor(
    @Assisted state: AppScreenState,
) : MavericksViewModel<AppScreenState>(state) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<AppScreenViewModel, AppScreenState>

    companion object : MavericksViewModelFactory<AppScreenViewModel, AppScreenState>
    by hiltMavericksViewModelFactory()
}