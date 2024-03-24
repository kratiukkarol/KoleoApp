package pl.kkapps.railways.ui

import com.airbnb.mvrx.MavericksState

data class AppScreenState(
    val isEmpty: Boolean = true
) : MavericksState