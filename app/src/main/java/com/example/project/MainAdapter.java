package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.ui.home.HomeFragment;

public class MainAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflator;
    private String[] homeWord;
    private String[] homerate;
    private int[] homeImage;
    private String[] homelocation;



   /* public MainAdapter(Context context, int[] homeImage, String[] homeWord, String[] homerate, String[] homelocation) {
        this.context=context;
        this.homeImage=homeImage;
        this.homeWord=homeWord;
        this.homerate=homerate;
        this.homelocation=homelocation;
    }*/

    public MainAdapter(HomeFragment homeFragment, int[] homeImage, String[] homeWord, String[] homerate, String[] homelocation) {
        this.context=context;
        this.homeImage=homeImage;
        this.homeWord=homeWord;
        this.homerate=homerate;
        this.homelocation=homelocation;
    }

    @Override
    public int getCount() {
        return homeWord.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflator==null)
        {
            inflator=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null)
        {
            convertView=inflator.inflate(R.layout.row_content,null);
        }
        ImageView imageView=convertView.findViewById(R.id.imageView3);
        TextView textView=convertView.findViewById(R.id.tv1);
        TextView textView1=convertView.findViewById(R.id.tv2);
        TextView textView2=convertView.findViewById(R.id.tv3);


        imageView.setImageResource(homeImage[position]);
        textView.setText(homeWord[position]);
        textView1.setText(homerate[position]);
        textView2 .setText(homelocation[position]);

        return convertView;
    }
}