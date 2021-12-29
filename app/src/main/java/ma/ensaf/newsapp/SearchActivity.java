package ma.ensaf.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {
    BottomNavigationView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
    }
}