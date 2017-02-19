package com.reliance.multipleshapeimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MultipleShapedImageView mView1
    ,mView2,mView3,mView4,mView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView1 = (MultipleShapedImageView) findViewById(R.id.msv_shper1);
        mView1.setMultipleShaper(new CircleShaper());
        mView2 = (MultipleShapedImageView) findViewById(R.id.msv_shper2);
        mView2.setMultipleShaper(new RoundedRectangleShaper());
        mView3 = (MultipleShapedImageView) findViewById(R.id.msv_shper3);
        mView3.setMultipleShaper(new HeartShaper());
        mView4 = (MultipleShapedImageView) findViewById(R.id.msv_shper4);
        mView4.setMultipleShaper(new FivePointedStar());
        mView5 = (MultipleShapedImageView) findViewById(R.id.msv_shper5);
        mView5.setMultipleShaper(new TriangleShaper());

    }


}
