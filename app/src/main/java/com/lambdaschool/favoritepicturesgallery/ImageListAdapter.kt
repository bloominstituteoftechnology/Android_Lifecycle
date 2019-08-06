package com.lambdaschool.favoritepicturesgallery

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lambdaschool.favoritepicturesgallery.ImageListAdapter.ViewHolder

import java.util.ArrayList

/**
 * Not required for intent assignment
 */
class ImageListAdapter// Provide a suitable constructor (depends on the kind of dataset)
(private val imageList: ArrayList<ImageData>, private val activity: Activity) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    private var context: Context? = null

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // each data item is just a string in this case
        var imageThumbnail: ImageView = view.findViewById(R.id.image_thumbnail)
        var textImageName: TextView = view.findViewById(R.id.text_image_name)
        var layoutMain: LinearLayout = view.findViewById(R.id.layout_main)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ImageListAdapter.ViewHolder {
        Log.i("LifeCycle", "${javaClass.simpleName} onCreateViewHolder")
        context = parent.context

        // create a new view
        val imageView = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_list_view, parent, false)

        return ViewHolder(imageView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ImageListAdapter.ViewHolder, position: Int) {
        Log.i("LifeCycle", "${javaClass.simpleName} onBindViewHolder")
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val data = imageList[position]

        holder.imageThumbnail.setImageURI(data.fileUri)
        holder.textImageName.text = data.name
        holder.layoutMain.tag = position
        holder.layoutMain.setOnClickListener { view ->
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra("object", imageList[Integer.parseInt(view.tag.toString())])
            (view.context as Activity).startActivityForResult(intent, EDIT_IMAGE_REQUEST)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return imageList.size
    }

    companion object {

        internal val EDIT_IMAGE_REQUEST = 2
    }
}
