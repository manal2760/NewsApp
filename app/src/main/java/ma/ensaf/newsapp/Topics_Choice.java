package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Topics_Choice extends AppCompatActivity {
    AppCompatButton science, education, business,divertissement,santé,politique,sports,technologie;
    List<AppCompatButton> categories = new ArrayList<>();
    Boolean isClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_choice);
        science = (AppCompatButton) findViewById(R.id.science);
        education = (AppCompatButton) findViewById(R.id.education);
        business=(AppCompatButton) findViewById(R.id.business);
        divertissement=(AppCompatButton) findViewById(R.id.divertissement);
        santé=(AppCompatButton) findViewById(R.id.santé);
        politique=(AppCompatButton) findViewById(R.id.politique);
        sports=(AppCompatButton) findViewById(R.id.sport);
        technologie=(AppCompatButton) findViewById(R.id.tech);

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClicked==false){
                    click(science);
                }

                else{
                    unclick(science);
                    categories.remove(science);

                }
                isClicked=!isClicked;
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                education.setBackgroundResource(R.drawable.onclick_pill_button);
                education.setTextColor(getResources().getColor(R.color.dark1));

            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                business.setBackgroundResource(R.drawable.onclick_pill_button);
                business.setTextColor(getResources().getColor(R.color.dark1));

            }
        });
        politique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                politique.setBackgroundResource(R.drawable.onclick_pill_button);
                politique.setTextColor(getResources().getColor(R.color.dark1));

            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sports.setBackgroundResource(R.drawable.onclick_pill_button);
                sports.setTextColor(getResources().getColor(R.color.dark1));

            }
        });

    }
    public void click(AppCompatButton b){
        b.setBackgroundResource(R.drawable.onclick_pill_button);
        b.setTextColor(getResources().getColor(R.color.dark1));
    }
    public void unclick(AppCompatButton b){
        b.setBackgroundResource(R.drawable.pill_button);
        b.setTextColor(getResources().getColor(R.color.grey1));
    }
}