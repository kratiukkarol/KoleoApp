package pl.kkapps.railways.data.base.realm.access

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmQuery
import javax.inject.Inject

internal class RealmOperations @Inject constructor(
    private val realmAccessor: RealmAccessor,
) {

    fun <T : RealmModel> readAll(query: Realm.() -> RealmQuery<T>): List<T> =
        realmAccessor.access {
            val results = query().findAll()
            copyFromRealm(results)
        }

    fun executeTransaction(action: Realm.() -> Unit) {
        realmAccessor.access {
            executeTransaction { realm ->
                realm.action()
            }
        }
    }
}