package ru.daryas.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

private TextView etInputTexr;
private Button btnPreview;
ImageView imgVk, imgInst;
TextView textFullname, textDateOfBirth, textPlacOfBirth, textSecondaryEducation, textHigherEducation;
public static  final String KEY_STR ="KEY_STR";
    public static  final String TAG ="AboutActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        etInputTexr = findViewById(R.id.input_text);
        btnPreview=  findViewById(R.id.preview);


        textFullname  = findViewById(R.id.tvFullname);
        textDateOfBirth = findViewById(R.id.tvDateOfBirth);
        textPlacOfBirth  = findViewById(R.id.tvPlacOfBirth);
        textSecondaryEducation = findViewById(R.id.tvSecondaryEducation);
        textHigherEducation = findViewById(R.id.tvHigherEducation);

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
            }
        });


        imgVk=  findViewById(R.id.idVk);

        imgVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri address = Uri.parse("https://vk.com/id109839057");
                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);

                if (openLinkIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(openLinkIntent);
                } else {
                    Log.d("Intent", "Не получается обработать намерение!");
                }
            }
        });

        imgInst=  findViewById(R.id.idInst);
        imgInst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri address = Uri.parse("https://www.instagram.com/dashashel1997/");
                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);

                if (openLinkIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(openLinkIntent);
                } else {
                    Log.d("Intent", "Не получается обработать намерение!");
                }
            }
        });


        RelativeLayout myLayout = findViewById(R.id.content);

        TextView tv = new TextView(this);
        tv.setText("(c) 2019 Dasha");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // width
                ViewGroup.LayoutParams.WRAP_CONTENT); // height

        relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        tv.setLayoutParams(relativeLayoutParams);
        myLayout.addView(tv, relativeLayoutParams);
    }

    public void openSecondActivity()
    {
        Intent scondActivityIntent = new Intent(this, SecondActivity.class);
        scondActivityIntent.putExtra(KEY_STR, etInputTexr.getText().toString());
        startActivity(scondActivityIntent);
    }
}
