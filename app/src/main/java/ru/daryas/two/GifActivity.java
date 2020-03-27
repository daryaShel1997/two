package ru.daryas.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.ResourceSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.daryas.two.Gif.GifDTO;
import ru.daryas.two.Gif.GifResponse;
import ru.daryas.two.List.GifListAdapter;
import ru.daryas.two.retrofit.GifEndpoint;
import ru.daryas.two.retrofit.RetrofitClient;

import static ru.daryas.two.retrofit.RetrofitClient.KeyApi;


public class GifActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnSearch;
    RecyclerView recyclerView;
    ImageView img;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        etSearch = findViewById(R.id.search_line);
        btnSearch = findViewById(R.id.search_button);
        //img = findViewById(R.id.qwn);

        recyclerView = findViewById(R.id.recycler_view2);

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

        /*собираем url*/
        GifEndpoint gifEndpoint = RetrofitClient.getRetrofit().create(GifEndpoint.class);
        gifEndpoint.search(KeyApi, "hello")
                .subscribeOn(Schedulers.io()) //подписываемся на другой поток
                .observeOn(AndroidSchedulers.mainThread()) //к главному
                .subscribe(new ResourceSingleObserver<GifResponse>() {
                    @Override
                    public void onSuccess(GifResponse gifResponse) {

                       recyclerView.setAdapter(new GifListAdapter(GifActivity.this, gifResponse.getData()));

//                        Glide.with(GifActivity.this)
//                               .load(gifResponse.getData().get(0).getUrl())
//                                .into(img);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

            btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GifEndpoint gifEndpoint = RetrofitClient.getRetrofit().create(GifEndpoint.class);
                gifEndpoint.search(KeyApi, etSearch.getText().toString())
                        .subscribeOn(Schedulers.io()) //подписываемся на другой поток
                        .observeOn(AndroidSchedulers.mainThread()) //к главному
                        .subscribe(new ResourceSingleObserver<GifResponse>() {
                            @Override
                            public void onSuccess(GifResponse gifResponse) {
                                recyclerView.setAdapter(new GifListAdapter(GifActivity.this, gifResponse.getData()));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });

//        gifEndpoint.search(KeyApi, "hello").enqueue(new Callback<GifResponse>() {
//            @Override
//            public void onResponse(Call<GifResponse> call, Response<GifResponse> response) {
//               Log.e("key",response.body().getData().toString());
//            }
//
//            @Override
//            public void onFailure(Call<GifResponse> call, Throwable t) {
//
//            }
//        });

//                .enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Gson gson = new Gson();
//                String gsonResponse = response.body().string();
//                GifResponse gifResponse = gson.fromJson(gsonResponse, GifResponse.class);
//
//                gifResponse.getData().get(0).getUrl(); //отдаю в список в адаптер
//                System.out.println(gsonResponse);
//            }
//        });


    }

}
