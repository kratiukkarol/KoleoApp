package pl.kkapps.railways.data.base.realm

import javax.inject.Inject

interface DatabaseOneTimeInitializer {

    fun init()
}

internal class DatabaseOneTimeInitializerImpl @Inject constructor(
    private val configurator: RealmConfigurator,
) : DatabaseOneTimeInitializer {

    override fun init() {
        if (isRealmInitialized.not()) {
            initRealm()
        }
    }

    private fun initRealm() {
        synchronized(DatabaseOneTimeInitializerImpl::class.java) {
            if (isRealmInitialized.not()) {
                configurator.init()
                isRealmInitialized = true
            }
        }
    }

    companion object {

        private var isRealmInitialized: Boolean = false
    }
}