package com.example.photogallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.photogallery.api.FlickrAPI;
import com.example.photogallery.api.ServiceAPI;
import com.example.photogallery.model.Photo;
import com.example.photogallery.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class PhotoGallery extends AppCompatActivity {

    PhotoAdapter adapter;
    RecyclerView r_view;
    Response respon;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        r_view = findViewById(R.id.r_view);
        r_view.setLayoutManager(new GridLayoutManager(this,3));
        context = this;
        Retrofit retrofit = ServiceAPI.getRetrofit();
        retrofit.create(FlickrAPI.class) .getRecent().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                respon = response.body();
                List<Photo> ph = respon.getPhotos().getPhoto();
                adapter = new PhotoAdapter(ph,context);
                r_view.setAdapter(adapter);

                Toast.makeText(PhotoGallery.this, "GOOD REQUEST",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(PhotoGallery.this, "BAD REQUEST",Toast.LENGTH_SHORT).show();
            }
        });
    }
}