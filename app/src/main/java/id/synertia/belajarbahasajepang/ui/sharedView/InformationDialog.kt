package id.synertia.belajarbahasajepang.ui.sharedView

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import id.synertia.belajarbahasajepang.R
import id.synertia.belajarbahasajepang.databinding.InformationDialogBinding

class InformationDialog(
    private val context: Context,
    private val title: String,
    private val message: String,
    private var callback: Callback? = null) {

    @SuppressLint("InflateParams")
    fun show() {
        // inflate and binding the layout
        val dialogView: View =
            LayoutInflater.from(context).inflate(R.layout.information_dialog, null)
        val binding = InformationDialogBinding.bind(dialogView)

        // set the title and message of the dialog
        binding.title.text = title
        binding.message.text = message

        // Create an AlertDialog with the inflated custom layout
        val alertDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()

        // Set the window background to be transparent
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // set positive custom view button
        binding.okBtn.setOnClickListener {
            alertDialog.dismiss()
            callback?.onButtonClick()
        }

        // display the dialog
        alertDialog.show()
    }

    interface Callback {
        fun onButtonClick()
    }
}
