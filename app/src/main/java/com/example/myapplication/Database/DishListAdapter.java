package com.example.myapplication.Database;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapplication.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import java.util.List;

import static android.util.Log.*;

public class DishListAdapter extends RecyclerView.Adapter<DishListAdapter.DishViewHolder>{
    private List<Dish> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnDishItemClick onDishItemClick;

    public DishListAdapter(List<Dish> list, Context context){
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.onDishItemClick = (OnDishItemClick) context;
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position){
        final int bind = e("bind", "onBindViewHolder: " + list.get(position));
        /*if (list != null){
            holder.getTextViewName().setText(list.get(position).getDish());
            holder.getTextViewType().setText(list.get(position).getType());
        } else {
            //covers the case of data not being ready yet
            holder.getTextViewName().setText("No dish");
        }*/
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = layoutInflater.inflate(R.layout.recylcerview_layout,parent,false);
        return new DishViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewName;
        private TextView textViewType;
        @SuppressLint("WrongViewCast")
        public DishViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            setTextViewName((TextView) itemView.findViewById(R.id.recipe_name));
            setTextViewType((TextView) itemView.findViewById(R.id.recipe_type));
        }

        @Override
        public void onClick(View view){
            onDishItemClick.onDishClick(getAdapterPosition());
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public void setTextViewName(TextView textViewName) {
            this.textViewName = textViewName;
        }

        public TextView getTextViewType() {
            return textViewType;
        }

        public void setTextViewType(TextView textViewType) {
            this.textViewType = textViewType;
        }
    }

        public interface OnDishItemClick{
            void onDishClick(int pos);
        }
    }




/*
     class DishViewHolder extends RecyclerView.ViewHolder{
     ///   private final TextView dishItemView;

      ///  private DishViewHolder(View itemView){
       ///     super(itemView);
       ///     dishItemView = itemView.findViewById(R.id.textView);}}
    private final LayoutInflater mInflater;
    private List<Dish> mDishes;

    public DishListAdapter(Context context){mInflater = LayoutInflater.from(context);}

    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recylcerview_layout, parent, false);
        DishViewHolder holder = new DishViewHolder(itemView){
            @Override
            public String toString(){
                return super.toString();
            }
        }; return holder;}

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position){
        if (mDishes != null){
            Dish current = mDishes.get(position);
            holder.dishItemView.setText(current.getDish());
        } else {
            //covers the case of data not being ready yet
            holder.dishItemView.setText("No dish");
        }
    }

    public void setDishes(List<Dish> dishes){
        this.mDishes = dishes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if (mDishes != null)
            return mDishes.size();
        else return 0;
    } */
