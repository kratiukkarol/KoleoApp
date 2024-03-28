package pl.kkapps.railways.data.base.remote

import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass
import pl.kkapps.railways.data.base.retrofit.RetrofitCreator

internal class RetrofitEndpointsCreator @Inject constructor(
    private val retrofitCreator: Provider<RetrofitCreator>,
) : EndpointsCreator {

    override fun <E : Any> lazyCreate(
        baseUrl: String,
        endpoint: KClass<E>,
    ) = lazy { create(baseUrl, endpoint) }

    override fun <E : Any> create(
        baseUrl: String,
        endpoint: KClass<E>,
    ): E {
        return retrofitCreator.get()
            .create(baseUrl)
            .create(endpoint.java)
    }
}