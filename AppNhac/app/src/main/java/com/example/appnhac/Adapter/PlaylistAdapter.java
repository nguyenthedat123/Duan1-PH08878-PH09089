package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super( context, resource, objects );
    }
    class  ViewHolder{
        TextView tvname;
        ImageView imgbackground,imgicon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from( getContext() );
            convertView=inflater.inflate( R.layout.list_all_ttracks,null );
            viewHolder=new ViewHolder();
            viewHolder.tvname=convertView.findViewById( R.id.tvnameplaylist );
            viewHolder.imgbackground=convertView.findViewById( R.id.imgbackgrow );
            viewHolder.imgicon=convertView.findViewById( R.id.imgicon );
        }else viewHolder= (ViewHolder) convertView.getTag();

        Playlist playlist=getItem( position );
        viewHolder.tvname.setText( playlist.getTenPL() );
        Picasso.with( getContext() ).load( playlist.getHinhPlayL() ).into( viewHolder.imgbackground );
        Picasso.with( getContext() ).load( playlist.getICON() ).into( viewHolder.imgicon );
        return convertView;
    }
}
