package pl.kkapps.railways.data.base.realm.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import pl.kkapps.railways.data.base.realm.DatabaseOneTimeInitializer
import pl.kkapps.railways.data.base.realm.DatabaseOneTimeInitializerImpl
import pl.kkapps.railways.data.base.realm.DbInstanceCreator
import pl.kkapps.railways.data.base.realm.RealmInstanceCreator

@Module
@InstallIn(SingletonComponent::class)
internal interface RealmModule {

    @Binds
    fun bindRealmOneTimeInitializer(impl: DatabaseOneTimeInitializerImpl): DatabaseOneTimeInitializer

    @Binds
    fun bindDbInstanceCreator(impl: RealmInstanceCreator): DbInstanceCreator<Realm>
}