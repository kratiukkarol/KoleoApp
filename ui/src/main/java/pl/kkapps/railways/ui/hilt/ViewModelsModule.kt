package pl.kkapps.railways.ui.hilt

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap
import pl.kkapps.railways.ui.AppScreenViewModel

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AppScreenViewModel::class)
    fun appScreenViewModelFactory(
        factory: AppScreenViewModel.Factory,
    ): AssistedViewModelFactory<*, *>
}