package ma.ensaf.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements categoryRVAdapter.CategoryClickInterface {
      //912a86cdf5b04b28a8b30878886c422b
      FirebaseAuth mAuth;
      BottomNavigationView bottomBar;
      private RecyclerView newsRV,categoryRV;
      private ProgressBar loadingPB;
      private ArrayList<Articles> articlesArrayList;
      private ArrayList<CategoryRVModal> categoryRVModalArrayList;
      private categoryRVAdapter categoryRVAdapter;
      private NewsRVAdapter newsRVAdapter;

      ArrayList<String> chosenCateg = new ArrayList<String>();
      DatabaseReference ref;
    private ArrayList<Articles> mExampleList;
    String text1="science";
    String text2="education";
    String text3="business";
    String text4="politics";
    String text5="sports";
    String text6="technologie";
    String text7="divertissement";
    String text8="sant??";

    private RecyclerView mRecyclerView;
    private NewsRVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

      //ArrayList<String> chosenCateg = null;
      EditText keyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAlarm();

        newsRV= findViewById(R.id.idRVNews);
        categoryRV=findViewById(R.id.idRVCategories);
        loadingPB=findViewById(R.id.idPBLoading);
       // keyword=findViewById(R.id.edittextsearch);
        articlesArrayList= new ArrayList<>();
        categoryRVModalArrayList= new ArrayList<>();
        newsRVAdapter= new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter= new categoryRVAdapter(categoryRVModalArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getCategories();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();



        bottomBar = (BottomNavigationView) findViewById(R.id.bottomBar);
        bottomBar.setSelectedItemId(R.id.home);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.bookmark:
                        //Toast.makeText(MainActivity.this,"home", Toast.LENGTH_LONG).show();

                         Intent intent = new Intent(MainActivity.this, bookmarkActivity.class);
                         startActivity(intent);


                        break;
                    case R.id.search:
                        //Toast.makeText(MainActivity.this,"home", Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.settings:
                        //Toast.makeText(MainActivity.this,"settings", Toast.LENGTH_LONG).show();

                         Intent intent1 = new Intent(MainActivity.this, SettingsActivity.class);
                          startActivity(intent1);
                        break;

                    case R.id.home:
                        //Toast.makeText(MainActivity.this,"settings", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent2);
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "message par d??faut" , Toast.LENGTH_LONG).show();

                }
                return true;
            }
        });

        mAuth= FirebaseAuth.getInstance();

