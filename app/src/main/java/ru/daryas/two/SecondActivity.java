package ru.daryas.two;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    public static  final String KEY_STR ="KEY_STR";
    public  String giveTextt;
    String addresses[] = {"dashashel1997@mail.ru", "palma65@mail.ru"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView getText = findViewById(R.id.getText);
        giveTextt = getIntent().getStringExtra("KEY_STR");
       getText.setText(giveTextt);

        Button btnEmail=  findViewById(R.id.email);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    composeEmail(addresses, giveTextt);
                }
                catch(Exception ex)
                {
                   // Toast.makeText(this, "hhh", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void composeEmail(String[] addresses, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);    //получатели
        intent.putExtra(Intent.EXTRA_TEXT, text);    //тема
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
