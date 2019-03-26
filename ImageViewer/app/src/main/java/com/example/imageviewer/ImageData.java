package com.example.imageviewer;

import android.net.Uri;

import java.io.Serializable;

public class ImageData implements Serializable
{
    private String name, stringUri;
    public static final String TAG = "image";

    public ImageData()
    {
        this.name = name;
        this.stringUri = stringUri;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return Uri.parse(stringUri);
    }

    public void setUri(Uri uri) {
        this.stringUri =  uri.toString();
    }
}


