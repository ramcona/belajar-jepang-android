package id.synertia.belajarbahasajepang.data.responseModel


import id.synertia.belajarbahasajepang.data.model.Penjelasan
import java.io.Serializable

class PenjelasanResponse() : Serializable {
    var penjelasan: List<Penjelasan> = ArrayList()
    var code: Int = 0
    var error: Boolean = false
}