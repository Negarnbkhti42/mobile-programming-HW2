package com.example.bookshelf;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Database.FavouredBookRepository;
import com.example.bookshelf.Entities.FavouredBook;
import com.example.bookshelf.adaptors.BookListAdaptor;
import com.example.bookshelf.services.SessionManager;

import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment {

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        LayoutInflater inflater2 = LayoutInflater.from(getActivity());
        View v = inflater2.inflate(R.layout.layout_empty_cart, null);
        LinearLayout cartViewGroup = rootView.findViewById(R.id.cart_fragment_view_group);
        cartViewGroup.addView(v);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BookListAdaptor adaptor = new BookListAdaptor();

        RecyclerView recyclerView = view.findViewById(R.id.cart_recycler_view);

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
}