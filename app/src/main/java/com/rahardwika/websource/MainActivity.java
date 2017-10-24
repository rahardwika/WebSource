package com.rahardwika.websource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner sp;
    EditText inputteks;
    Button getSource;
    static TextView textresult;

    ConnectToInternet conn;
    CekKoneksi check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputMethodManager mng = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        check = new CekKoneksi(this);

        sp = (Spinner) findViewById(R.id.webspinner);
        inputteks = (EditText) findViewById(R.id.urlinput);
        getSource = (Button) findViewById(R.id.tombol);
        textresult = (TextView) findViewById(R.id.result);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jenis_web, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp.setAdapter(adapter);

    }

    public void klik(View view){
        if (check.isConnected()){
            if(inputteks.getText().toString().isEmpty()){
                Toast.makeText(this, "Please Fill The Form", Toast.LENGTH_SHORT).show();
            }
            else {
                conn = new ConnectToInternet(this);
                conn.execute(sp.getSelectedItem().toString()+inputteks.getText());
            }
        }
        else{
            Toast.makeText(MainActivity.this,"Check Your Connection and Again", Toast.LENGTH_SHORT).show();
        }
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getSource.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }
}
