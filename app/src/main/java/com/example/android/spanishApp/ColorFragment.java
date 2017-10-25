package com.example.android.spanishApp;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorFragment extends Fragment {


    public ColorFragment() {
        // Required empty public constructor
    }



    MediaPlayer mMediaPlayer = new MediaPlayer();
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {releaseMediaPlayer();}
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mMediaPlayer!= null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_category, container, false);


        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("white","blanco",R.drawable.color_white,R.raw.color_blanco));
        words.add(new Word("cream","crema", R.drawable.color_dusty_yellow, R.raw.color_crema));
        words.add(new Word("mustard","el amarillo mostaza",R.drawable.color_mustard_yellow,R.raw.color_el_amarillo_mostaza));
        words.add(new Word("gray","gris",R.drawable.color_gray,R.raw.color_gris));
        words.add(new Word("brown","marrón",R.drawable.color_brown,R.raw.color_marron));
        words.add(new Word("black","negro",R.drawable.color_black,R.raw.color_negro));
        words.add(new Word("red","rojo",R.drawable.color_red,R.raw.color_rojo));
        words.add(new Word("green","verde",R.drawable.color_green, R.raw.color_verde));

        WordAdapter itemAdapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

        return rootView;

    }

}
