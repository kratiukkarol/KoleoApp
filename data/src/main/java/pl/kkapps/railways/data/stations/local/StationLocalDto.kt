package pl.kkapps.railways.data.stations.local

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class StationLocalDto(
    @PrimaryKey
    var id: String = "",
    var name: String? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    var hits: Int? = null,
) : RealmObject() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StationLocalDto) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (hits != other.hits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (latitude?.hashCode() ?: 0)
        result = 31 * result + (longitude?.hashCode() ?: 0)
        result = 31 * result + (hits ?: 0)
        return result
    }

    override fun toString(): String =
        "StationLocalDto(id='$id', " +
            "name=$name, " +
            "latitude=$latitude, " +
            "longitude=$longitude, " +
            "hits=$hits)"
}