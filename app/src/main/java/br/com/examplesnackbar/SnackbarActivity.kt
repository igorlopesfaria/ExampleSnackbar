package br.com.examplesnackbar

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class SnackbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)

        val simpleSnackbarBTN = findViewById<Button>(R.id.simpleSnackbarBTN)
        val longSnackbarBTN = findViewById<Button>(R.id.longSnackbarBTN)
        val indefiniteSnackbarBTN = findViewById<Button>(R.id.indefiniteSnackbarBTN)
        val genericMethodSnackbarBTN = findViewById<Button>(R.id.genericMethodSnackbarBTN)
        val addActionSnackbarBTN = findViewById<Button>(R.id.addActionSnackbarBTN)
        val customSnackbarBTN = findViewById<Button>(R.id.customSnackbarBTN)
        val callbackSnackbarBTN = findViewById<Button>(R.id.callbackSnackbarBTN)
        val customViewSnackbarBTN = findViewById<Button>(R.id.customViewSnackbarBTN)

        simpleSnackbarBTN.setOnClickListener {
            Snackbar.make(it, "Simple Snackbar", Snackbar.LENGTH_SHORT)
                .show()
        }

        longSnackbarBTN.setOnClickListener {
            Snackbar.make(it, "Long Snackbar", Snackbar.LENGTH_LONG)
                .show()
        }

        genericMethodSnackbarBTN.setOnClickListener {
            genericMethodSnackbar(it, "Generic Method Snackbar")
        }

        addActionSnackbarBTN.setOnClickListener {
            Snackbar.make(it, "Snack with action show toast", Snackbar.LENGTH_LONG)
                .setAction("Action") {
                    Log.i("Snackbar", "Clicked on Action")
                }
                .show()
        }

        indefiniteSnackbarBTN.setOnClickListener {
            Snackbar.make(it, "Infinite Snackbar", Snackbar.LENGTH_INDEFINITE)
                .show()
        }

        customSnackbarBTN.setOnClickListener {
            Snackbar.make(it, "Custom snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action") {
                    Log.i("Snackbar", "Clicked in Action")
                }
                .setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
                .setBackgroundTint(ContextCompat.getColor(applicationContext, R.color.purple_200))
                .setActionTextColor(ContextCompat.getColor(applicationContext, R.color.teal_700))
                .show()
        }


        callbackSnackbarBTN.setOnClickListener {
            Snackbar.make(it, "Infinite Snackbar", Snackbar.LENGTH_LONG)
                .addCallback( object:Snackbar.Callback(){
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        Log.i("Snackbar", "Dismissed")
                    }

                    override fun onShown(sb: Snackbar?) {
                        super.onShown(sb)
                        Log.i("Snackbar", "Shown")
                    }
                })
                .show()
        }

        customViewSnackbarBTN.setOnClickListener {
            CustomSnackbar.makeActionMessage(
                parent = findViewById(android.R.id.content),
                iconImage = android.R.drawable.ic_menu_close_clear_cancel,
                messageText = "Custom view component",
                actionText = "Action",
                action = {
                    Log.i("Snackbar", "Action click listener")
                }
            ).show()
        }
    }

    private fun genericMethodSnackbar(view: View, text: String, duration: Int = Snackbar.LENGTH_SHORT){
        Snackbar.make(view, text, duration).show()
    }
}