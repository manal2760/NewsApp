package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class aboutActivity extends AppCompatActivity {
    private RelativeLayout rel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        rel1= (RelativeLayout) findViewById(R.id.relaId);

        rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(aboutActivity.this, SettingsActivity.class));

            }
        });
    }
}