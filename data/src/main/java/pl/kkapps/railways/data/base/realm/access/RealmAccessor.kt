package pl.kkapps.railways.data.base.realm.access

import io.realm.Realm
import javax.inject.Inject
import pl.kkapps.railways.data.base.realm.RealmInstanceCreator

internal class RealmAccessor @Inject constructor(
    private val instanceCreator: RealmInstanceCreator,
) {

    fun <T> access(action: Realm.() -> T): T =
        instanceCreator.create().use { realm ->
            realm.action()
        }
}