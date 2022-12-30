package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

class MainActivity : AppCompatActivity(), AsyncResponse {
    lateinit var mTextView: TextView
    var checked = false
    val url = "https://api.publicapis.org/entries"
    var asyncTask: DownloadFilesTask = DownloadFilesTask()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        System.out.println("onCreate")
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                // Restore value of members from saved state
               // checked = getBoolean(STATE_CHECKED)
            }
        }

        setContentView(R.layout.activity_main);
        val mCheckBox: CheckBox = findViewById(R.id.checkBox)
        mCheckBox.setChecked(checked);
        mCheckBox.setOnCheckedChangeListener{ _, isChecked -> if(isChecked) {
           checked = true
        }}

        val mButton: Button = findViewById(R.id.button)
        val mEditText: EditText = findViewById(R.id.editText)
        this.mTextView = findViewById(R.id.textView)
        mButton.setOnClickListener{
            val text = mEditText.text
            this.mTextView.setText(text)
        }
        asyncTask.delegate = this
        asyncTask.execute(URL(url));

    }

    override fun onSaveInstanceState(outState: Bundle) {
        System.out.println("onSaveInstanceState")
        // Save the user's current game state
        outState?.run {
            //putBoolean(STATE_CHECKED, checked)
        }

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState)
    }

    companion object {
        val STATE_CHECKED = "checked"
    }

    override fun onStart() {
        super.onStart()
        System.out.println("onStart")
    }

    override fun onResume() {
        super.onResume()
        System.out.println("onResume")
    }

    override fun onPause() {
        super.onPause()
        System.out.println("onPause")
    }

    override fun onStop() {
        super.onStop()
        System.out.println("onStop")
    }

    override fun onDestroy() {
        System.out.println("onDestroy")
        super.onDestroy()
    }

    override fun processFinish(output: String?) {
        this.mTextView.setText(output)
    }

}