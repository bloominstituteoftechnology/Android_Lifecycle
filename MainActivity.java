package com.lambda.imageviewer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private static final int IMAGE_REQUEST_CODE = 50;
    private ImageView imageView;
    private ImageData imageData;
    private static ArrayList<ImageData> listImages = new ArrayList(50);
    private static int iCounter = 1;
    private static boolean fromOtherScreen=false;


    @Override

    protected void onStart() {

        super.onStart();

        Log.i("ActivityLifecycle", getLocalClassName() + " - onStart");

        LinearLayout ll = findViewById(R.id.scrolling_view);







    }


    @Override

    protected void onResume() {

        super.onResume();

        Log.i("ActivityLifecycle", getLocalClassName() + " - onResume");
        PrecessOnResuming();

    }





    // user interacting with app


    @Override

    protected void onPause() {

        super.onPause();

        Log.i("ActivityLifecycle", getLocalClassName() + " - onPause");

    }


    @Override

    protected void onStop() {

        super.onStop();

        Log.i("ActivityLifecycle", getLocalClassName() + " - onStop");

    }


    @Override

    protected void onDestroy() {

        super.onDestroy();

        Log.i("ActivityLifecycle", getLocalClassName() + " - onDestroy");

    }

    private int mTransitionCount;   // 遷移した回数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("ActivityLifecycle", getLocalClassName() + " - onCreate");

        setContentView(R.layout.activity_main);

        LinearLayout ll = findViewById(R.id.scrolling_view);


        findViewById(R.id.add_image_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                intent.setType("image/*");

                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }

        });

    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK && requestCode == IMAGE_REQUEST_CODE) {

            if (data != null) {

                Uri dataUri = data.getData();
                listImages.add(ShowResult(iCounter++, dataUri));





            }

        }

    }

    private void PrecessOnResuming(){
  //      setContentView(R.layout.activity_main);
        LinearLayout ll = findViewById(R.id.scrolling_view);
        ll.invalidate();


        Bundle extras =getIntent().getExtras();
        if(extras!=null) {


            String stringNew = extras.getString("COMING_BACK");
            if (stringNew.equals("I am back")&&fromOtherScreen==true) {
                ShowAllResult(listImages);

            }

        }
    }

    private ImageData ShowResult(int index, Uri dataUri) {
        String name = "";
        TextView tv = new TextView(getApplicationContext());

        imageData = new ImageData(dataUri.toString(), index);
        name = imageData.getStringUri();
        tv.setText("[" + index + "] " + name);
        LinearLayout ll = findViewById(R.id.scrolling_view);

        ll.addView(tv);

        imageData.setUri(dataUri);
        imageView = new ImageView(getApplicationContext());
        imageView.setImageURI(dataUri);
        imageView.setTag(dataUri.toString());
        ll.addView(imageView);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tva = findViewById(R.id.text_results);
                sendData((TextView) v);


            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendDataforDetail(v.getTag().toString());
            }
        });
        fromOtherScreen=false;
        return imageData;
    }

    private void ShowAllResult(ArrayList<ImageData> listImages) {

        LinearLayout ll = findViewById(R.id.scrolling_view);

        ImageData imageData = new ImageData("", 0);
        Context context=getApplicationContext();
        if(ll.getContext()==null&&listImages.get(0).getIndex()!=1)return;
        for (int i = 0; i < listImages.size(); i++) {
            imageData = listImages.get(i);

            TextView tv = new TextView(context);
            imageView = new ImageView(context);
            tv.setText("[" + imageData.getIndex() + "] " + imageData.getStringUri());
            imageView.setImageURI(imageData.getUri());

            ll.addView(tv);
            ll.addView(imageView);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sendData((TextView) v);
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendDataforDetail(v.getTag().toString());
                }
            });

        }
        fromOtherScreen=false;

    }

    void sendDataforDetail(String str){
        TextView tva = findViewById(R.id.text_results);


         ImageData imageData = new ImageData(str, 0);

        Context     context= getApplicationContext();

        Intent intent = new Intent(context, activity_detail.class);
        intent.putExtra("DATA_I_NEED",imageData);
        fromOtherScreen=true;
        startActivity(intent);
    }


    void sendData(TextView tv) {


        TextView tva = findViewById(R.id.text_results);


        String stringUri = (String) tv.getText();
        tva.setText(stringUri);
        stringUri= ExtractUri(stringUri);


        ImageData imageData = new ImageData(stringUri, 0);
        imageData.setStringUri(stringUri);

        Context context;
        context= getApplicationContext();


        Intent intent = new Intent(context, FullscreenActivity.class);
        intent.putExtra("STRING_I_NEED",stringUri);
        fromOtherScreen=true;
        startActivity(intent);

    }

    //Remove index attached in front of URI
    private String ExtractUri(String stringUri){
        for(int i=0;i<stringUri.length();++i){
            if(stringUri.charAt(i)==' ') {
                stringUri=(String)stringUri.substring(i+1);
                return stringUri;
            }
        }
        return stringUri;
    }


}
