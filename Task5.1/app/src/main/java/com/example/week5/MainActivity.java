package com.example.week5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        progressBar = findViewById(R.id.progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newsRecyclerView.setLayoutManager(layoutManager);

        // Fetch news articles in the background using an AsyncTask
        new FetchNewsTask().execute();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private class FetchNewsTask extends AsyncTask<Void, Void, List<NewsArticle>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressBar();
        }

        @Override
        protected List<NewsArticle> doInBackground(Void... voids) {
            return NetworkUtils.fetchNewsArticles("technology");
        }

        @Override
        protected void onPostExecute(List<NewsArticle> newsArticles) {
            super.onPostExecute(newsArticles);
            hideProgressBar();

            newsAdapter = new NewsAdapter(newsArticles);
            newsRecyclerView.setAdapter(newsAdapter);
        }
    }



    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
        private List<NewsArticle> articles;

        public NewsAdapter(List<NewsArticle> articles) {
            this.articles = articles;
        }

        @NonNull
        @Override
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
            NewsArticle article = articles.get(position);
            holder.titleTextView.setText(article.getTitle());
            holder.descriptionTextView.setText(article.getDescription());
            holder.publishDateTextView.setText(article.getPublishDate());

            Picasso.get().load(article.getImageUrl())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }

        public class NewsViewHolder extends RecyclerView.ViewHolder {
            public TextView titleTextView;
            public TextView descriptionTextView;
            public TextView publishDateTextView;
            public ImageView imageView;

            public NewsViewHolder(View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.titleTextView);
                descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
                publishDateTextView = itemView.findViewById(R.id.publishDateTextView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }


    public static class NewsArticle {
        private String title;
        private String description;
        private String imageUrl;
        private String publishDate;

        public NewsArticle(String title, String description, String imageUrl, String publishDate) {
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
            this.publishDate = publishDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getPublishDate() {
            return publishDate;
        }
    }


    public static class NetworkUtils {
        private static final String BASE_URL = "https://newsapi.org/v2/";
        private static final String API_KEY = "a0bee184368e4a7aa6b7d9e4fc0c6358";

        public static List<NewsArticle> fetchNewsArticles(String category) {
            // Build the URL for the news articles API request
            Uri.Builder builder = Uri.parse(BASE_URL).buildUpon()
                    .appendPath("top-headlines")
                    .appendQueryParameter("country", "au")
                    .appendQueryParameter("category", category)
                    .appendQueryParameter("apiKey", API_KEY);
            String url = builder.build().toString();

            // Create a new HTTP client and request object
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                // Send the HTTP request and parse the response
                Response response = client.newCall(request).execute();
                String responseString = response.body().string();
                JSONObject responseJson = new JSONObject(responseString);
                JSONArray articlesJson = responseJson.getJSONArray("articles");
                List<NewsArticle> newsArticles = new ArrayList<>();
                for (int i = 0; i < articlesJson.length(); i++) {
                    JSONObject articleJson = articlesJson.getJSONObject(i);
                    String title = articleJson.getString("title");
                    String description = articleJson.getString("description");
                    String publishDate = articleJson.getString("publishedAt");
                    String imageUrl = articleJson.getString("urlToImage");
                    NewsArticle newsArticle = new NewsArticle(title, description, publishDate, imageUrl);
                    newsArticles.add(newsArticle);
                }
                return newsArticles;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}





