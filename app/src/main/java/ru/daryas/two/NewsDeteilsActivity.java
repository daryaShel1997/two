package ru.daryas.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsDeteilsActivity extends AppCompatActivity {
    public static  final String KEY_SECTION ="KEY_SECTION";
    public static  final String KEY_TITLE ="KEY_TITLE";
    public static  final String KEY_FULLTEXT ="KEY_FULLTEXT";
    public static  final String KEY_DATE ="KEY_DATE";
    public static  final String KEY_AVATAR ="KEY_AVATAR";

  TextView tvSection;
     TextView tvTitle;
     TextView tvText;
    TextView tvDate;
     ImageView ivAvatar;
     int position;
    public  String giveTextt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_deteils);
        tvTitle = findViewById(R.id.title);
        tvText = findViewById(R.id.text);
        tvDate = findViewById(R.id.date);
        ivAvatar = findViewById(R.id.avatar);



        giveTextt = getIntent().getStringExtra("KEY_SECTION");
        setTitle(giveTextt);  //установка ToolBar

        giveTextt = getIntent().getStringExtra("KEY_TITLE");
        tvTitle.setText(giveTextt);
        giveTextt = getIntent().getStringExtra("KEY_FULLTEXT");
        tvText.setText(giveTextt);
        giveTextt = getIntent().getStringExtra("KEY_DATE");
        tvDate.setText(giveTextt);

      /*  Bundle extras = getIntent().getExtras();
        Bitmap bmp = extras.getParcelable("KEY_AVATAR");
        ivAvatar.setImageBitmap(bmp );*/
        giveTextt = getIntent().getStringExtra("KEY_AVATAR");
      //  ivAvatar.set(giveTextt );

        Glide.with(this).load(giveTextt).into(ivAvatar);


    }
}
