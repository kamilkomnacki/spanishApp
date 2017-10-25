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


public class FamilyFragment extends Fragment {


    MediaPlayer mediaPlayer = new MediaPlayer();


    public FamilyFragment() {
        // Required empty public constructor
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_category, container, false);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("daughter", "hija", R.drawable.family_daughter, R.raw.family_hija));
        words.add(new Word("father", "padre", R.drawable.family_father, R.raw.family_padre));
        words.add(new Word("grandfather", "abuelo", R.drawable.family_grandfather, R.raw.family_abuelo));
        words.add(new Word("grandmother", "abuela", R.drawable.family_grandmother, R.raw.family_abuela));
        words.add(new Word("mother", "madre", R.drawable.family_mother, R.raw.family_madre));
        words.add(new Word("older brother", "hermano mayor", R.drawable.family_older_brother, R.raw.family_hermano_mayor));
        words.add(new Word("older sister", "hermana mayor", R.drawable.family_older_sister, R.raw.family_hermana_mayor));
        words.add(new Word("son", "hijo", R.drawable.family_son, R.raw.family_hijo));
        words.add(new Word("younger brother", "hermano menor", R.drawable.family_younger_brother, R.raw.family_hermano_menor));
        words.add(new Word("younger sister", "hermana menor", R.drawable.family_younger_sister, R.raw.family_hermana_menor));




        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_family);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });


        return rootView;
    }


    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
