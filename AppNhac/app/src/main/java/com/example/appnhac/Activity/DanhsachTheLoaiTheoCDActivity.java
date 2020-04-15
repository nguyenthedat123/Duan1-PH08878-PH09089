package com.example.appnhac.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appnhac.Adapter.DSALLChudeAdapter;
import com.example.appnhac.Adapter.DSTLtheoCDAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachTheLoaiTheoCDActivity extends AppCompatActivity {
    ChuDe chuDe;
    RecyclerView rcctheloaitheochude;
    DSTLtheoCDAdapter dstLtheoCDAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_danhsach_the_loai_theo_cd );
        init();
        GetIntent();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<TheLoai>> theLoaiCall=dataservice.GetTheLoaitheoChuDe( chuDe.getIdChuDe() );
        theLoaiCall.enqueue( new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList= (ArrayList<TheLoai>) response.body();
                dstLtheoCDAdapter= new DSTLtheoCDAdapter( DanhsachTheLoaiTheoCDActivity.this,theLoaiArrayList );
                rcctheloaitheochude.setLayoutManager( new GridLayoutManager( DanhsachTheLoaiTheoCDActivity.this,2 ) );
                rcctheloaitheochude.setAdapter( dstLtheoCDAdapter );
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        } );
    }

    private void init() {
        rcctheloaitheochude=findViewById( R.id.rcctheloaitheochude );

    }


    private void GetIntent() {
        Intent intent=getIntent();
        if (intent.hasExtra( "chude" )){
            chuDe= (ChuDe) intent.getSerializableExtra( "chude" );
            Toast.makeText( this,chuDe.getTenChuDe(),Toast.LENGTH_SHORT ).show();
        }
    }
}
