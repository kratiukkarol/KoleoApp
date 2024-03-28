package pl.kkapps.railways.domain.base

object MappingRequirements {

    fun <T> T?.requireNotNull(message: () -> String = { "" }): T =
        this ?: throw MappingException(message())
}