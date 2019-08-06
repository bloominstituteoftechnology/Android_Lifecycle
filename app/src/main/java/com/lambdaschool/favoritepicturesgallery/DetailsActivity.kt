package com.lambdaschool.favoritepicturesgallery

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var data: ImageData? = null

    //sets layout up JBG
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Makes it possible to return to Main activity by pressing back button JBG
        val intent = intent
        data = intent.getSerializableExtra("object") as ImageData
        val context = this

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        //gives the ability to enter fullscreen activity by clicking image JBG
        image.setOnClickListener {
            val intent = Intent(context, FullscreenActivity::class.java)
            intent.putExtra("image", data?.fileUriString)
            startActivity(intent)
        }

    }

    //collects image and data to display JBG
    override fun onStart() {
        super.onStart()
        Log.i(javaClass.simpleName, "onStart")

        image.setImageURI(data?.fileUri)
        //        image.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star));
        text_name.text = data?.name
        edit_name.setText(data?.name)
        text_uri.text = data?.fileUri.toString()
        text_description.text = data?.description
        edit_description.setText(data?.description)
    }

    // makes it possible to return to Main activity by pressing back button. JBG
    override fun onBackPressed() {
        data?.name = edit_name.text.toString()
        data?.description = edit_description.text.toString()
        val resultIntent = Intent()
        resultIntent.putExtra("object", data)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        Log.i(javaClass.simpleName, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(javaClass.simpleName, "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(javaClass.simpleName, "onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i(javaClass.simpleName, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(javaClass.simpleName, "onRestart")
    }
}
