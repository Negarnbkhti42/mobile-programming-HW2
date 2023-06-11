package com.example.bookshelf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.bookshelf.Entities.FavouredBook;
import com.example.bookshelf.Google.Book;
import com.example.bookshelf.Google.GoogleFacadeImpl;
import com.example.bookshelf.adaptors.BookListAdaptor;
import com.example.bookshelf.viewmodels.FavouredBookViewModel;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

    private FavouredBookViewModel favouredBookViewModel;
    private BookListAdaptor adaptor;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.favorite_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        favouredBookViewModel = new ViewModelProvider(getActivity()).get(FavouredBookViewModel.class);
        adaptor = new BookListAdaptor();
        favouredBookViewModel.getFavouredBooks().observe(getActivity(), new Observer<List<FavouredBook>>() {
            @Override
            public void onChanged(List<FavouredBook> favouredBooks) {
                List<Book> books = new ArrayList<>();
                for (FavouredBook favouredBook : favouredBooks) {
                    books.add(GoogleFacadeImpl.getINSTANCE().findById(favouredBook.getBookId()));
                }
                adaptor.setBookList(books);
            }
        });
    }
}