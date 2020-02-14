package ru.daryas.two;

import android.content.Intent;
import android.content.res.Configuration;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerActivity extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_padding);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
       TextView textView= findViewById(R.id.about);
       // recyclerView.addItemDecoration(new ItemOffsetDecoration(20));

        /*делаем 2 колонки*/
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        /*проверка ориентации*/
        /*если портретная, то передаем текущий*/
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        /*если альбомная, то передаем тот, где установлено 2 колонки*/
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            recyclerView.setLayoutManager(mLayoutManager);
        }


        recyclerView.setAdapter(new NewsListAdapter(this,
               DataUtils.generateNews()));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutActivity();
            }
        });
    }
    public void openAboutActivity()
    {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


}
