package id.synertia.belajarbahasajepang.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Kosa() : Serializable, Parcelable {
    var id: String = ""
    var id_sub: String = ""
    var hiragana: String = ""
    var kanji: String = ""
    var romanji: String = ""
    var arti: String = ""
    var created_by: String = ""
    var created_at: String = ""
    var updated_at: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString() ?: ""
        id_sub = parcel.readString() ?: ""
        hiragana = parcel.readString() ?: ""
        kanji = parcel.readString() ?: ""
        romanji = parcel.readString() ?: ""
        arti = parcel.readString() ?: ""
        created_by = parcel.readString() ?: ""
        created_at = parcel.readString() ?: ""
        updated_at = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(id_sub)
        parcel.writeString(hiragana)
        parcel.writeString(kanji)
        parcel.writeString(romanji)
        parcel.writeString(arti)
        parcel.writeString(created_by)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Kosa> {
        override fun createFromParcel(parcel: Parcel): Kosa = Kosa(parcel)
        override fun newArray(size: Int): Array<Kosa?> = arrayOfNulls(size)
    }
}