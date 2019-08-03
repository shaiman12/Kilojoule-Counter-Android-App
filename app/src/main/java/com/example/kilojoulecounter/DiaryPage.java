package com.example.kilojoulecounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DiaryPage extends AppCompatActivity {

    private ArrayList<DiaryEntry> diary;
    private int currentEntry;
    public  TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_icon);
        toolbar.setOverflowIcon(drawable);

        loadData();

        tv = (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();
        String activityPrev = intent.getStringExtra("activity");
        String output = "";
        if(activityPrev==null){
            currentEntry = intent.getIntExtra("fromOverview",0);
            output=diary.get(currentEntry).toString();

        }
        else if(activityPrev.equalsIgnoreCase("Calculator")){
            output = diary.get(diary.size()-1).toString();
            currentEntry = diary.size()-1;
        }


        tv.setText(output);
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list",null);
        Type type = new TypeToken<ArrayList<DiaryEntry>>() {}.getType();
        diary = gson.fromJson(json,type);
        if(diary==null){
            diary = new ArrayList<DiaryEntry>();
        }
    }


    public void goHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void returnToCalc(View view){
        Intent intent = new Intent(this, Calculator.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goToPrev(View view){
        if(currentEntry==0){
            Toast.makeText(getApplicationContext(),"This is the First Entry!",Toast.LENGTH_SHORT).show();
        }else{
            currentEntry--;
            tv.setText(diary.get(currentEntry).toString());
        }

    }


    public void goToNext(View view){
        if(currentEntry==(diary.size()-1)){
            Toast.makeText(getApplicationContext(),"This is the Last Entry!",Toast.LENGTH_SHORT).show();
        }else{
            currentEntry++;
            tv.setText(diary.get(currentEntry).toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.calculatorMenuItem:
                Intent intent = new Intent(this, Calculator.class);

                startActivity(intent);
                return true;

            case R.id.goHomeMenuItem:
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                return true;

            default:return super.onOptionsItemSelected(item);

        }



    }

}
