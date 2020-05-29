package com.example.recyclerandcardviewapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    TextView mTitleView, mDescriptionView, mProfessorView, mDayView, mHourView, mDateView;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ActionBar actionBar=getSupportActionBar();

        //Referenciando elementos de activity_2.xml
        mTitleView=findViewById(R.id.titleView);
        mDescriptionView=findViewById(R.id.descriptionView);
        mImageView=findViewById(R.id.imageView);
        mProfessorView=findViewById(R.id.professorView);
        mDayView=findViewById(R.id.dayView);
        mHourView=findViewById(R.id.hourView);
        mDateView=findViewById(R.id.dateView);

        //Seleccionando datos de la actividad principal con intent
        Intent intent=getIntent();
        String mTitle=intent.getStringExtra("iTitle");
        String mDesc=intent.getStringExtra("iDesc");

        byte[] mBytes=getIntent().getByteArrayExtra("iImage");
        Bitmap bitmap= BitmapFactory.decodeByteArray(mBytes,0,mBytes.length);

        String mProf=intent.getStringExtra("iProf");
        String mDay=intent.getStringExtra("iDay");
        String mHour=intent.getStringExtra("iHour");
        String mDate=intent.getStringExtra("iDate");

        //Asignando los datos al activity_2.xml
        actionBar.setTitle(mTitle);
        mTitleView.setText(mTitle);
        mDescriptionView.setText(mDesc);
        mImageView.setImageBitmap(bitmap);
        mProfessorView.setText(mProf);
        mDayView.setText(mDay);
        mHourView.setText(mHour);
        mDateView.setText(mDate);
    }
}
