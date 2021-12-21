package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Topics_Choice extends AppCompatActivity {
    AppCompatButton science, education, business,divertissement,santé,politique,sports,technologie;
    //List<AppCompatButton> categories = new ArrayList<>();
    DatabaseReference reference;
    FirebaseAuth mAuth;
    Categories categories= new Categories();
    String text1="science";
    String text2="education";
    String text3="business";
    String text4="politique";
    String text5="sports";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_choice);
        reference= FirebaseDatabase.getInstance().getReference().child("categories");
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
                Boolean isClicked1=false;

                if (isClicked1==false){
                    click(science);
                    categories.setText(text1);
                    reference.push().setValue(categories);
                }

                else{
                    unclick(science);
                    reference.child(text1).removeValue();

                }
                isClicked1=!isClicked1;
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isClicked2=false;
                if (isClicked2==false){
                    click(science);
                    categories.setText(text2);
                    reference.push().setValue(categories);
                }

                else{
                    unclick(science);
                    reference.child(text2).removeValue();

                }
                isClicked2=!isClicked2;

            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isClicked3=false;
                if (isClicked3==false){
                    click(science);
                    categories.setText(text3);
                    reference.push().setValue(categories);
                }

                else{
                    unclick(science);
                    reference.child(text3).removeValue();

                }
                isClicked3=!isClicked3;

            }
        });
        politique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isClicked4=false;
                if (isClicked4==false){
                    click(science);
                    categories.setText(text4);
                    reference.push().setValue(categories);
                }

                else{
                    unclick(science);
                    reference.child(text4).removeValue();

                }
                isClicked4=!isClicked4;

            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isClicked5=false;
                if (isClicked5==false){
                    click(science);
                    categories.setText(text5);
                    reference.push().setValue(categories);
                }

                else{
                    unclick(science);
                    reference.child(text5).removeValue();

                }
                isClicked5=!isClicked5;

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