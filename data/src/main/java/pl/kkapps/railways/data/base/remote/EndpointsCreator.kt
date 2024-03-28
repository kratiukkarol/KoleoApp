package pl.kkapps.railways.data.base.remote

import kotlin.reflect.KClass

internal interface EndpointsCreator {

    fun <E : Any> lazyCreate(
        baseUrl: String,
        endpoint: KClass<E>,
    ): Lazy<E>

    fun <E : Any> create(
        baseUrl: String,
        endpoint: KClass<E>,
    ): E
}