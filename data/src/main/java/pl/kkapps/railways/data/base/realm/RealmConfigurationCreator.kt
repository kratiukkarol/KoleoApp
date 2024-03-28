package pl.kkapps.railways.data.base.realm

import io.realm.RealmConfiguration
import javax.inject.Inject

internal class RealmConfigurationCreator @Inject constructor() {

    private val databaseName = "railways.realm"

    fun create(): RealmConfiguration =
        RealmConfiguration.Builder()
            .name(databaseName)
            .deleteRealmIfMigrationNeeded()
            .compactOnLaunch()
            .build()
}