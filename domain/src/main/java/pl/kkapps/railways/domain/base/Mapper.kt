package pl.kkapps.railways.domain.base

interface Mapper<FROM, RESULT> {

    fun map(data: FROM): RESULT

    fun map(data: Iterable<FROM>): List<RESULT>
}