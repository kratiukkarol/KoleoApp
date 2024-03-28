package pl.kkapps.railways.data.keywords.local

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class KeywordLocalDto (
    @PrimaryKey
    var id: String = "",
    var keyword: String? = null,
    var stationId: Int? = null
) : RealmObject() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is KeywordLocalDto) return false

        if (id != other.id) return false
        if (keyword != other.keyword) return false
        if (stationId != other.stationId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (keyword?.hashCode() ?: 0)
        result = 31 * result + (stationId ?: 0)
        return result
    }

    override fun toString(): String =
        "KeywordLocalDto(id='$id', keyword=$keyword, stationId=$stationId)"
}