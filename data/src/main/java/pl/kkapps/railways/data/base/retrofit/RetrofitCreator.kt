package pl.kkapps.railways.data.base.retrofit

import dagger.Lazy
import javax.inject.Inject
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Retrofit

internal class RetrofitCreator @Inject constructor(
    private val retrofitBuilder: Lazy<Retrofit.Builder>,
    private val provideJsonConverterFactory: ProvideJsonConverterFactory,
    private val okHttpClientCreator: OkHttpClientCreator,
) {

    @OptIn(ExperimentalSerializationApi::class)
    fun create(baseUrl: String): Retrofit =
        retrofitBuilder.get()
            .baseUrl(baseUrl)
            .client(okHttpClientCreator.create())
            .addConverterFactory(provideJsonConverterFactory())
            .build()
}