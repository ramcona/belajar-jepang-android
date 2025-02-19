package id.synertia.belajarbahasajepang.extention

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import id.synertia.belajarbahasajepang.helper.MoneyTextWatcher

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun EditText.addMoneyTextWatcher() {
    val watcher = MoneyTextWatcher(this)
    this.addTextChangedListener(watcher)
    this.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            watcher.formatValue(this.text.toString())
        }
    }
}

fun EditText.getRawValue(): Long {
    val text = this.text.toString()
    if (text.isEmpty()) return 0L
    return text.replace("[^\\d]".toRegex(), "").toLong()
}
