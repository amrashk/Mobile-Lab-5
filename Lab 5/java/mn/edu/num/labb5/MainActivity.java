package mn.edu.num.labb5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    SharedPreferences settingFile;

    MyDatabaseHelper myDB;
    ArrayList<String> word_id, eng_word, mon_word;
    CustomAdapter customAdapter;
    int viewModeMain = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);



        settingFile = getSharedPreferences("appSettings", Context.MODE_PRIVATE);

        if(settingFile.getInt("viewMode", -1) == -1){
            SharedPreferences.Editor editor = settingFile.edit();
            editor.putInt("viewMode", 0);
            editor.commit();
        }else{
            viewModeMain = settingFile.getInt("viewMode", 0);
        }



        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        word_id = new ArrayList<>();
        eng_word = new ArrayList<>();
        mon_word = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,MainActivity.this, word_id, eng_word, mon_word);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else{
            while(cursor.moveToNext()){
                word_id.add(cursor.getString(0));
                eng_word.add(cursor.getString(1));
                mon_word .add(cursor.getString(2));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings: {
                Intent i = new Intent();
                i.setClass(MainActivity.this, Settings.class);
                i.putExtra("viewMode", viewModeMain);
                startActivityForResult(i, 3);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}