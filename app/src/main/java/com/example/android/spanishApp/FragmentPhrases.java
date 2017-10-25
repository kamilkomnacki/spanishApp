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

public class FragmentPhrases extends Fragment {


    MediaPlayer mMediaPlayer = new MediaPlayer();

    public FragmentPhrases() {
        // Required empty public constructor
    }

    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_category, container, false);


        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("My name is...","Me llamo...",R.raw.phrases_me_llamo));
        words.add(new Word("Where is the...","Dónde está el",R.raw.phrases_donde_esta_el));
        words.add(new Word("What are you doing?","¿Qué haces?",R.raw.phrases_que_haces));
        words.add(new Word("How old are you?","¿Cuántos años tiene(s)?",R.raw.phrases_quantos_anios_tiene));
        words.add(new Word("Where are you from?","¿De dónde eres?",R.raw.phrases_de_donde_eres));
        words.add(new Word("How are you?","¿Cómo estás?",R.raw.phrases_como_estas));
        //words.add(new Word("God bless you!","Dios te bendiga!",R.raw.));
        words.add(new Word("Come here...","Ven acá...",R.raw.phrases_ven_aka));
        words.add(new Word("I don't understand.","no entiendo",R.raw.phrases_no_entiendo));
        words.add(new Word("I speak a little spanish.","Hablo un poco de español.",R.raw.phrases_hablo_un_poco_de_espanol));
        words.add(new Word("This is ...","este es...",R.raw.phrases_este_es));
        words.add(new Word("Thank you for your help.","Gracias por tu ayuda.",R.raw.phrases_gracias_por_tu_ayuda));

        WordAdapter itemAdapter = new WordAdapter(getActivity(),words,R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });
        return rootView;
    }

}
