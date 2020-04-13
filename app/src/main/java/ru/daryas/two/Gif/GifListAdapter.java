package ru.daryas.two.Gif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;


import ru.daryas.two.ActivityFragment.MessageFragmentListener;

import ru.daryas.two.R;


public class GifListAdapter
        extends RecyclerView.Adapter<GifListAdapter.ViewHolder> {

    private MessageFragmentListener mClickListener;


    final static String TAG_1 = "fragment1";
    public static List<GifDTO> gifff;


    public static Context context;
    private final LayoutInflater inflater;
    public static final String KEY_AVATAR = "KEY_AVATAR";
    public static final String KEY_USER = "KEY_USER";

    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;



    public GifListAdapter(Context context, List<GifDTO> Gif, MessageFragmentListener clickListener) {
        this.gifff = Gif;
        this.context = context;
        inflater = LayoutInflater.from(context);
        mClickListener = clickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView tvAvatar;
        private final CardView cvCard;

        private ViewHolder(View itemView) {
            super(itemView);
            tvAvatar = itemView.findViewById(R.id.avatar);
            cvCard = itemView.findViewById(R.id.card_view_gif);

//            cvCard.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//
//                }
//            });

        }
    }

    @Override
    public int getItemCount() {
        return gifff.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);


                Glide.with(context)
                        .load(gifff.get(position).getUrl())
                        .into(holder.tvAvatar);
                holder.cvCard.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (mClickListener != null) {
                            mClickListener.onNextMessageClicked(gifff.get(position).getUrl());

                        }
                    }
                });
      }

    @Override
    public int getItemViewType(int position) {
        // условие для определения айтем какого типа выводить в конкретной позиции
        if (position == gifff.size() - 1) return TYPE_ITEM2;
        return TYPE_ITEM1;
    }

}