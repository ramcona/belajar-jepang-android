package id.synertia.belajarbahasajepang.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Penjelasan() : Serializable, Parcelable {
    var id: String = ""
    var id_level: String = ""
    var judul: String = ""
    var penjelasan: String = ""
    var created_by: String = ""
    var created_at: String = ""
    var updated_at: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString() ?: ""
        id_level = parcel.readString() ?: ""
        judul = parcel.readString() ?: ""
        penjelasan = parcel.readString() ?: ""
        created_by = parcel.readString() ?: ""
        created_at = parcel.readString() ?: ""
        updated_at = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(id_level)
        parcel.writeString(judul)
        parcel.writeString(penjelasan)
        parcel.writeString(created_by)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Penjelasan> {
        override fun createFromParcel(parcel: Parcel): Penjelasan {
            return Penjelasan(parcel)
        }

        override fun newArray(size: Int): Array<Penjelasan?> {
            return arrayOfNulls(size)
        }
    }
}