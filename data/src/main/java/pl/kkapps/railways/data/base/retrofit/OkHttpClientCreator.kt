package pl.kkapps.railways.data.base.retrofit

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Provider
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal open class OkHttpClientCreator @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpBuilder: Provider<OkHttpClient.Builder>,
) {

    private val cacheSize = 10L * 1024L * 1024L

    open fun create(): OkHttpClient = okHttpBuilder.get()
        .cache(Cache(context.cacheDir, cacheSize))
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()
}