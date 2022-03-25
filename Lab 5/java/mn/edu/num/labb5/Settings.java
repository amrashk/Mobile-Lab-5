package mn.edu.num.labb5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    RadioGroup rg;
    RadioButton see_eng_mon, mon_word, eng_word;
    Button btnSave, btnToBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rg = findViewById(R.id.radioGroup);
        see_eng_mon = findViewById(R.id.visible_eng_mon);
        eng_word = findViewById(R.id.visible_mon);
        mon_word = findViewById(R.id.visible_eng);
        btnSave = findViewById(R.id.save);
        btnToBack = findViewById(R.id.toBack);
        Intent in = getIntent();
        int intVM = in.getIntExtra("viewMode", 0);

        if(intVM == 1){
            eng_word.setChecked(true);
        }else if(intVM == 2){
            mon_word.setChecked(true);
        }else{
            see_eng_mon.setChecked(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.visible_mon: {
                        Intent in = new Intent();
                        in.putExtra("viewMode", 1);
                        setResult(RESULT_OK, in);
                        finish();
                        break;
                    }

                    case R.id.visible_eng : {
                        Intent in = new Intent();
                        in.putExtra("viewMode", 2);
                        setResult(RESULT_OK, in);
                        finish();
                        break;
                    }

                    case R.id.visible_eng_mon: default:{
                        Intent in = new Intent();
                        in.putExtra("viewMode",0);
                        setResult(RESULT_OK, in);
                        finish();
                        break;
                    }
                };
            }
        });

        btnToBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent();
                i.setClass(Settings.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}