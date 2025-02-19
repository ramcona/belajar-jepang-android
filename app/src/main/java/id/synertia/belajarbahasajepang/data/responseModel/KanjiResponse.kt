package id.synertia.belajarbahasajepang.data.responseModel


import id.synertia.belajarbahasajepang.data.model.Bunpo
import id.synertia.belajarbahasajepang.data.model.Kanji
import java.io.Serializable

class KanjiResponse() : Serializable {
    var kanji: List<Kanji> = ArrayList()
    var code: Int = 0
    var error: Boolean = false
}