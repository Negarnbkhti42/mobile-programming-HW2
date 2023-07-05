package com.example.bookshelf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Entities.FavouredBook;
import com.example.bookshelf.Google.Book;
import com.example.bookshelf.Google.GoogleFacade;
import com.example.bookshelf.Google.GoogleFacadeImpl;
import com.example.bookshelf.Google.OpenLibraryImpl;
import com.example.bookshelf.adaptors.BookListAdaptor;
import com.example.bookshelf.services.SessionManager;
import com.example.bookshelf.viewmodels.FavouredBookViewModel;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {
    private GoogleFacade googleFacade = OpenLibraryImpl.getINSTANCE();
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
        LinearLayout favoriteViewGroup = rootView.findViewById(R.id.favorite_fragment_view_group);
        favoriteViewGroup.addView(v);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.favorite_recycler_view);

        favouredBookViewModel = new ViewModelProvider(getActivity()).get(FavouredBookViewModel.class);
        adaptor = new BookListAdaptor();
//        LiveData<List<FavouredBook>> favouredBooks = favouredBookViewModel.getFavouredBooks("amirali");
//        if (favouredBooks != null)
//            favouredBooks.observe(getActivity(), favouredBooks1 -> new Thread(() -> {
//                List<Book> books = new ArrayList<>();
//                for (FavouredBook favouredBook : favouredBooks1) {
//                    if (!favouredBook.getBookId().equals("1")) {
//                        books.add(googleFacade.findById(favouredBook.getBookId()));
//                    }
//                }
//                adaptor.setBookList(books);
//            }).start());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adaptor);

    }
}