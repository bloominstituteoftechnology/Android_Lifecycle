package com.lambda.imageviewer;

import android.net.Uri;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

class ImageData implements Serializable {
    private String stringUri;
    private TextView tV;
    private int index;
    ImageData(String stringUri, int index){
        this.stringUri = stringUri;
        this.index=index;


    }

    public void setIndex(int index){
        this.index=index;

    }

    public int getIndex(){
        return this.index;
    }

    public String getStringUri() {
        return stringUri;
    }

    public void setStringUri(String stringUri) {
        this.stringUri = stringUri;
    }

    public void setUri(Uri uriUri) {
        this.stringUri=uriUri.toString();

    }

    public String toString(Uri uri) {
         this.stringUri=uri.toString();
        return super.toString();
    }

    public Uri getUri(){

        Uri uriUri=Uri.parse(this.stringUri);

        return uriUri;
    }


}
