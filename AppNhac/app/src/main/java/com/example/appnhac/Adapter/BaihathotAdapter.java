package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.PlayNhacActivity;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;

    public BaihathotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate( R.layout.dong_bai_hat_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txtten.setText(baihat.getTenbaihat());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten,txtcasi;
        ImageView imghinh, imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById( R.id.textviewtenbaihathot);
            txtcasi = itemView.findViewById( R.id.textviewcasibaihathot);
            imghinh = itemView.findViewById( R.id.imageviewbaihathot);
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent( context, PlayNhacActivity.class );
                    intent.putExtra( "cakhuc",baihatArrayList.get( getPosition() ) );
                    context.startActivity( intent );
                }
            } );
            imgluotthich = itemView.findViewById( R.id.imageviewluotthich);
            imgluotthich.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource( R.drawable.iconloved );
                    Dataservice dataservice= APIService.getService();
                    Call<String> stringCall=dataservice.UpdateLuotThich( "1", baihatArrayList.get( getPosition() ).getIdbaihat());
                    stringCall.enqueue( new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua=response.body();
                            if (ketqua.equals( "Succsess" )){
                                Toast.makeText( context,"Da thich",Toast.LENGTH_SHORT ).show();
                            }else {
                                Toast.makeText( context,"Error",Toast.LENGTH_SHORT ).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    } );
                    imgluotthich.setEnabled( false );
                }
            } );
        }
    }
}
