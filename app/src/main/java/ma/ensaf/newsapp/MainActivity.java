package ma.ensaf.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
      //912a86cdf5b04b28a8b30878886c422b
      BottomNavigationView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomBar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        Toast.makeText(MainActivity.this,"home", Toast.LENGTH_LONG).show();
                      //  Intent intent = new Intent(MainActivity.this, home.class);
                      //  startActivity(intent);

                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this,"settings", Toast.LENGTH_LONG).show();
                      //  Intent intent1 = new Intent(MainActivity.this, home.class);
                      //  startActivity(intent1);


                        break;


                    default:
                        Toast.makeText(MainActivity.this, "message par défaut" , Toast.LENGTH_LONG).show();

                }
                return true;
            }
        });


    }
}