package com.example.kilojoulecounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Calculator extends AppCompatActivity {
    public String dateToday;
    public int breakfast = 0;
    public int lunch = 0;
    public int dinner = 0;
    public int snacks = 0;
    public int foodTotal=0;
    public int gym =0;
    public int sports = 0;
    public int jogging = 0;
    public int exTotal = 0;
    public int netTotal;
    public EditText breakView;
    public EditText  lunchView;
    public EditText  dinnerView;
    public EditText  snacksView;
    public EditText  gymView;
    public EditText  sportsView;
    public EditText  joggingView;
    public TextView foodText;
    public TextView gymText;
    public TextView netText;
    public Button buttonSave;
    private ArrayList<DiaryEntry> diary;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_icon);
        toolbar.setOverflowIcon(drawable);

        setDate();
        breakView = (EditText )findViewById(R.id.breakfastInput);
        lunchView = (EditText )findViewById(R.id.lunchInput);
       dinnerView = (EditText )findViewById(R.id.DinnerInput);
        snacksView = (EditText )findViewById(R.id.snacksInput);
        gymView = (EditText )findViewById(R.id.gymInput);
        sportsView = (EditText )findViewById(R.id.sportInput);
        joggingView = (EditText )findViewById(R.id.joggingInput);
        foodText = (TextView) findViewById(R.id.foodSubtotal);
        gymText = (TextView) findViewById(R.id.ExerciseSubtotal);
        netText = (TextView) findViewById(R.id.netTotal);
        buttonSave = (Button) findViewById(R.id.saveButton);
        loadData();


        breakView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                breakfast = Integer.parseInt(breakView.getText().toString());
                updateFields();
            }
        });

        lunchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                lunch = Integer.parseInt(lunchView.getText().toString());
                updateFields();
            }
        });

        dinnerView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dinner = Integer.parseInt(dinnerView.getText().toString());
                updateFields();
            }
        });

        snacksView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                snacks = Integer.parseInt(snacksView.getText().toString());
                updateFields();
            }
        });

        gymView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                gym = Integer.parseInt(gymView.getText().toString());
                updateFields();
            }
        });


        sportsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sports = Integer.parseInt(sportsView.getText().toString());
                updateFields();
            }
        });

        joggingView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                jogging = Integer.parseInt(joggingView.getText().toString());
                updateFields();
            }
        });





    }



    public void updateFields(){
        foodTotal = breakfast+lunch+dinner+snacks;
        exTotal = gym+sports+jogging;
        netTotal = foodTotal-exTotal;
        foodText.setText("Food Subtotal:  "+foodTotal);
        gymText.setText("Exercise Subtotal:  "+exTotal);
        netText.setText("Nett Total Kilojouls:  "+netTotal);
    }

    public void setDate(){
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(today);
        TextView view = findViewById(R.id.dateTextView);
        view.setText(date);
        dateToday = date;

    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void saveEntry(View view){
        DiaryEntry temp = new DiaryEntry(dateToday,breakfast,lunch,dinner,snacks,foodTotal,gym,sports,jogging,exTotal,netTotal);
        diary.add(temp);


        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(diary);
        editor.putString("task list",json);
        editor.apply();


        Intent intent = new Intent(this, DiaryPage.class);
        intent.putExtra("activity","Calculator");
        startActivity(intent);

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
                Toast.makeText(this,"Already Here", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.goHomeMenuItem:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            default:return super.onOptionsItemSelected(item);

        }



    }


}
