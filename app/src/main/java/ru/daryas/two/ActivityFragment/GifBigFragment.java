package ru.daryas.two.ActivityFragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;

import ru.daryas.two.R;

public class GifBigFragment extends Fragment {
    ImageView img;
    private String  url;
    private MessageFragmentListener listener;


    public static GifBigFragment newInstance(String  url) {
        GifBigFragment fragment = new GifBigFragment();
        Bundle args = new Bundle();
        args.putString("utl", url);

        fragment.setArguments(args);
        return fragment;
    }


    @Override

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gif_big_fragment,
                container, false);

        if (getArguments() != null) {
            url = getArguments().getString("utl");

        }

        img = view.findViewById(R.id.idtx);
//        String message = getArguments().getString("position");
//        textView.setText(url);
//
//        Toast toast = Toast.makeText(getActivity(), "Element " +url, Toast.LENGTH_SHORT);
//        toast.show();


        Glide.with(this).load(url).into(img);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof MessageFragmentListener) {
            listener = (MessageFragmentListener) getActivity();
        }
    }
}
