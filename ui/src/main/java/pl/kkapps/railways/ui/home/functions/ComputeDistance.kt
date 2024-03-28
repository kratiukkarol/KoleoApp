package pl.kkapps.railways.ui.home.functions

import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt
import pl.kkapps.railways.ui.home.AppScreenState

const val earthRadiusKm: Double = 6372.8

fun AppScreenState.Station.haversine(destination: AppScreenState.Station): Int {
    val dLat = Math.toRadians(destination.latitude.toDouble() - latitude.toDouble())
    val dLon = Math.toRadians(destination.longitude.toDouble() - longitude.toDouble())
    val originLat = Math.toRadians(latitude.toDouble())
    val destinationLat = Math.toRadians(destination.latitude.toDouble())

    val a = sin(dLat / 2).pow(2.toDouble()) +
        sin(dLon / 2).pow(2.toDouble()) * cos(originLat) * cos(destinationLat)
    val c = 2 * asin(sqrt(a))
    return (earthRadiusKm * c).roundToInt()
}