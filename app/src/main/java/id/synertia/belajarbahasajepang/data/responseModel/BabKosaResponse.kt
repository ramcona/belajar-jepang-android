package id.synertia.belajarbahasajepang.data.responseModel


import id.synertia.belajarbahasajepang.data.model.BabKosa
import java.io.Serializable

class BabKosaResponse() : Serializable {
    var subkosa: List<BabKosa> = ArrayList()
    var code: Int = 0
    var error: Boolean = false
}