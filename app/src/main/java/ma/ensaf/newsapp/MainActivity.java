package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
      //912a86cdf5b04b28a8b30878886c422b
      FirebaseAuth mAuth;
      Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        //logout= (Button) findViewById(R.id.logoutBTN);
        //logout.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   mAuth.signOut();
              //  startActivity(new Intent(MainActivity.this, LoginActivity.class));
           // }
       // });
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
}