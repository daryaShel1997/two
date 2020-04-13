package ru.daryas.two.ActivityFragment;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.daryas.two.R;
import ru.daryas.two.SecondActivity;

public class AboutFragment extends Fragment {
    private TextView etInputTexr;
    private Button btnPreview;
    ImageView imgVk, imgInst;
    TextView textFullname, textDateOfBirth, textPlacOfBirth, textSecondaryEducation, textHigherEducation;
    public static  final String KEY_STR ="KEY_STR";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_about,
                container, false);


        etInputTexr = view.findViewById(R.id.input_text);
        btnPreview=  view.findViewById(R.id.preview);


        textFullname  = view.findViewById(R.id.tvFullname);
        textDateOfBirth = view.findViewById(R.id.tvDateOfBirth);
        textPlacOfBirth  = view.findViewById(R.id.tvPlacOfBirth);
        textSecondaryEducation = view.findViewById(R.id.tvSecondaryEducation);
        textHigherEducation = view.findViewById(R.id.tvHigherEducation);

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scondActivityIntent = new Intent(getActivity(), SecondActivity.class);
                scondActivityIntent.putExtra(KEY_STR, etInputTexr.getText().toString());
                startActivity(scondActivityIntent);


            }
        });


        imgVk=  view.findViewById(R.id.idVk);

        imgVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri address = Uri.parse("https://vk.com/id109839057");
                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
                try {
                    startActivity(openLinkIntent);
                }
                catch(Exception e) {
                    Log.d("Intent", "Не получается обработать намерение!");
                }
                }
        });
//         if (openLinkIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(openLinkIntent);
//                } else {
//                    Log.d("Intent", "Не получается обработать намерение!");
//                }
//            }
//        });

        imgInst=  view.findViewById(R.id.idInst);
        imgInst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri address = Uri.parse("https://www.instagram.com/dashashel1997/");
                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
                try {
                    startActivity(openLinkIntent);
                }
                catch(Exception e) {
                    Log.d("Intent", "Не получается обработать намерение!");
                }
            }
        });


        RelativeLayout myLayout = view.findViewById(R.id.content);

        TextView tv = new TextView(getActivity());
        tv.setText("(c) 2019 Dasha");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // width
                ViewGroup.LayoutParams.WRAP_CONTENT); // height

        relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        tv.setLayoutParams(relativeLayoutParams);
        myLayout.addView(tv, relativeLayoutParams);


        return view;
    }

}
