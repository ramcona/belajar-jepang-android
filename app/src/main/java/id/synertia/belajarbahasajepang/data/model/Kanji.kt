package id.synertia.belajarbahasajepang.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Kanji() : Serializable, Parcelable {
    var id: String = ""
    var kanji: String = ""
    var level_id: String = ""
    var kunyomi: String = ""
    var onyomi: String = ""
    var arti: String = ""
    var created_at: String = ""
    var updated_at: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString() ?: ""
        kanji = parcel.readString() ?: ""
        level_id = parcel.readString() ?: ""
        kunyomi = parcel.readString() ?: ""
        onyomi = parcel.readString() ?: ""
        arti = parcel.readString() ?: ""
        created_at = parcel.readString() ?: ""
        updated_at = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(kanji)
        parcel.writeString(level_id)
        parcel.writeString(kunyomi)
        parcel.writeString(onyomi)
        parcel.writeString(arti)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Kanji> {
        override fun createFromParcel(parcel: Parcel): Kanji = Kanji(parcel)
        override fun newArray(size: Int): Array<Kanji?> = arrayOfNulls(size)
    }
}