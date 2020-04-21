package com.example.project.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.MainActivity;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    RecyclerView mRecyclerview;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    ArrayList<HashMap<String, String>> arrayListNews;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v2 = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerview = v2.findViewById(R.id.recycler);
        mLayoutManager=new LinearLayoutManager(getContext());
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerview.setLayoutManager(mLayoutManager);
        callAPI();
        return v2;
    }

    private void callAPI() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url = "https://babyandroid.000webhostapp.com/property/home.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("=====", "=========response:" + response);
                        parseAPIResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void parseAPIResponse(String response) {
        try {
            JSONObject objResponse = new JSONObject(response);
            JSONArray arrayHeadlines = objResponse.getJSONArray("Offers");
            arrayListNews = new ArrayList<>();

            for (int i = 0; i < arrayHeadlines.length(); i++) {
                JSONObject objItem = arrayHeadlines.getJSONObject(i);
                String imgUrl = objItem.getString("imgUrl");
                String title = objItem.getString("title");
                String Rate = objItem.getString("Rate");
                String location = objItem.getString("location");

                HashMap<String, String> map = new HashMap<>();

                map.put("imgUrl", imgUrl);
                map.put("title", title);
                map.put("Rate", Rate);
                map.put("location", location);
                arrayListNews.add(map);
            }
            //set adapter
            mAdapter = new MyAdapter(getContext(), arrayListNews);
            mRecyclerview.setAdapter(mAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}