package ru.daryas.two.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

import ru.daryas.two.Gif.GifDTO;

import ru.daryas.two.GifActivity;
import ru.daryas.two.R;
import ru.daryas.two.RecyclerActivity;
import ru.daryas.two.SecondActivity;

public class GifListAdapter
        extends RecyclerView.Adapter<GifListAdapter.ViewHolder> {

    public static List<GifDTO> gifff;


    public static Context context;
    private final LayoutInflater inflater;
    public static final String KEY_AVATAR = "KEY_AVATAR";
    public static final String KEY_USER = "KEY_USER";

    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;

    public GifListAdapter(Context context, List<GifDTO> Gif) {
        this.gifff = Gif;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView tvAvatar;
        private final Button btnBeck;
        private final CardView cvCard;

        private ViewHolder(View itemView) {
            super(itemView);
            tvAvatar = itemView.findViewById(R.id.avatar);
            btnBeck = itemView.findViewById(R.id.all_app);
            cvCard = itemView.findViewById(R.id.card_view_gif);

//            btnBeck.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(context, RecyclerActivity.class);
//                        context.startActivity(intent);
//                    }
//                });

        }
    }

    @Override
    public int getItemCount() {
        return gifff.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif, parent, false);;


        switch (viewType) {
            // инфлейтим нужную разметку в зависимости от того,
            // какой тип айтема нужен в данной позиции

            case TYPE_ITEM1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif, parent, false);
                break;
            case TYPE_ITEM2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_button, parent, false);


        }return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        int type = getItemViewType(position);

         switch (type) {
            case TYPE_ITEM1:
                Glide.with(context)
                        .load(gifff.get(position).getUrl())
                        .into(holder.tvAvatar);

                break;
            case TYPE_ITEM2:
                holder.btnBeck.setText("Текст");
                holder.btnBeck.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, RecyclerActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        // условие для определения айтем какого типа выводить в конкретной позиции
        if (position == gifff.size() - 1) return TYPE_ITEM2;
        return TYPE_ITEM1;
    }
}