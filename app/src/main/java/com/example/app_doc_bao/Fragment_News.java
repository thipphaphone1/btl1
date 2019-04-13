package com.example.app_doc_bao;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_News extends Fragment {
    private RecyclerView recyclerView;
    private List<News> listNews = new ArrayList<>();
    private  Adapter_News adapterNews;
    Date date=null;

    public static Fragment_News newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("id",id);
        Fragment_News fragment = new Fragment_News();
        fragment.setArguments(args);
        return fragment;
    }

private  int idGR;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idGR = getArguments().getInt("id");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);

        recyclerView=view.findViewById(R.id.recyclerView);
        Format fm = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr="2011/03/07";
        try {
            date=((SimpleDateFormat) fm).parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }

        loadData();

        adapterNews= new Adapter_News(getContext(),listNews);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNews);
        return view;

    }

    private void loadData() {

        // Su dung API de load du lieu tren mang.
        Api.getRetrofit().create(Api.Data_Service.class)
                .getNewList("t",idGR)
                .enqueue(new Callback<News_ListRespose>() {
                    @Override
                    public void onResponse(Call<News_ListRespose> call, Response<News_ListRespose> response) {
                        //
                        if (response.isSuccessful()){
//                                    try {
//                                        Log.e("getCooking List",response.body().string());
//                                    } catch (IOException e) {
//                                        Log.e("getCooking List Err0 ",e.getMessage());
//                                        e.printStackTrace();
//                                    }
                            listNews.clear();
                            listNews.addAll(response.body().getNewsList());
                            adapterNews.notifyDataSetChanged();
                        }else {
                            Log.e("getNew Error 1",response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<News_ListRespose> call, Throwable throwable) {
                        Log.e("getNew False Erro 2", throwable.getMessage());
                        throwable.printStackTrace();
                    }
                });

    }
}
