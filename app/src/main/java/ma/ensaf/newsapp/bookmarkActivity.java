package ma.ensaf.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bookmarkActivity extends AppCompatActivity {
   // String title,content,desc,imageUrl,url;
    private TextView titleTV, subDescTV, contentTV;
    private ImageView newsIV;
    private RecyclerView favsRV;
    private ArrayList<Articles> articlesArrayList;
    private FavsRVAdapter favsRVAdapter;
    private ImageButton bookmarkBTN;
    private DatabaseReference reference;
    BottomNavigationView bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomBar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.bookmark:

                        //Toast.makeText(MainActivity.this,"home", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(bookmarkActivity.this, bookmarkActivity.class);
                        startActivity(intent);

                        break;
                    case R.id.settings:
                        //Toast.makeText(MainActivity.this,"settings", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(bookmarkActivity.this, SettingsActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.home:
                        //Toast.makeText(MainActivity.this,"settings", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(bookmarkActivity.this, MainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.search:
                        //Toast.makeText(MainActivity.this,"home", Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent(bookmarkActivity.this, SearchActivity.class);
                        startActivity(intent3);
                        break;


                    default:
                        Toast.makeText(bookmarkActivity.this, "message par défaut" , Toast.LENGTH_LONG).show();

                }
                return true;
            }
        });

        titleTV = findViewById(R.id.idTVTitle);
        subDescTV = findViewById (R.id.idTVSubDesc);
        contentTV = findViewById(R.id.idTVContent);
        newsIV = findViewById(R.id.idIVNews);
        favsRV= findViewById(R.id.idRVFavs);
        articlesArrayList= new ArrayList<>();
        favsRVAdapter= new FavsRVAdapter(articlesArrayList,this);
        favsRV.setLayoutManager(new LinearLayoutManager(this));
        favsRV.setAdapter(favsRVAdapter);
        getNews();
        favsRVAdapter.notifyDataSetChanged();


            }

    private void getNews()
    {
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        String userId= currentUser.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("favoris");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if((snapshot.exists()))
                {
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String key = ds.getKey();
                        String titleStr = snapshot.child(key).child("title").getValue().toString();
                        String descStr = snapshot.child(key).child("subtitle").getValue().toString();
                        String contentStr = snapshot.child(key).child("content").getValue().toString();
                        String img = snapshot.child(key).child("imageUrl").getValue().toString();
                        String url = snapshot.child(key).child("url").getValue().toString();

                        //titleTV.setText(titleStr);
                        //subDescTV.setText(descStr);
                        //contentTV.setText(contentStr);
                        //Picasso.get().load(img).into(newsIV);


                        articlesArrayList.add(new Articles(titleStr,descStr,img,url,contentStr));
                        favsRVAdapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}