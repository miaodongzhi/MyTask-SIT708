package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    Spinner sp;
    EditText input_data;
    TextView display_data;
    String names[] = {"Inch", "Foot", "Yard", "Mile", "Pound", "Ounce", "Ton", "C to F", "F to C", "C to K", "K to C"};
    ArrayAdapter <String> adapter;

    Double data = 0.0;

    String select = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display_data = findViewById(R.id.output);
        input_data = findViewById(R.id.input);

        sp = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = Double.valueOf(input_data.getText().toString());
                select = sp.getSelectedItem().toString();

                if(select == "Inch"){
                    data = data * 2.54;
                    display_data.setText(data.toString() + " cm");
                }

                if(select == "Foot"){
                    data = data * 30.48;
                    display_data.setText(data.toString() + " cm");
                }

                if(select == "Yard"){
                    data = data * 91.44;
                    display_data.setText(data.toString() + " cm");
                }

                if(select == "Mile"){
                    data = data * 1.60394;
                    display_data.setText(data.toString() + " km");
                }

                if(select == "Pound"){
                    data = data * 0.453592;
                    display_data.setText(data.toString() + " kg");
                }

                if(select == "Ounce"){
                    data = data * 28.3495;
                    display_data.setText(data.toString() + " g");
                }

                if(select == "Ton"){
                    data = data * 907.185;
                    display_data.setText(data.toString() + " kg");
                }

                if(select == "C to F"){
                    data =  (data  * 1.8) + 32;
                    display_data.setText(data.toString());
                }

                if(select == "F to C"){
                    data =  (data - 32) / 1.8;
                    display_data.setText(data.toString());
                }

                if(select == "C to K"){
                    data =  data + 273.15;
                    display_data.setText(data.toString());
                }

                if(select == "K to C"){
                    data =  data - 273.15;
                    display_data.setText(data.toString());
                }



            }
        });

    }

}





