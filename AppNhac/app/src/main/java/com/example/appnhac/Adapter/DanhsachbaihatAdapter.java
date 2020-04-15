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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate( R.layout.dong_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txttenbaihat.setText(baihat.getTenbaihat());
        holder.txtindex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txtcasi;
        ImageView imgluotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById( R.id.textviewtencasi);
            txtindex = itemView.findViewById( R.id.textviewdanhsachindex);
            txttenbaihat = itemView.findViewById( R.id.textviewtenbaihat);
            imgluotthich = itemView.findViewById( R.id.imageviewluotthichdanhsachbaihat);
            imgluotthich.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource( R.drawable.iconloved );
                    Dataservice dataservice= APIService.getService();
                    Call<String> stringCall=dataservice.UpdateLuotThich( "1", mangbaihat.get( getPosition() ).getIdbaihat());
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
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent( context, PlayNhacActivity.class );
                    intent.putExtra( "cakhuc",mangbaihat.get( getPosition() ) );
                    context.startActivity( intent );
                }
            } );
        }
    }
}
