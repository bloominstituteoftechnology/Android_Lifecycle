package com.lambda.imageviewer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class activity_detail extends AppCompatActivity {
    private View mContentView;

    @Override

    protected void onStart() {

        super.onStart();

        Log.i("ActivityLifecycle", getClass().getSimpleName() + " - onStart");


        setContentView(R.layout.activity_detail);
        TextView tv=findViewById(R.id.text_detail);
        ImageData id=(ImageData) getIntent().getSerializableExtra("DATA_I_NEED");
        tv.setText(id.getStringUri());
        ImageView iv=findViewById(R.id.image_on_detail);
        iv.setImageURI(id.getUri());




    }

    @Override
    public void onBackPressed()
    {
        // do stuff
        super.onBackPressed();



                // Intent のインスタンスを取得する
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // 渡したいデータとキーを指定する
                intent.putExtra("COMING_BACK","I am back");
                // 遷移先の画面を呼び出す
                startActivity(intent);

    }

    @Override

    protected void onResume() {

        super.onResume();

        Log.i("ActivityLifecycle", getClass().getSimpleName() + " - onResume");


    }


    // user interacting with app


    @Override

    protected void onPause() {

        super.onPause();

        Log.i("ActivityLifecycle", getClass().getSimpleName() + " - onPause");

    }


    @Override

    protected void onStop() {

        super.onStop();

        Log.i("ActivityLifecycle", getClass().getSimpleName() + " - onStop");

    }


    @Override

    protected void onDestroy() {

        super.onDestroy();

        Log.i("ActivityLifecycle", this.getClass().getSimpleName() + " - onDestroy");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ActivityLifecycle", getClass().getSimpleName() + " - onCreate");



    }

}
