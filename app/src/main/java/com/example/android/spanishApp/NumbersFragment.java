package com.example.android.spanishApp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersFragment extends Fragment {



    MediaPlayer mMediaPlayer = new MediaPlayer();

    public NumbersFragment() {
        // Required empty public constructor
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_category,container,false);


        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one","uno", R.drawable.number_one, R.raw.number_1));
        words.add(new Word("two","dos", R.drawable.number_two, R.raw.number_2));
        words.add(new Word("three","tres", R.drawable.number_three, R.raw.number_3));
        words.add(new Word("four","cuatro", R.drawable.number_four, R.raw.number_4));
        words.add(new Word("five","cinco", R.drawable.number_five, R.raw.number_5));
        words.add(new Word("six","seis", R.drawable.number_six, R.raw.number_6));
        words.add(new Word("seven","siete", R.drawable.number_seven, R.raw.number_7));
        words.add(new Word("eight","ocho", R.drawable.number_eight, R.raw.number_8));
        words.add(new Word("nine","nueve", R.drawable.number_nine, R.raw.number_9));
        words.add(new Word("ten","diez", R.drawable.number_ten, R.raw.number_10));




        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("NUMBER ACTIVITY: " , "Current word: " + words.get(position).toString());
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
