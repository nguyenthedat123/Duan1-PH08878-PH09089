package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.appnhac.Adapter.DSALLChudeAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {
    RecyclerView recyclerViewallchude;
    DSALLChudeAdapter dsallChudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_danhsachtatcachude );
        recyclerViewallchude=findViewById( R.id.rccallchude );
        GatData();
    }

    private void GatData() {
        Dataservice dataservice= APIService.getService();
        Call<List<ChuDe>> callback=dataservice.GetAllChuDe();
        callback.enqueue( new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList= (ArrayList<ChuDe>) response.body();
                dsallChudeAdapter=new DSALLChudeAdapter( DanhsachtatcachudeActivity.this,chuDeArrayList );
                recyclerViewallchude.setLayoutManager( new GridLayoutManager( DanhsachtatcachudeActivity.this ,1) );
                recyclerViewallchude.setAdapter( dsallChudeAdapter );
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        } );
    }


}
