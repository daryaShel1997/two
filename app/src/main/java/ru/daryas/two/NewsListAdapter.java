package ru.daryas.two;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsListAdapter
        extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    public static List<NewsItem> news;
    public static Context context;
    private final LayoutInflater inflater;

    public static  final String KEY_SECTION ="KEY_SECTION";
    public static  final String KEY_TITLE ="KEY_TITLE";
    public static  final String KEY_FULLTEXT ="KEY_FULLTEXT";
    public static  final String KEY_DATE ="KEY_DATE";
    public static  final String KEY_AVATAR ="KEY_AVATAR";

    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;

    public NewsListAdapter(Context context, List<NewsItem> News) {
        this.news = News;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSection;
        private final TextView tvTitle;
        private final TextView tvText;
        private final TextView tvDate;
        private final ImageView tvAvatar;
        private final CardView cvCard;
        String strFullText;

        private ViewHolder(View itemView) {
            super(itemView);
            tvSection = itemView.findViewById(R.id.section);
            tvTitle = itemView.findViewById(R.id.title);
            tvText = itemView.findViewById(R.id.text);
            tvDate = itemView.findViewById(R.id.date);
            tvAvatar = itemView.findViewById(R.id.avatar);
            cvCard = itemView.findViewById(R.id.card_view);

            cvCard.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                // получаем позицию в адаптере, которой соответсвует холдер
                    int positionIndex = getAdapterPosition();

                    Toast toast = Toast.makeText(context, "Element " + positionIndex, Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent(context, NewsDeteilsActivity.class);

                    intent.putExtra(KEY_SECTION, tvSection.getText().toString());
                    intent.putExtra(KEY_TITLE, tvTitle.getText().toString());

                    intent.putExtra(KEY_FULLTEXT, news.get(positionIndex).getFullText());

                    intent.putExtra(KEY_DATE, tvDate.getText().toString());

                    /*tvAvatar.buildDrawingCache();
                    Bitmap image= tvAvatar.getDrawingCache();
                    Bundle extras = new Bundle();
                    extras.putParcelable(KEY_AVATAR, image);
                    intent.putExtras(extras);*/

                    intent.putExtra(KEY_AVATAR, news.get(positionIndex).getImageUrl());


                    context.startActivity(intent);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return news.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);

        switch (viewType) {
            // инфлейтим нужную разметку в зависимости от того,
            // какой тип айтема нужен в данной позиции

            case TYPE_ITEM1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
                break;
            case TYPE_ITEM2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news_text, parent, false);
        }
        return new ViewHolder(view);




      /*  return new ViewHolder(
                inflater.inflate(R.layout.list_item_news, parent, false)
        );*/
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

// get our custom object from our dataset at this position
        NewsItem news_ = news.get(position);

        int type = getItemViewType(position);

        /*для данного случая swech не нужен, т.к. одинаковые view*/
        switch (type) {
            case TYPE_ITEM1:
                holder.tvSection.setText(news_.getSection());
                holder.tvTitle.setText(news_.getTitle());
                holder.tvText.setText(news_.getPreviewText());
                holder.tvDate.setText(news_.getPublishDate());
                Glide.with(context).load(news_.getImageUrl()).into(holder.tvAvatar);
                break;
            case TYPE_ITEM2:
                holder.tvSection.setText(news_.getSection());
                holder.tvTitle.setText(news_.getTitle());
                holder.tvText.setText(news_.getPreviewText());
                holder.tvDate.setText(news_.getPublishDate());
                Glide.with(context).load(news_.getImageUrl()).into(holder.tvAvatar);
                break;
        }

// Fill views with our data


    }
    @Override
    public int getItemViewType(int position) {
        // условие для определения айтем какого типа выводить в конкретной позиции
        if (position  % 2 != 0) return TYPE_ITEM2;
        return TYPE_ITEM1;
    }

    }