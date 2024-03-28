package pl.kkapps.railways.data.base.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter

internal class ProvideJsonConverterFactory @Inject constructor() {

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
    }

    @ExperimentalSerializationApi
    operator fun invoke(): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())
}