package com.example.kilojoulecounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DiaryEntry> diary;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_icon);
        toolbar.setOverflowIcon(drawable);

        loadData();
        double sum = 0;
        for(int i = 0;i<diary.size();i++){
            sum+=diary.get(i).getNetTotal();
        }
        double average = sum/diary.size()*10;
        average  = Math.round(average)/10;
        TextView aveText = (TextView) findViewById(R.id.averageNKIText);
        aveText.setText(getString(R.string.average_NKI)+average);



        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(diary);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                diary.get(position);
                Intent intent = new Intent(mRecyclerView.getContext(), DiaryPage.class);
                intent.putExtra("fromOverview",position);
                startActivity(intent);
            }
        });



    }


    public void openNewEntry(View view){
        Intent intent = new Intent(this, Calculator.class);
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
                Intent intent = new Intent(this, Calculator.class);
                startActivity(intent);
                return true;

            case R.id.goHomeMenuItem:
                Toast.makeText(this,"Already Here", Toast.LENGTH_SHORT).show();
                return true;

            default:return super.onOptionsItemSelected(item);

        }



    }
}
