package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DSTLtheoCDAdapter extends RecyclerView.Adapter<DSTLtheoCDAdapter.ViewHolder>{

    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DSTLtheoCDAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from( context );
        View view=layoutInflater.inflate( R.layout.dong_theloaitheochude,parent,false );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai=theLoaiArrayList.get( position );
        Picasso.with( context ).load( theLoai.getHinhTheLoai() ).into( holder.imghinhnen );
        holder.tvtentheloai.setText( theLoai.getTenTheLoai() );
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView tvtentheloai;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            imghinhnen=itemView.findViewById( R.id.imgtheloaitheochude );
            tvtentheloai=itemView.findViewById( R.id.tvtentheloaitheochude );
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent( context, DanhsachbaihatActivity.class );
                    intent.putExtra( "idtheloai",theLoaiArrayList.get( getPosition() ) );
                    context.startActivity( intent );
                }
            } );
        }
    }
}
