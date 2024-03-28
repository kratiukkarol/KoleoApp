package pl.kkapps.railways.data.base.realm

import io.realm.Realm
import javax.inject.Inject

interface DbInstanceCreator<T> {

    fun create(): T
}

internal class RealmInstanceCreator @Inject constructor(
    private val oneTimeInitializer: DatabaseOneTimeInitializer,
) : DbInstanceCreator<Realm> {

    override fun create(): Realm {
        oneTimeInitializer.init()
        return Realm.getDefaultInstance()
    }
}