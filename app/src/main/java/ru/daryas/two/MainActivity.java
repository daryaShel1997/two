package ru.daryas.two;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView etInputTexr;
private Button btnPreview;
private String inputText;
public static  final String KEY_STR ="KEY_STR";
    public static  final String TAG ="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputTexr = findViewById(R.id.input_text);
        btnPreview=  findViewById(R.id.preview);

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
            }
        });
    }

    public void openSecondActivity()
    {
        Intent scondActivityIntent = new Intent(this, SecondActivity.class);
        scondActivityIntent.putExtra(KEY_STR, etInputTexr.getText().toString());
        startActivity(scondActivityIntent);
    }
}
