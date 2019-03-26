package com.example.imageviewer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    public static final int IMAGE_REQUEST_CODE = 54;
    public static int id = 0;
    public static String txt = "blah";


    Button buttonAddImage;
    LinearLayout scrollLayout;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i( getLocalClassName(),this.getClass().getSimpleName() + " onCreate");


        context =this;

        buttonAddImage = findViewById(R.id.button_add);
        scrollLayout = findViewById(R.id.layout_scroll);

        //scrollLayout.addView(createTextView(id++, txt));
        buttonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(intent, IMAGE_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i( getLocalClassName(),this.getClass().getSimpleName() + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i( getLocalClassName(),this.getClass().getSimpleName() + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i( getLocalClassName(),this.getClass().getSimpleName() + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i( getLocalClassName(),this.getClass().getSimpleName() + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i( getLocalClassName(),this.getClass().getSimpleName() + "onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == IMAGE_REQUEST_CODE) {
                Uri photoUri = data.getData();
                ImageData img = new ImageData();
                img.setUri(photoUri);
                img.setName("test");

                ArrayList images = new ArrayList() {};
                images.add(img);


                scrollLayout.addView(createTextView(img));
                //((ImageView)findViewById(R.id.image)).setImageURI(photoUri);
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    public TextView createTextView(final int id, final String txt)
    {
        final Intent txtIntent = new Intent(context, FullscreenActivity.class);

        final TextView tv = new TextView(context);
        tv.setText(txt);
        tv.setId(id);
        //tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtIntent.putExtra(ImageData.TAG, txt);
                //txtIntent.putExtra("value", id);
                startActivity(txtIntent);


            }
        });
        return tv;
    }
    public TextView createTextView(final ImageData img)
    {
        final Intent txtIntent = new Intent(context, detailsActivity.class);

        final TextView tv = new TextView(context);
        tv.setText(img.getUri().toString());
        tv.setId(id);
        //tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtIntent.putExtra(ImageData.TAG, img);
                //txtIntent.putExtra("value", id);
                startActivity(txtIntent);


            }
        });
        return tv;
    }


}

