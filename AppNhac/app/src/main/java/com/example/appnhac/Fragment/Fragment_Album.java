package com.example.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appnhac.Activity.AllAlbumActivity;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album extends Fragment {


    public static Fragment_Album newInstance() {
        return new Fragment_Album();
    }
    RecyclerView recyclerView;
    TextView tvxemthemalbum;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate( R.layout.fragment__album, container, false );
        recyclerView=view.findViewById( R.id.recycleViewAlbum );
        tvxemthemalbum=view.findViewById( R.id.tvxemthemalbum );
        tvxemthemalbum.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( getActivity(), AllAlbumActivity.class );
                startActivity( intent );
            }
        } );
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> callalbum=dataservice.GetAlbum();
        callalbum.enqueue( new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>) response.body();

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        } );
    }


}
