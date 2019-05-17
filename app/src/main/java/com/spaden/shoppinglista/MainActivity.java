package com.spaden.shoppinglista;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogNewItem dialog = new DialogNewItem();
                dialog.show(getSupportFragmentManager(), "");
            }
        });


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        mAdapter = new ItemAdapter(this, itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewItem(Item n){
        itemList.add(n);
        mAdapter.notifyDataSetChanged();
        saveData();
    }

    private void saveData(){
        String filename = "SaveData.json";
        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            Writer writer = new OutputStreamWriter(outputStream);
            Gson gson = new Gson();
            gson.toJson(itemList, writer);
            writer.close();
            Log.d("Sparat","Sparat");
        }catch (Exception e){

            Log.e("Kan inte spara","Kan inte spara");

        }
    }

    private void loadData(){
        String filename = "SaveData.json";
        FileInputStream inputStream;
        try{
            inputStream = openFileInput(filename);
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<Item>>(){}.getType();
            itemList = gson.fromJson(reader, collectionType);
            gson.fromJson(reader, collectionType);
            reader.close();
            Log.d("Laddat","Sparat");
        }catch (Exception e){

            Log.e("Kan inte ladda","Kan inte ladda");

        }
    }

}
