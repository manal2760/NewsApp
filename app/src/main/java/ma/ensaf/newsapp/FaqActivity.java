package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class FaqActivity extends AppCompatActivity {
    private RelativeLayout relFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        relFaq= (RelativeLayout) findViewById(R.id.relaId2);

        relFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FaqActivity.this, SettingsActivity.class));

            }
        });
    }
}