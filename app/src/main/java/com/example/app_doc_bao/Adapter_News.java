package com.example.app_doc_bao;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class Adapter_News extends RecyclerView.Adapter<Adapter_News.MyHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<News> newsList;
    public static String DOMAIN="https://android-review-food.000webhostapp.com/"  ;


    public Adapter_News(Context context, List<News> newsList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.list_news,viewGroup,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        News news = newsList.get(i);
        if (!TextUtils.isEmpty(news.getAnh_Interface())) {
            Picasso.with(context).load(DOMAIN+news.getAnh_Interface()).into(myHolder.anhchinh);
              Log.e("True ","Co anh ");
        } else {
            Log.e("Err anh", "Khong co anh");
        }


        myHolder.tvTenTin.setText(news.getTenTin_Interface());
        Format fm = new SimpleDateFormat("yyyy-MM-dd");
        myHolder.tvTime.setText(fm.format(news.getNgay()));



    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView anhchinh,anh1,anh2,anh3;
        TextView tvTenTin,tvNd1,tvNd2,tvNd3,tv_idTin;
        TextView tvTime;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            anhchinh=itemView.findViewById(R.id.ivAnh);
            tvTenTin=itemView.findViewById(R.id.tvTen);
            tvTime=itemView.findViewById(R.id.tvNgay);
            anh1=itemView.findViewById(R.id.tvanh1);
            tvNd1=itemView.findViewById(R.id.tvMoTa1);
            anh2=itemView.findViewById(R.id.tvanh2);
            tvNd2=itemView.findViewById(R.id.tvMoTa2);
            anh3=itemView.findViewById(R.id.tvanh3);
            tvNd3=itemView.findViewById(R.id.tvMoTa3);
            tv_idTin=itemView.findViewById(R.id.tvChuDe);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(ct,"a",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, InforNewsActivity.class);
                    // Gui du lieu
                    int vitri = getAdapterPosition();
                    i.putExtra("anhChinh",newsList.get(vitri).getAnh_Interface());
                    i.putExtra("tenTin",newsList.get(vitri).getTenTin_Interface());
                    i.putExtra("ngay",newsList.get(vitri).getNgay());
                    i.putExtra("anh1",newsList.get(vitri).getAnh1());
                    i.putExtra("moTa1",newsList.get(vitri).getNoiDung1());
                    i.putExtra("anh2",newsList.get(vitri).getAnh2());
                    i.putExtra("moTa2",newsList.get(vitri).getNoiDung2());
                    i.putExtra("anh3",newsList.get(vitri).getAnh3());
                    i.putExtra("moTa3",newsList.get(vitri).getNoiDung3());
                    context.startActivity(i);
                }
            });


        }
    }
}
