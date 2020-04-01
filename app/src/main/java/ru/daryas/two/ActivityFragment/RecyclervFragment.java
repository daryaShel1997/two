package ru.daryas.two.ActivityFragment;

import android.content.Intent;
import android.content.res.Configuration;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.daryas.two.DataUtils;
import ru.daryas.two.NewsListAdapter;
import ru.daryas.two.R;

public class RecyclervFragment extends Fragment {


    @Override

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_item_padding,
                container, false);



        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

       // recyclerView.addItemDecoration(new ItemOffsetDecoration(20));

        /*делаем 2 колонки*/
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);

        /*проверка ориентации*/
        /*если портретная, то передаем текущий*/
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        /*если альбомная, то передаем тот, где установлено 2 колонки*/
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            recyclerView.setLayoutManager(mLayoutManager);
        }


       recyclerView.setAdapter(new NewsListAdapter(getActivity(),
             DataUtils.generateNews()));
        return view;
    }



}
