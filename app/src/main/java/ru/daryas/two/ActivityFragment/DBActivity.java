package ru.daryas.two.ActivityFragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.ResourceSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.daryas.two.DB.App;
import ru.daryas.two.DB.AppDatabase;
import ru.daryas.two.DB.DBListAdapter;
import ru.daryas.two.DB.Employee;
import ru.daryas.two.DB.EmployeeDao;
import ru.daryas.two.Gif.GifResponse;
import ru.daryas.two.Gif.GifListAdapter;
import ru.daryas.two.R;
import ru.daryas.two.retrofit.GifEndpoint;
import ru.daryas.two.retrofit.RetrofitClient;



import static ru.daryas.two.retrofit.RetrofitClient.KeyApi;


public class DBActivity extends Fragment implements MessageFragmentListener {

    private EditText etSearch;
    private Button btnSearch;
    RecyclerView recyclerView;
    private MessageFragmentListener listener;

    final static String TAG_1 = "fragment1";
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_gif,            container, false);

        etSearch = view.findViewById(R.id.search_line);
        btnSearch = view.findViewById(R.id.search_button);
        //img = findViewById(R.id.qwn);


        recyclerView = view.findViewById(R.id.recycler_view2);

        /*делаем 2 колонки*/
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        /*проверка ориентации*/
        /*если портретная, то передаем текущий*/
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        /*если альбомная, то передаем тот, где установлено 2 колонки*/
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(mLayoutManager);
        }
        if (getActivity() instanceof MessageFragmentListener) {
            listener = (MessageFragmentListener) getActivity();
        }


        listener = this::onNextMessageClicked;
        /*собираем url*/
        GifEndpoint gifEndpoint = RetrofitClient.getRetrofit().create(GifEndpoint.class);
        gifEndpoint.search(KeyApi, "hello")
                .subscribeOn(Schedulers.io()) //подписываемся на другой поток
                .observeOn(AndroidSchedulers.mainThread()) //к главному
                .subscribe(new ResourceSingleObserver<GifResponse>() {
                    @Override
                    public void onSuccess(GifResponse gifResponse) {


                        AppDatabase db = App.getInstance().getDatabase();
                        EmployeeDao employeeDao = db.employeeDao();

                        Employee employee = new Employee();

                        List<Employee> employeess = employeeDao.getAll();
                        employeeDao.delete(employeess);


                        employee.id = 1;
                        employee.name = "Amy Winehouse";
                        employee.salary = 10000;

                        employeeDao.insert(employee);


                        employee.id = 2;
                        employee.name = "Freddy Mercury";
                        employee.salary = 30000;
                        //                        URL imageurl = new URL("https://i.pinimg.com/originals/93/d2/85/93d285ff593476eb3ceda8b80313fa9f.jpg");
//                        Bitmap bitmap = BitmapFactory.decodeStream(imageurl.openConnection().getInputStream());
                        //   employee.img = bitmap;
                        employeeDao.insert(employee);


                        List<Employee> employees = employeeDao.getAll();

                  //      recyclerView.setAdapter(new GifListAdapter(getActivity(), gifResponse.getData(), listener));
                          recyclerView.setAdapter(new DBListAdapter(getActivity(), employees));

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
                                recyclerView.setAdapter(new GifListAdapter(getActivity(), gifResponse.getData(), listener));
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

        return view;

    }

    public void onNextMessageClicked(String url) {

//        Toast toast = Toast.makeText(getActivity(), "Element " + position, Toast.LENGTH_SHORT);
//        toast.show();


        GifBigFragment frag1 = GifBigFragment.newInstance(url);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, frag1, TAG_1)
                .addToBackStack(null)
                .commit();
    }

}
