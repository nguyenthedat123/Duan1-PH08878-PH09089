package com.example.appnhac.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appnhac.Adapter.PlaylistAdapter;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {


    public static Fragment_PlayList newInstance() {
        return new Fragment_PlayList();
    }
    ListView lvlist;
    TextView tvplaylist,tvxemthempl;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> arrayList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate( R.layout.play_list_fragment, container, false );
        lvlist=view.findViewById( R.id.lvplaylist );
        tvplaylist=view.findViewById( R.id.tvplaylist );
        tvxemthempl=view.findViewById( R.id.tvmoreplay );
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Playlist>> callback=dataservice.getDataPlaylist();
        callback.enqueue( new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                arrayList= (ArrayList<Playlist>) response.body();
                playlistAdapter=new PlaylistAdapter( getActivity(),android.R.layout.simple_list_item_1,arrayList);
                lvlist.setAdapter( playlistAdapter );
                setListViewHeightBasedOnChildren( lvlist );
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        } );
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
