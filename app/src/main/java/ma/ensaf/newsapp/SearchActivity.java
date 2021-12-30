package ma.ensaf.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    BottomNavigationView bottomBar;
    FirebaseAuth mAuth;
    EditText searchKeyword;
    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> SearchArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private categoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchKeyword=findViewById(R.id.edittextsearch);
        newsRV= findViewById(R.id.idSearchNews);
        loadingPB=findViewById(R.id.idPBLoading);
        SearchArrayList= new ArrayList<>();
        categoryRVModalArrayList= new ArrayList<>();
        newsRVAdapter= new NewsRVAdapter(SearchArrayList,this);
       // categoryRVAdapter = new categoryRVAdapter(categoryRVModalArrayList,this,this::)
      //  categoryRVAdapter= new categoryRVAdapter(categoryRVModalArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
       // getCategories();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomBar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.bookmark:
                        //Toast.makeText(MainActivity.this,"home", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SearchActivity.this, bookmarkActivity.class);
                        startActivity(intent);


                        break;

                    case R.id.settings:
                        //Toast.makeText(MainActivity.this,"settings", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(SearchActivity.this, SettingsActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.home:
                        //Toast.makeText(MainActivity.this,"settings", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(SearchActivity.this, MainActivity.class);
                        startActivity(intent2);

                        break;
                    case R.id.search:
                        Intent intent3 = new Intent(SearchActivity.this, SearchActivity.class);
                        startActivity(intent3);
                        item.isCheckable();
                        break;



                    default:
                        Toast.makeText(SearchActivity.this, "message par défaut" , Toast.LENGTH_LONG).show();

                }
                return true;
            }
        });
        mAuth= FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(SearchActivity.this, LoginActivity.class));
        }
    }
    private void getNews(String category)
    {
        loadingPB.setVisibility(View.VISIBLE);
        SearchArrayList.clear();
       // String categoryUrl="https://newsapi.org/v2/top-headlines?country=ma&category="+category+"&apiKey=912a86cdf5b04b28a8b30878886c422b";
        String url="https://newsapi.org/v2/everything?q="+ searchKeyword+"&apiKey=apiKey=912a86cdf5b04b28a8b30878886c422b";
        String Base_url="https://newsapi.org/";
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi= retrofit.create(RetrofitApi.class);
        Call<NewsModal> call;
        call=retrofitApi.getAllNews(url);
      /*  if(category.equals("All"))
        {
            call=retrofitApi.getAllNews(url);
        }
        else
        {
            call= retrofitApi.getNewsByCategory(categoryUrl);
        }*/

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal= response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles= newsModal.getArticles();
                for(int i=0;i<articles.size();i++)
                {
                    SearchArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }

                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(SearchActivity.this,"Fail to get news",Toast.LENGTH_SHORT).show();
            }
        });

    }
}