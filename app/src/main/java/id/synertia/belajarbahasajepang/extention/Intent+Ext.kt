package id.synertia.belajarbahasajepang.extention

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import id.synertia.belajarbahasajepang.helper.Config.extra_model

inline fun <reified T : Parcelable> Intent.getParcelableExtras(default: T): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getParcelableExtra(extra_model, default.javaClass) ?: default
    } else {
        this.getParcelableExtra(extra_model) ?: default
    }
}

//fun Intent.putExtras(
//    id: String = "",
//    type: String = "",
//    other: String = "",
//    choose: Boolean = false,
//    listData: List<Parcelable> = emptyList(),
//    data: Parcelable? = null,
//    url: String = ""
//): Intent {
//    putExtra(extra_id, id)
//    putExtra(extra_type, type)
//    putExtra(extra_other, other)
//    putExtra(extra_choose, choose)
//    putExtra(Config.extra_list, ArrayList(listData))
//    putExtra(extra_url, url)
//    if (data != null) {
//        putExtra(extra_model, data)
//    }
//    return this
//}
//
//fun Intent.start(ctx: Context, target: Class<*>, clearPrevious: Boolean = false) {
//    if (clearPrevious) {
//        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//    }
//    ComponentName(ctx, target).also {
//        setClassName(it.packageName, it.className)
//    }
//    ctx.startActivity(this)
//}
