package com.example.imageveiwer3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity<imageList> extends AppCompatActivity {

    static final int REQUESTCODE = 0;
    private Context context = this;
    private LinearLayout linearLayout;
    private Button getPicButton;
    ArrayList<ImageViewerModel> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ActivityLifecycle",getLocalClassName() +  " - onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.inter_LinearLayout);
        getPicButton = findViewById(R.id.button_get_pic);
        getPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickImage = new Intent(Intent.ACTION_PICK);
                pickImage.setType("image/*");
                pickImage.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(pickImage,REQUESTCODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && REQUESTCODE == requestCode){
            if (data != null){
                Uri imageUri = data.getData();
                ImageViewerModel imageViewerModel = new ImageViewerModel(imageUri.toString(), "imageUri");
                imageList.add(imageViewerModel);
                generateTextView(Integer.toString(imageList.size()), imageList.indexOf(imageViewerModel));
            }

        }
    }

    public TextView generateTextView( String text, final int index){
       final TextView listedPicture = new TextView(context);

        listedPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listedPicture.setTag(imageList);
                Intent sentImage = new  Intent(context,ImageViewerDetails.class);
                ImageViewerModel imageViewerModel = imageList.get(index);
                sentImage.putExtra("sentImage",imageViewerModel);
                startActivity(sentImage);
            }
        });

        listedPicture.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        listedPicture.setText(text);
        listedPicture.setTextSize(25);
        listedPicture.setPadding(15,15,15,15);
        linearLayout.addView(listedPicture);

        return listedPicture;

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityLifecycle",getLocalClassName() +  " - onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityLifecycle",getLocalClassName() +  " - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityLifecycle",getLocalClassName() +  " - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityLifecycle",getLocalClassName() +  " - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityLifecycle",getLocalClassName() +  " - onDestroy");
    }


}
