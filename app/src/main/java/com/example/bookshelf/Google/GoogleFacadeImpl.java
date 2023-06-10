package com.example.bookshelf.Google;

import com.example.bookshelf.Database.BookRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleFacadeImpl implements GoogleFacade {
    private static GoogleFacadeImpl INSTANCE;

    public static GoogleFacadeImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleFacadeImpl();
        }
        return INSTANCE;
    }

    private GoogleFacadeImpl() {

    }

    private final String urlString = "https://www.googleapis.com/books/v1/volumes";

    @Override
    public List<Book> searchBook(BookFilter filter) {
        List<Book> result = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getQuery(filter))
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json;
            if (response.body() != null) {
                json = response.body().string();
            } else {
                return result;
            }
            JSONObject jsonObject = new JSONObject(json);
            JSONArray items = jsonObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                Book book = new Book();
                JSONObject item = items.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                fillAuthorAndTitleAndImage(book, volumeInfo);
                book.setRate(getRating(item.getString("id")));
                result.add(book);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Double getRating(String Id) {
        return BookRepository.getInstance().getAverageRate(Id);
    }

    private String getQuery(BookFilter bookFilter) {
        StringBuilder query = new StringBuilder();
        Boolean removePlus = Boolean.FALSE;
        query.append(urlString);
        query.append("?q=");
        if (bookFilter.getAuthor() != null) {
            query.append(APIParameter.AUTHOR);
            query.append(bookFilter.getAuthor());
            query.append("+");
            removePlus = Boolean.TRUE;
        }
        if (bookFilter.getPublisher() != null) {
            query.append(APIParameter.PUBLISHER);
            query.append(bookFilter.getAuthor());
            query.append("+");
            removePlus = Boolean.TRUE;

        }
        if (bookFilter.getTitle() != null) {
            query.append(APIParameter.TITLE);
            query.append(bookFilter.getTitle());
            query.append("+");
            removePlus = Boolean.TRUE;
        }
        if (removePlus) {
            query.deleteCharAt(query.length() - 1);
        }
        return query.toString();
    }

    private void fillAuthorAndTitleAndImage(Book book, JSONObject volumeInfo)
            throws JSONException, MalformedURLException {
        book.setTitle(volumeInfo.getString("title"));
        JSONArray authors = volumeInfo.getJSONArray("authors");
        for (int j = 0; j < authors.length(); j++) {
            book.getAuthors().add(authors.getString(j));
        }
        String url = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
        book.setImageThumbnail(new URL(url));
    }

    @Override
    public Book findById(String Id) {
        Book result;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getFindBookQuery(Id))
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json;
            if (response.body() != null) {
                json = response.body().string();
            } else {
                return null;
            }
            JSONObject jsonObject = new JSONObject(json);
            result = new Book();
            JSONObject volumeInfo = jsonObject.getJSONObject("volumeInfo");
            fillAuthorAndTitleAndImage(result, volumeInfo);
            result.setRate(getRating(jsonObject.getString("id")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private String getFindBookQuery(String bookId) {
        return urlString +
                "/" +
                bookId;
    }
}
