package pl.kkapps.railways.data.base.realm

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject
import javax.inject.Provider

internal class RealmConfigurator @Inject constructor(
    @ApplicationContext private val context: Context,
    private val configurationProvider: Provider<RealmConfiguration>,
) {

    fun init() {
        Realm.init(context)
        Realm.setDefaultConfiguration(
            configurationProvider.get()
        )
    }
}