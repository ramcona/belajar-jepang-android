package id.synertia.belajarbahasajepang.helper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class MoneyTextWatcher(private val editText: EditText) : TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {
        editText.removeTextChangedListener(this)
        formatValue(s.toString())
        editText.addTextChangedListener(this)
    }

    fun formatValue(originalString: String) {
        try {
            var formattedString = ""
            if (originalString.isNotEmpty()) {
                val longVal = originalString.replace("[^\\d]".toRegex(), "").toLong()
                val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
                formatter.applyPattern("#,###,###,###")
                formattedString = formatter.format(longVal)
            }
            editText.setText(formattedString)
            editText.setSelection(editText.text!!.length)
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }
    }
}