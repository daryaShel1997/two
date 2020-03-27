package ru.daryas.two.retrofit;


import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.daryas.two.Gif.GifResponse;

public interface GifEndpoint {
    @GET("/v1/gifs/search")
    Single<GifResponse> search(@Query("api_key")String apiKey,    /*ключ*/
                             @Query("q") String search);        //строка поиска
}