//        buildRecyclerView();
//
//        EditText editText = findViewById(R.id.edittextsearch);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    private void getCategories()
    {
        //ref= FirebaseDatabase.getInstance().getReference().child("text");
        //chosenCateg = new ArrayList<>();
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        String userId= currentUser.getUid();
        ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("categories");
          //ref = FirebaseDatabase.getInstance().getReference("categories");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chosenCateg = new ArrayList<String>();
                // Result will be holded Here

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String key  = dsp.getKey();
                    String category = dataSnapshot.child(key).child("text").getValue().toString();
                    chosenCateg.add(category); //add result into array list
                }

                if (chosenCateg.contains(text1)){
                    categoryRVModalArrayList.add(new CategoryRVModal("Science","https://media.istockphoto.com/photos/vaccine-in-laboratory-flu-shot-and-covid19-vaccination-picture-id1289345741?b=1&k=20&m=1289345741&s=170667a&w=0&h=oG8iaDNP4rOLSgXWfeSziU3Vyu6KJS9Hn2ORohzSsRg="));
                }
                if (chosenCateg.contains(text2)){
                    categoryRVModalArrayList.add(new CategoryRVModal("Education","https://media.istockphoto.com/photos/television-streaming-multimedia-wall-concept-picture-id1287677376?b=1&k=20&m=1287677376&s=170667a&w=0&h=wvu0lKn4WbfHtKId83KzrHvGmBP7zn7ZwGEWmU99HWE="));
                }
                if (chosenCateg.contains(text3)){
                    categoryRVModalArrayList.add(new CategoryRVModal("Business","https://media.istockphoto.com/photos/crisis-in-news-picture-id147036034?b=1&k=20&m=147036034&s=170667a&w=0&h=AwPgqLUXvbBfH_WI_rzpVc1VNfaFCPJmvDrgourmMbE="));
                }
                if (chosenCateg.contains(text4)){
                    categoryRVModalArrayList.add(new CategoryRVModal("Politics","https://media.istockphoto.com/photos/television-streaming-multimedia-wall-concept-picture-id1287677376?b=1&k=20&m=1287677376&s=170667a&w=0&h=wvu0lKn4WbfHtKId83KzrHvGmBP7zn7ZwGEWmU99HWE="));
                }
                if (chosenCateg.contains(text5)){
                    categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://media.istockphoto.com/photos/various-sport-equipments-on-grass-picture-id949190736?b=1&k=20&m=949190736&s=170667a&w=0&h=f3ofVqhbmg2XSVOa3dqmvGtHc4VLA_rtbboRGaC8eNo="));
                }
                if (chosenCateg.contains(text6)){
                    categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8dGVjaG5vbG9neXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"));
                }
                if (chosenCateg.contains(text7)){
                    categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://media.istockphoto.com/photos/media-concept-smart-tv-picture-id540834826?b=1&k=20&m=540834826&s=170667a&w=0&h=1RMIxPcdb_Z9gwzG6yMxIMbaN9gMyJWdjUE9-CQ8Ooo="));
                }
                if (chosenCateg.contains(text8)){
                    categoryRVModalArrayList.add(new CategoryRVModal("Health","https://media.istockphoto.com/photos/magazines-and-stethoscope-picture-id1296480132?b=1&k=20&m=1296480132&s=170667a&w=0&h=RXh-mJO4b4LiuKMqvew_l3qUA07JsM4UOOA6Ty7x9RU="));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
//       Bundle b=this.getIntent().getExtras();
//       ChosenCategories=b.getStringArrayList("key1");

        categoryRVModalArrayList.add(new CategoryRVModal("","https://media.istockphoto.com/photos/abstract-digital-news-concept-picture-id1290904409?b=1&k=20&m=1290904409&s=170667a&w=0&h=6khncht98kwYG-l7bdeWfBNs_GGcG1pDqzLb6ZXhh7I="));

        categoryRVAdapter.notifyDataSetChanged();
     }


    private void getNews(String category)
    {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
     // String url="https://newsapi.org/v2/top-headlines?country=ma&q="+keyword+"&apiKey=912a86cdf5b04b28a8b30878886c422b";
        String categoryUrl="https://newsapi.org/v2/top-headlines?country=ma&category="+category+"&apiKey=912a86cdf5b04b28a8b30878886c422b";
        String url="https://newsapi.org/v2/top-headlines?country=ma&exludeDomains=stackoverflow.com&sortBy=publishedAt&language=fr&apiKey=912a86cdf5b04b28a8b30878886c422b";
        String Base_url="https://newsapi.org/";
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi= retrofit.create(RetrofitApi.class);
        Call<NewsModal> call;
        if(category.equals("All"))
        {
            call=retrofitApi.getAllNews(url);
        }
        else
        {
            call= retrofitApi.getNewsByCategory(categoryUrl);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal= response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles= newsModal.getArticles();
                for(int i=0;i<articles.size();i++)
                {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }

                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail to get news",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onCategoryClick(int position)
    {
        String category= categoryRVModalArrayList.get(position).getCategory();
        getNews(category);

    }


public void myAlarm() {

    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.HOUR_OF_DAY, 16);
    calendar.set(Calendar.MINUTE, 54);
    calendar.set(Calendar.SECOND, 0);

    if (calendar.getTime().compareTo(new Date()) < 0)
        calendar.add(Calendar.DAY_OF_MONTH, 1);

    Intent intent = new Intent(getApplicationContext(), ReminderBroadcast.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    if (alarmManager != null) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

}
}