package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.DanhsachTheLoaiTheoCDActivity;
import com.example.appnhac.Activity.DanhsachtatcachudeActivity;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DSALLChudeAdapter extends RecyclerView.Adapter<DSALLChudeAdapter.ViewHolder>{
    Context context;
    ArrayList<ChuDe> chuDeArrayList;

    public DSALLChudeAdapter(Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from( context );
        View view=inflater.inflate( R.layout.dong_all_chude,parent,false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe=chuDeArrayList.get( position );
        Picasso.with( context ).load( chuDe.getHinhChuDe() ).into( holder.imgallchude );
    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgallchude;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            imgallchude=itemView.findViewById( R.id.imgallchude);
            imgallchude.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent( context, DanhsachTheLoaiTheoCDActivity.class );
                    intent.putExtra( "chude",chuDeArrayList.get( getPosition() ));
                    context.startActivity( intent );
                }
            } );
        }
    }
}
