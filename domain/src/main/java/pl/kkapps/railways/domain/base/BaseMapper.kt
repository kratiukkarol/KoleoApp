package pl.kkapps.railways.domain.base

abstract class BaseMapper<FROM, RESULT> : Mapper<FROM, RESULT> {

    protected val tag: String = this::class.java.simpleName

    override fun map(data: Iterable<FROM>): List<RESULT> = data.map { map(it) }
}