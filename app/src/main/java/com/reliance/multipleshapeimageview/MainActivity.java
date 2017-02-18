package com.reliance.multipleshapeimageview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MultipleShapeImageView mView1
    ,mView2,mView3,mView4,mView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView1 = (MultipleShapeImageView) findViewById(R.id.msv_shper1);
        mView1.setMultipleShaper(new CircleShaper());
        mView2 = (MultipleShapeImageView) findViewById(R.id.msv_shper2);
        mView2.setMultipleShaper(new RoundedRectangleShaper());
        mView3 = (MultipleShapeImageView) findViewById(R.id.msv_shper3);
        mView3.setMultipleShaper(new HeartShaped());
        mView4 = (MultipleShapeImageView) findViewById(R.id.msv_shper4);
        mView4.setMultipleShaper(new FivePointedStar());
        mView5 = (MultipleShapeImageView) findViewById(R.id.msv_shper5);
        mView5.setMultipleShaper(new TriangleShper());

    }


}
