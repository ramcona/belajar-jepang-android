package id.synertia.belajarbahasajepang.base

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class BaseFragment: Fragment() {
    fun removeLoading(shimmer: ShimmerFrameLayout? = null) {
        if (shimmer != null) {
            shimmer.isVisible = false
            shimmer.stopShimmer()
        }else {
//            App.loadingDialog.dismiss()
        }
    }

    fun showLoading(shimmer: ShimmerFrameLayout? = null) {

        if (shimmer != null) {
            shimmer.isVisible = true
            shimmer.showShimmer(true)
        }else {
//            App.loadingDialog = LoadingDialog(requireActivity())
//            App.loadingDialog.show()
        }
    }

//    fun showMessageDialog(title:String, message:String, callback: InformationDialog.Callback? = null) {
//        InformationDialog(requireContext(), title, message,callback).show()
//    }



}