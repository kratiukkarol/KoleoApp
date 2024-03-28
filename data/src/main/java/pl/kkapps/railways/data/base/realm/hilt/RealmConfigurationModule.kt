package pl.kkapps.railways.data.base.realm.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.RealmConfiguration
import pl.kkapps.railways.data.base.realm.RealmConfigurationCreator

@Module
@InstallIn(SingletonComponent::class)
abstract class RealmConfigurationModule {

    companion object {

        @Provides
        internal fun provideRealmConfiguration(
            configurationCreator: RealmConfigurationCreator,
        ): RealmConfiguration = configurationCreator.create()
    }
}