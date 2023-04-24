package com.example.youtubefillters;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchResults extends AsyncTask<String, Void, String> {

    final String BASE_URL = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyAEk7F_bbhTFUWxwJXDn5fzxviwCJYk7EY&part=snippet&maxResults=10&q=SEARCH_QUERY&type=video";
    final String QUERY = "q";
//    final String MAX_RESULTS = "maxResults";
//    final String PRINT_TYPE = "printType";

    OkHttpClient client = new OkHttpClient();

    Context context = MainActivity.mainBinding.getRoot().getContext();

    @Override
    protected String doInBackground(String... strings) {
        HttpUrl.Builder builder = HttpUrl.parse(BASE_URL).newBuilder();
        builder.addQueryParameter(QUERY, strings[0]);

        String url = builder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);

        try {
            JSONObject jsonObject = new JSONObject(string);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            List<Fillters> videoList = new ArrayList<>();

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject video = itemsArray.getJSONObject(i);
                JSONObject snippet = video.getJSONObject("snippet");
                JSONObject thumbnails = snippet.getJSONObject("thumbnails");

                videoList.add(new Fillters(
                        video.getJSONObject("id").getString("videoId"),
                        thumbnails.getJSONObject("high").getString("url"),
                        snippet.getString("title"),
                        snippet.getString("channelTitle"),
                        snippet.getString("publishTime"),
                        snippet.getString("description")
                ));
            }

            if (videoList.size() > 0) {
                MainActivity.mainBinding.progressBar.setVisibility(View.INVISIBLE);
                ListAdapter listAdapter = new ListAdapter(videoList);
                MainActivity.mainBinding.recyclerView.setAdapter(listAdapter);
                MainActivity.mainBinding.recyclerView.setLayoutManager(
                        new LinearLayoutManager(context));
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainActivity.mainBinding.progressBar.setVisibility(View.VISIBLE);
    }
}
