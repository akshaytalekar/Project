package com.example.project.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;

import java.util.ArrayList;
import java.util.HashMap;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context mContext;
    ArrayList<HashMap<String, String>> mArray;
    public MyAdapter(Context context, ArrayList<HashMap<String, String>> mArray) {
        this.mContext = context;
        this.mArray = mArray;

    }public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        TextView tv1,tv2,tv3;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView3);
            tv1 = v.findViewById(R.id.tv1);
            tv2 = v.findViewById(R.id.tv2);
            tv3 = v.findViewById(R.id.tv3);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content, parent, false);
        MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(mRowView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String, String> map = mArray.get(position);
        Glide.with(mContext).load(map.get("imgUrl")).into(holder.imageView);
        holder.tv1.setText(map.get("title"));
        holder.tv2.setText(map.get("Rate"));
        holder.tv3.setText(map.get("location"));

    }



    @Override
    public int getItemCount() {
        return mArray.size();
    }
}