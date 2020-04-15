package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Adapter.ViewPagerPlay;
import com.example.appnhac.Fragment.Fragment_Bai_Hat_Hot;
import com.example.appnhac.Fragment.Fragment_Dia_nhac;
import com.example.appnhac.Fragment.Fragment_Play_DSBH;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import static android.os.StrictMode.*;

public class PlayNhacActivity extends AppCompatActivity {
    SeekBar seekBar;
    ImageButton imgplay,imgrepeat,imgnext,imgpre,imgrandom;
    TextView timestart,timeend,tvnamebh,tvnamecs;
    ViewPager viewPagerplayqq;
    public static ArrayList<Baihat> mangbaihat=new ArrayList<>(  );
    public static ViewPagerPlay viewPagerPlay;
    Fragment_Dia_nhac fragment_dia_nhac;
    Fragment_Play_DSBH fragment_play_dsbh;

    MediaPlayer mediaPlayer;

    int position=0;
    boolean repeat=false;
    boolean check=false;
    boolean next=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_play_nhac );
        init();
        GetDataFrom();
        evenClick();

    }

    private void evenClick() {
        final Handler handler=new Handler(  );
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlay.getItem( 1 )!=null){
                    if (mangbaihat.size()>0){
                                handler.removeCallbacks( this );
                    }else {
                        handler.postDelayed( this,300 );
                    }
                }
            }
        },500 );
        imgplay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource( R.drawable.iconplay );
                }else {
                    mediaPlayer.start();
                    imgplay.setImageResource( R.drawable.iconpause
                    );
                }
            }
        } );
        imgrepeat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat==false){
                    if (check == true){
                        check=false;
                        imgrepeat.setImageResource( R.drawable.iconsyned );
                        imgrandom.setImageResource( R.drawable.iconsuffle );
                    }
                    imgrepeat.setImageResource( R.drawable.iconsyned );
                    repeat=true;

                }else {
                    imgrepeat.setImageResource( R.drawable.iconrepeat );
                    repeat=false;
                }
            }
        } );
        imgrandom.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check==false){
                    if (repeat == true){
                        repeat=false;
                        imgrandom.setImageResource( R.drawable.iconshuffled );
                        imgrepeat.setImageResource( R.drawable.iconrepeat );
                    }
                    imgrandom.setImageResource( R.drawable.iconshuffled );
                    check=true;

                }else {
                    imgrepeat.setImageResource( R.drawable.iconrepeat );
                    repeat=false;
                }
            }
        } );
        seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo( seekBar.getProgress() );
            }
        } );
        imgnext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size()>0){
                    if (mediaPlayer.isPlaying()||mediaPlayer!=null);
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer=null;
                }
                if (position<mangbaihat.size()){
                    imgplay.setImageResource( R.drawable.iconpause );
                    position++;
                    if (repeat==true){
                        if (position==0){
                            position=mangbaihat.size();
                        }
                        position=-1;
                    }
                    if (check==true){
                        Random random=new Random(  );
                        int index=random.nextInt(mangbaihat.size());
                        if (index==position){
                            position=index-1;
                        }
                        position=index;
                    }
                    if (position>mangbaihat.size()-1){
                        position=0;
                    }
                    new PlayMP3().execute( mangbaihat.get( position ).getLinkbaihat());
                    getSupportActionBar().setTitle( mangbaihat.get( position ).getTenbaihat() );

                }
            }

        } );
    }

    private void GetDataFrom() {
        Intent intent=getIntent();
        mangbaihat.clear();
        if (intent.hasExtra( "cakhuc" )){
            Baihat baihat=intent.getParcelableExtra( "cakhuc" );
            mangbaihat.add( baihat );

        }
        if (intent.hasExtra( "cacbaihat" )){
            ArrayList<Baihat> mangbaihat1=intent.getParcelableArrayListExtra( "cacbaihat" );
            mangbaihat=mangbaihat1;
        }
    }

    private void init() {
        seekBar=findViewById( R.id.seekbar );
        timestart=findViewById( R.id.tvtimestart );
        timeend=findViewById( R.id.tvtimeend );
        tvnamebh=findViewById( R.id.tvnamebhmusic );
        tvnamecs=findViewById( R.id.tvnamecsmusic );
        imgnext=findViewById( R.id.imgbottomnext );
        imgplay=findViewById( R.id.imgbottomplay );
        imgpre=findViewById( R.id.imgbottomback );
        imgrandom=findViewById( R.id.imgbottomsuffle );
        imgrepeat=findViewById( R.id.imgbottomrepeat );
        viewPagerplayqq=findViewById( R.id.viewpagerplaylist );
        fragment_dia_nhac=new Fragment_Dia_nhac();
        fragment_play_dsbh=new Fragment_Play_DSBH();
        viewPagerPlay=new ViewPagerPlay( getSupportFragmentManager() );
        viewPagerPlay.addFragment( fragment_dia_nhac );
        viewPagerPlay.addFragment( fragment_play_dsbh );
        fragment_dia_nhac= (Fragment_Dia_nhac) viewPagerPlay.getItem( 1 );
        viewPagerplayqq.setAdapter( viewPagerPlay );
        if (mangbaihat.size()>0){
            getSupportActionBar().setTitle( mangbaihat.get( 0 ).getTenbaihat() );
            new PlayMP3().execute( mangbaihat.get( 0 ).getLinkbaihat() );
            imgplay.setImageResource( R.drawable.iconpause );
        }
    }
    class PlayMP3 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute( s );
            try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setAudioStreamType( AudioManager.STREAM_MUSIC );
            mediaPlayer.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            } );

                mediaPlayer.setDataSource( s );
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }
        private void TimeSong() {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat( "mm:ss" );
            timeend.setText( simpleDateFormat.format( mediaPlayer.getDuration() ) );
            seekBar.setMax( mediaPlayer.getDuration() );
        }

}
