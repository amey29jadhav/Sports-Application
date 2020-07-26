package com.amey.sports_android.utilities;

import android.content.Context;
import android.graphics.Typeface;

import androidx.core.content.res.ResourcesCompat;

import com.amey.sports_android.R;

import java.util.HashMap;


public class TypeFaceHelper {

    public static final String REGULAR ="font/roboto_regular.ttf";
    public static final String MEDIUM = "font/roboto_medium.ttf";


    private static TypeFaceHelper typeFaceHelper;
    private final Context context;



    private TypeFaceHelper(Context context) {
        this.context = context;
    }

    public static TypeFaceHelper getInstance(Context context){
        if (typeFaceHelper == null){
            typeFaceHelper = new TypeFaceHelper(context);
        }
        return typeFaceHelper;
    }


    private static HashMap<String, Typeface> sTypeFaces = new HashMap<>(1);

    public Typeface getStyleTypeFace(String fileName) {
        Typeface typeface = sTypeFaces.get(fileName);
        if (typeface == null) {
            typeface = ResourcesCompat.getFont(context, R.font.roboto_medium);
            //typeface = Typeface.createFromAsset(context.getAssets(), fileName);
            sTypeFaces.put(fileName, typeface);
        }
        return typeface;
    }
}
