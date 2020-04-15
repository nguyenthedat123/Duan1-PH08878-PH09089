package com.example.appnhac.Fragment;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appnhac.Activity.DanhsachTheLoaiTheoCDActivity;
import com.example.appnhac.Activity.DanhsachtatcachudeActivity;
import com.example.appnhac.Model.CDandTL;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai extends Fragment {


    public static Fragment_ChuDe_TheLoai newInstance() {
        return new Fragment_ChuDe_TheLoai();
    }
    HorizontalScrollView horizontalScrollView;
    TextView xemthem;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate( R.layout.fragment__chu_de__the_loai, container, false );
        horizontalScrollView=view.findViewById( R.id.scrollviewhori );
        xemthem=view.findViewById( R.id.tvxemthem );
        xemthem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( getActivity(), DanhsachtatcachudeActivity.class );
                startActivity( intent );
            }
        } );
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<CDandTL> cDandTLCall=dataservice.getChuDevaTL();
        cDandTLCall.enqueue( new Callback<CDandTL>() {
            @Override
            public void onResponse(Call<CDandTL> call, Response<CDandTL> response) {
                CDandTL cDandTL=response.body();
                Log.d( "CCC",cDandTL.getTheLoai().get( 0 ).getTenTheLoai() );

                final ArrayList<ChuDe> chuDeArrayList=new ArrayList<>(  );
                chuDeArrayList.addAll( cDandTL.getChuDe() );

                final ArrayList<TheLoai> theLoaiArrayList=new ArrayList<>(  );
                theLoaiArrayList.addAll( cDandTL.getTheLoai() );

                LinearLayout linearLayout=new LinearLayout( getActivity() );
                linearLayout.setOrientation( LinearLayout.HORIZONTAL );
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams( 580,250 );
                layoutParams.setMargins( 10,20,10,30 );

                for (int i = 0; i < (chuDeArrayList.size()); i++) {
                    CardView cardView=new CardView( getActivity() );
                    cardView.setRadius( 10 );
                    ImageView imageView=new ImageView( getActivity() );
                    imageView.setScaleType( ImageView.ScaleType.FIT_XY );
                    if (chuDeArrayList.get( i).getHinhChuDe()!=null){
                        Picasso.with( getActivity() ).load( chuDeArrayList.get( i ).getHinhChuDe() ).into( imageView );
                    }
                    cardView.setLayoutParams( layoutParams );
                    cardView.addView( imageView );
                    linearLayout.addView( cardView );
                    final int finalI = i;
                    imageView.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent( getActivity(), DanhsachTheLoaiTheoCDActivity.class );
                            intent.putExtra( "chude",chuDeArrayList.get( finalI ) );
                            startActivity( intent );
                        }

                    } );
                }
                for (int j = 0; j < (theLoaiArrayList.size()); j++) {
                    CardView cardView=new CardView( getActivity() );
                    cardView.setRadius( 10 );
                    ImageView imageView=new ImageView( getActivity() );
                    imageView.setScaleType( ImageView.ScaleType.FIT_XY );
                    if (theLoaiArrayList.get( j).getHinhTheLoai()!=null){
                        Picasso.with( getActivity() ).load( theLoaiArrayList.get( j ).getHinhTheLoai() ).into( imageView );
                    }
                    cardView.setLayoutParams( layoutParams );
                    cardView.addView( imageView );
                    linearLayout.addView( cardView );
                }
                horizontalScrollView.addView( linearLayout );
            }

            @Override
            public void onFailure(Call<CDandTL> call, Throwable t) {

            }
        } );
    }


}
