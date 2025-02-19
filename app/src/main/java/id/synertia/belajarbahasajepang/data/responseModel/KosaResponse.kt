package id.synertia.belajarbahasajepang.data.responseModel


import id.synertia.belajarbahasajepang.data.model.Bunpo
import id.synertia.belajarbahasajepang.data.model.Kosa
import java.io.Serializable

class KosaResponse() : Serializable {
    var kosa: List<Kosa> = ArrayList()
    var code: Int = 0
    var error: Boolean = false
}