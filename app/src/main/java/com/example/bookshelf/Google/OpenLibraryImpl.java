package com.example.bookshelf.Google;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenLibraryImpl implements GoogleFacade {
    private static OpenLibraryImpl INSTANCE;

    public static OpenLibraryImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new OpenLibraryImpl();
        }
        return INSTANCE;
    }

    private OpenLibraryImpl() {

    }

    private final String urlString = "https://openlibrary.org/search.json?";
    private final String baseImageUrl = "https://covers.openlibrary.org/b/id/";

    private final String keyBaseSearch = "https://openlibrary.org";

    private final String jsonKeyWord = ".json";

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
            JSONArray docs = jsonObject.getJSONArray("docs");
            int size = docs.length();
            if (size > 5) {
                size = 5;
            }
            for (int i = 0; i < size; i++) {
                Book book = new Book();
                fillBook(docs.getJSONObject(i), book);
                result.add(book);
            }
        } catch (Exception e) {
            Book book = new Book();
            book.setTitle("harry potter deathly hallows");
            book.setAuthor("j.k Rolling");
            book.setPrice(25);
            book.setId("fer");
            book.setDescription("a wonderful end to adventure of harry and his friends...");
            List<Book> books = new ArrayList<>();
            books.add(book);
            return books;
        }
        return result;
    }

    private void fillBook(JSONObject doc, Book book) throws JSONException {
        book.setTitle(doc.getString("title"));
        book.setAuthor(doc.getJSONArray("author_name").getString(0));
        book.setId(doc.getString("key"));
        try {
            book.setImageThumbnail(baseImageUrl + doc.getString("cover_i") + "-L.jpg");
        } catch (Exception ignored) {

        }
        Random rand = new Random();
        int randomNumber = rand.nextInt(46) + 15;
        book.setPrice(randomNumber);
        StringBuilder subjectBuilder = new StringBuilder();
        JSONArray subjectFacets = doc.getJSONArray("subject_facet");
        for (int i = 0; i < subjectFacets.length(); i++) {
            String subject = subjectFacets.getString(i);
            subjectBuilder.append(subject);
            if (i < subjectFacets.length() - 1) {
                subjectBuilder.append(", ");
            }
        }
        book.setDescription(subjectBuilder.toString());
    }

    private String getQuery(BookFilter bookFilter) {
        StringBuilder query = new StringBuilder();
        Boolean removePlus = Boolean.FALSE;
        query.append(urlString);
        if (bookFilter.getAuthor() != null) {
            query.append("author=");
            query.append(bookFilter.getAuthor());
            query.append("&");
            removePlus = Boolean.TRUE;
        }
        if (bookFilter.getPublisher() != null) {
            query.append("publisher=");
            query.append(bookFilter.getPublisher());
            query.append("&");
            removePlus = Boolean.TRUE;

        }
        if (bookFilter.getTitle() != null) {
            query.append("title=");
            query.append(bookFilter.getTitle());
            query.append("&");
            removePlus = Boolean.TRUE;
        }
        if (removePlus) {
            query.deleteCharAt(query.length() - 1);
        }
        return query.toString();
    }

    @Override
    public Book findById(String Id) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getFindBookQuery(Id))
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json;
            if (response.body() != null) {
                json = response.body().string();
            } else {
                return new Book();
            }
            JSONObject jsonObject = new JSONObject(json);
            Book book = new Book();
            fillBook(jsonObject, book, Id);
            return book;
        } catch (Exception ignored) {
            return new Book();
        }
    }

    private void fillBook(JSONObject doc, Book book, String id) throws JSONException {
        book.setTitle(doc.getString("title"));
        book.setId(id);
        StringBuilder subjectBuilder = new StringBuilder();
        JSONArray subjectFacets = doc.getJSONArray("subjects");
        for (int i = 0; i < subjectFacets.length(); i++) {
            String subject = subjectFacets.getString(i);
            subjectBuilder.append(subject);
            if (i < subjectFacets.length() - 1) {
                subjectBuilder.append(", ");
            }
        }
        book.setDescription(subjectBuilder.toString());
    }

    private String getFindBookQuery(String bookId) {
        return keyBaseSearch +
                bookId +
                jsonKeyWord;
    }
}
