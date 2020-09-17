package br.com.examplesnackbar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout

class CustomSnackbar {

    companion object {
        fun makeActionMessage(
            parent: View,
            iconImage: Int? = null,
            messageText: String,
            actionText: String? = null,
            action: (() -> Unit)? = null,
            duration: Int = Snackbar.LENGTH_INDEFINITE
        ): Snackbar {
            val snackbar = Snackbar.make(parent, "", duration)

            val view = snackbar.addCustomView(R.layout.view_snackbar_simple)

            val messageTextView: TextView = view.findViewById(R.id.messageTextView)
            val iconIMG: ImageView = view.findViewById(R.id.iconIMG)
            val actionTextView: TextView = view.findViewById(R.id.actionTextView)

            iconImage?.let {
                iconIMG.setImageResource(iconImage)
            }?: run{
                iconIMG.visibility = View.GONE
            }

            messageTextView.text = messageText

            actionText?.let {
                actionTextView.text = actionText
                actionTextView.setOnClickListener { action?.invoke() }
            }?: run{
                actionTextView.visibility = View.GONE
            }
            return snackbar
        }
    }
}

fun Snackbar.addCustomView(@LayoutRes layout: Int): View {
    val snackbarLayout = this.view as Snackbar.SnackbarLayout
    val snackbarContentLayout: SnackbarContentLayout = snackbarLayout.getChildAt(0) as SnackbarContentLayout
    val customView = LayoutInflater.from(this.context).inflate(layout, snackbarLayout, false)

    snackbarContentLayout.findViewById<TextView>(R.id.snackbar_text).visibility = View.INVISIBLE
    snackbarLayout.setPadding(0, 0, 0, 0)
    snackbarLayout.setBackgroundColor(Color.TRANSPARENT)
    if (snackbarContentLayout.getChildAt(0) is AppCompatTextView)
        snackbarContentLayout.addView(customView, 0)
    return customView
}
