package com.example.bookshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;


public class FavoriteFragment extends Fragment {

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        LayoutInflater inflater2 = LayoutInflater.from(getActivity());
        View v = inflater2.inflate(R.layout.layout_empty_favorite_list, null);
        ScrollView favoriteViewGroup = rootView.findViewById(R.id.favorite_fragment_view_group);
        favoriteViewGroup.addView(v);
        return rootView;
    }
}