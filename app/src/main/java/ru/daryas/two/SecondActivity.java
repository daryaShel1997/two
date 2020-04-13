package ru.daryas.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        LinearLayout myLayout = findViewById(R.id.content);
        TextView tv = new TextView(this);
        tv.setText("Created by code!");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        tv.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // width
                ViewGroup.LayoutParams.MATCH_PARENT); // height
        linearLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        tv.setLayoutParams(linearLayoutParams);
        myLayout.addView(tv);
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
