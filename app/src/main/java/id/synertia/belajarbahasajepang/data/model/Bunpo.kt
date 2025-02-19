package id.synertia.belajarbahasajepang.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Bunpo() : Serializable, Parcelable {
    var id: String = ""
    var id_sub:String = ""
    var judul:String = ""
    var penjelasan:String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString() ?: ""
        id_sub = parcel.readString() ?: ""
        judul = parcel.readString() ?: ""
        penjelasan = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(id_sub)
        parcel.writeString(judul)
        parcel.writeString(penjelasan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bunpo> {
        override fun createFromParcel(parcel: Parcel): Bunpo {
            return Bunpo(parcel)
        }

        override fun newArray(size: Int): Array<Bunpo?> {
            return arrayOfNulls(size)
        }
    }

}
