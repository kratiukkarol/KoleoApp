package pl.kkapps.railways.data.base.retrofit.hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import pl.kkapps.railways.data.base.remote.EndpointsCreator
import pl.kkapps.railways.data.base.remote.RetrofitEndpointsCreator
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {

    @Binds
    fun bindEndpointsCreator(impl: RetrofitEndpointsCreator): EndpointsCreator

    companion object {

        @Provides
        fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

        @Provides
        fun provideOkHttpClientBuilder(okHttpClient: OkHttpClient): OkHttpClient.Builder =
            okHttpClient.newBuilder()
    }
}