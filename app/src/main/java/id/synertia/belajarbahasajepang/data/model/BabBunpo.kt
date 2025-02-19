package id.synertia.belajarbahasajepang.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class BabBunpo() : Serializable, Parcelable {
    var id: String = ""
    var id_level:String = ""
    var nama:String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString() ?: ""
        id_level = parcel.readString() ?: ""
        nama = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(id_level)
        parcel.writeString(nama)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BabBunpo> {
        override fun createFromParcel(parcel: Parcel): BabBunpo {
            return BabBunpo(parcel)
        }

        override fun newArray(size: Int): Array<BabBunpo?> {
            return arrayOfNulls(size)
        }
    }
}
