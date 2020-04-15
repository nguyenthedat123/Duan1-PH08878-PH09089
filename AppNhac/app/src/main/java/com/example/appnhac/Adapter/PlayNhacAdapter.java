package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;

    public PlayNhacAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from( context );
        View view=layoutInflater.inflate( R.layout.dong_play,parent,false );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat=mangbaihat.get( position );
        holder.tvindex.setText( position +1);
        holder.tvtencsi.setText( baihat.getCasi() );
        holder.tvtenbh.setText( baihat.getTenbaihat() );
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvindex,tvtenbh,tvtencsi;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            tvindex=itemView.findViewById( R.id.tvplayindex );
            tvtenbh=itemView.findViewById( R.id.tvplaynhac );
            tvtencsi=itemView.findViewById( R.id.tvnamecasi );
        }

    }
}
