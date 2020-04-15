package com.example.appnhac.Fragment;

import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.appnhac.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Dia_nhac extends Fragment {


    public static Fragment_Dia_nhac newInstance() {
        return new Fragment_Dia_nhac();
    }
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate( R.layout.fragment__dia_nhac, container, false );
        circleImageView=view.findViewById( R.id.imgdianhac );
        objectAnimator=ObjectAnimator.ofFloat( circleImageView,"rotation",0f,360f );
        objectAnimator.setDuration( 10000 );
        objectAnimator.setRepeatCount( ValueAnimator.INFINITE );
        objectAnimator.setRepeatMode( ValueAnimator.RESTART );
        objectAnimator.setInterpolator( new LinearInterpolator(  ) );
        return view;
    }



}
