package ma.ensaf.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Topics_Choice extends AppCompatActivity {
    AppCompatButton science, education, business,divertissement,santé,politique,sports,technologie,next;
    //List<AppCompatButton> categories = new ArrayList<>();
    DatabaseReference reference;
    int i=0;
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
        next= (AppCompatButton) findViewById(R.id.next_Btn);





        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                science.setBackgroundResource(R.drawable.onclick_pill_button);
                science.setTextColor(getResources().getColor(R.color.dark1));
                //categories.add(science);

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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String text1= "science";
                String text2= "education";
                String text3= "business";
                String text4= "politics";
                String text5= "sports";
                Categories categories= new Categories();

                if(science.isSelected())
                {
                    categories.setText(text1);
                    reference.push().setValue(categories);
                }
                else if(education.isSelected())
                {
                    categories.setText(text2);
                    reference.push().setValue(categories);
                }
                else if(business.isSelected())
                {
                    categories.setText(text3);
                    reference.push().setValue(categories);
                }
                else if(politique.isSelected())
                {
                    categories.setText(text4);
                    reference.push().setValue(categories);
                }
                else if(sports.isSelected())
                {
                    categories.setText(text5);
                    reference.push().setValue(categories);
                }
            }
        });


    }
}