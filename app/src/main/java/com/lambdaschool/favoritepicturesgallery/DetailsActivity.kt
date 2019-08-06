import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import com.lambdaschool.favoritepicturesgallery.FullscreenActivity
import com.lambdaschool.favoritepicturesgallery.ImageData
import com.lambdaschool.favoritepicturesgallery.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

        private var data: ImageData? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_details)

            val intent = intent
            data = intent.getSerializableExtra("object") as ImageData
            val context = this

            this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

            image.setOnClickListener {
                val intent = Intent(context, FullscreenActivity::class.java)
                intent.putExtra("image", data?.fileUriString)
                startActivity(intent)
            }
            Log.i("${javaClass.simpleName}", " onCreate")
        }

        override fun onStart() {
            super.onStart()

            image.setImageURI(data?.fileUri)
            //        image.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star));
            text_name.text = data?.name
            edit_name.setText(data?.name)
            text_uri.text = data?.fileUri.toString()
            text_description.text = data?.description
            edit_description.setText(data?.description)

            Log.i("${javaClass.simpleName}", " onStart")
        }

        override fun onResume() {
            super.onResume()
            Log.i("${javaClass.simpleName}", " onResume")
        }

        override fun onRestart() {
            super.onRestart()
            Log.i("${javaClass.simpleName}", " onRestart")
        }

        override fun onPause() {
            super.onPause()
            Log.i("${javaClass.simpleName}", " onPause")
        }

        override fun onStop() {
            super.onStop()
            Log.i("${javaClass.simpleName}", " onStop")
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.i("${javaClass.simpleName}", " onDestroy")
        }

        override fun onBackPressed() {
            data?.name = edit_name.text.toString()
            data?.description = edit_description.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("object", data)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
