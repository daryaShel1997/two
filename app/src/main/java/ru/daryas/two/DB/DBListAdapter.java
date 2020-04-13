package ru.daryas.two.DB;

import android.content.Context;
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

import ru.daryas.two.ActivityFragment.MessageFragmentListener;
import ru.daryas.two.Gif.GifDTO;
import ru.daryas.two.R;


public class DBListAdapter
        extends RecyclerView.Adapter<DBListAdapter.ViewHolder> {

    private MessageFragmentListener mClickListener;


    public static List<Employee> emp;


    public static Context context;
    private final LayoutInflater inflater;
    public static final String KEY_AVATAR = "KEY_AVATAR";
    public static final String KEY_USER = "KEY_USER";

    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;



    public DBListAdapter(Context context, List<Employee> Emp) {
        this.emp = Emp;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cvCard;
        private final TextView tvId, tvName, tvSalary;
        private final ImageView imgPhoto;


        private ViewHolder(View itemView) {
            super(itemView);
                cvCard = itemView.findViewById(R.id.card_view_db);
                tvId= itemView.findViewById(R.id.emp_id);
                tvName= itemView.findViewById(R.id.emp_name);
                tvSalary= itemView.findViewById(R.id.emp_salary);
                imgPhoto=itemView.findViewById(R.id.emp_img);

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
        return emp.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_db, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);
        Employee emp_ = emp.get(position);

//
        holder.tvId.setText(String.valueOf(emp_.getId()));
        holder.tvName.setText(emp_.getName());
       holder.tvSalary.setText(String.valueOf(emp_.getSalary()));
        holder.imgPhoto.setImageBitmap(emp_.getImage());


//
//                holder.cvCard.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        Toast toast = Toast.makeText(context, "Element ", Toast.LENGTH_SHORT);
//                       toast.show();
//                    }
//                });
      }

    @Override
    public int getItemViewType(int position) {
        // условие для определения айтем какого типа выводить в конкретной позиции
        if (position == emp.size() - 1) return TYPE_ITEM2;
        return TYPE_ITEM1;
    }

}