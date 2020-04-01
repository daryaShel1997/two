package ru.daryas.two.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {



    private static final String URL = "https://api.giphy.com";
    public static final String KeyApi = "wpNNWxm7trWSzGu3r4fcxMVMRrsmr9t8";

   /*возврвщает HOST*/
    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()) //конвертирует json в объект
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
