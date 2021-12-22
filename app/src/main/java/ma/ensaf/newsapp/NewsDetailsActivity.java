package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewsDetailsActivity extends AppCompatActivity {
String title,content,desc,imageUrl,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        title= getIntent().getStringExtra("title");
        content= getIntent().getStringExtra("content");
        desc= getIntent().getStringExtra("desc");
        imageUrl= getIntent().getStringExtra("image");
        url= getIntent().getStringExtra("url");

    }
}