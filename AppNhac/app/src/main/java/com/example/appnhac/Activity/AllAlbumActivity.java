package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appnhac.Adapter.AllAlbumAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumActivity extends AppCompatActivity {
    RecyclerView rccallAlbum;
    AllAlbumAdapter allAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_album );

        rccallAlbum=findViewById( R.id.rccallalbum );
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> albumCall=dataservice.GetAllAlbum();
        albumCall.enqueue( new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>) response.body();
                allAlbumAdapter=new AllAlbumAdapter( AllAlbumActivity.this,albumArrayList );
                rccallAlbum.setLayoutManager( new GridLayoutManager(  AllAlbumActivity.this,3) );

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        } );
    }
}
