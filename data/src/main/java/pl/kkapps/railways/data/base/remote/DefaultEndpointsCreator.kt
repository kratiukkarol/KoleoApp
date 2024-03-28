package pl.kkapps.railways.data.base.remote

import javax.inject.Inject
import kotlin.reflect.KClass

internal class DefaultEndpointsCreator @Inject constructor(
    private val endpointsCreator: EndpointsCreator,
) {

    fun <E : Any> lazyCreate(
        endpoint: KClass<E>,
    ): Lazy<E> {
        return endpointsCreator.lazyCreate(
            baseUrl = KOLEO_API_URL,
            endpoint = endpoint
        )
    }

    fun <E : Any> create(endpoint: KClass<E>): E {
        return endpointsCreator.create(
            baseUrl = KOLEO_API_URL,
            endpoint = endpoint
        )
    }

    companion object {

        private const val KOLEO_API_URL = "https://koleo.pl/api/v2/main/"
    }
}