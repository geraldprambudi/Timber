package com.naman14.timber.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naman14.timber.R;
import com.naman14.timber.adapters.ArtistSongAdapter;
import com.naman14.timber.dataloaders.ArtistSongLoader;
import com.naman14.timber.models.Song;
import com.naman14.timber.utils.Constants;
import com.naman14.timber.widgets.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by naman on 23/07/15.
 */
public class ArtistMusicFragment extends Fragment {

    long artistID = -1;

    public static RecyclerView songsRecyclerview;

    ArtistSongAdapter mSongAdapter;

    public static ArtistMusicFragment newInstance(long id) {
        ArtistMusicFragment fragment = new ArtistMusicFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.ARTIST_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artistID = getArguments().getLong(Constants.ARTIST_ID);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_artist_music, container, false);

        songsRecyclerview=(RecyclerView) rootView.findViewById(R.id.recycler_view_songs);

        setUpSongs();


        return rootView;
    }



    private void setUpSongs() {
        songsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Song> songList;
        songList=ArtistSongLoader.getSongsForArtist(getActivity(), artistID);

       // adding two dummy songs to top of arraylist
        //there will be  dummy header and albums header respectively in theses two positions in recyclerview
        songList.add(0,new Song(-1,-1,-1,"dummy","dummy","dummy",-1,-1));

        mSongAdapter = new ArtistSongAdapter(getActivity(), songList, artistID);
        songsRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        songsRecyclerview.setAdapter(mSongAdapter);
    }


}
