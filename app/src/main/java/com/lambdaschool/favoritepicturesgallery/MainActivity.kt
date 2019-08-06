package com.lambdaschool.favoritepicturesgallery

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    internal var imageList: ArrayList<ImageData> = ArrayList()
    private var layoutManager: LinearLayoutManager? = null
    private var listAdapter: ImageListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Lifecycle - onCreate", Toast.LENGTH_SHORT).show()
        Log.i(javaClass.simpleName, "Lifecycle-onCreate")
        setContentView(R.layout.activity_main)
        val context = this

        imageList = ArrayList()

        button_add.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, REQUEST_IMAGE_GET)
            }
        }
        Log.i("Activity_State_Log_Data", imageList.toString())


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        layout_list.setHasFixedSize(true)

        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        layout_list.layoutManager = layoutManager

        // specify an adapter (see also next example)
        listAdapter = ImageListAdapter(imageList, this)
        layout_list.adapter = listAdapter

    }

    private fun refreshListView() {
        listAdapter!!.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            val fullPhotoUri = data!!.data
            if (fullPhotoUri != null) {
                imageList.add(ImageData(fullPhotoUri))
                listAdapter!!.notifyItemInserted(imageList.size - 1)
            }
        } else if (requestCode == EDIT_IMAGE_REQUEST && resultCode == RESULT_OK) {
            // Make sure the request was successful
            val returnedData = data!!.getSerializableExtra("object") as ImageData

            for (i in imageList.indices) {
                if (imageList[i].fileUriString == returnedData.fileUriString) {
                    imageList[i] = returnedData
                }
            }

        }
        refreshListView()

    }

    companion object {

        internal const val REQUEST_IMAGE_GET = 1
        internal const val EDIT_IMAGE_REQUEST = 2
    }
    override fun onRestart() {
        Toast.makeText(this, "Lifecycle - onRestart", Toast.LENGTH_SHORT).show()
        Log.i(javaClass.simpleName, "Lifecycle-onRestart")
        super.onRestart()
    }
    override fun onDestroy() {
        Toast.makeText(this, "Lifecycle - onDestroy", Toast.LENGTH_SHORT).show()
        Log.i(javaClass.simpleName, "Lifecycle-onDestroy")
        super.onDestroy()
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "Lifecycle - onStop", Toast.LENGTH_SHORT).show()
        Log.i(javaClass.simpleName, "Lifecycle-onStop")

    }
    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "Lifecycle - onPause", Toast.LENGTH_SHORT).show()
        Log.i(javaClass.simpleName, "Lifecycle-onPause")

    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "Lifecycle - onResume", Toast.LENGTH_SHORT).show()
        Log.i(javaClass.simpleName, "Lifecycle-onResume")

    }
    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "Lifecycle - onStart", Toast.LENGTH_SHORT).show()
        Log.i(javaClass.simpleName, "Lifecycle-onStart")
    }


}
