package com.example.appnhac.Fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnhac.Activity.PlayNhacActivity;
import com.example.appnhac.Adapter.PlayNhacAdapter;
import com.example.appnhac.R;

public class Fragment_Play_DSBH extends Fragment {

    RecyclerView rccplay;
    PlayNhacAdapter playNhacAdapter;

    public static Fragment_Play_DSBH newInstance() {
        return new Fragment_Play_DSBH();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate( R.layout.fragment__play__dsbh, container, false );
        rccplay=view.findViewById( R.id.rccplay );
        if (PlayNhacActivity.mangbaihat.size()>0) {
            playNhacAdapter = new PlayNhacAdapter( getActivity(), PlayNhacActivity.mangbaihat );
            rccplay.setLayoutManager( new LinearLayoutManager( getActivity() ) );
            rccplay.setAdapter( playNhacAdapter );
        }
        return  view;

    }


}
