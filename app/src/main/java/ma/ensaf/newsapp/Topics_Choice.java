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
        science = (AppCompatButton) findViewById(R.id.science);
        education = (AppCompatButton) findViewById(R.id.education);
        business=(AppCompatButton) findViewById(R.id.business);
        divertissement=(AppCompatButton) findViewById(R.id.divertissement);
        santé=(AppCompatButton) findViewById(R.id.santé);
        politique=(AppCompatButton) findViewById(R.id.politique);
        sports=(AppCompatButton) findViewById(R.id.sport);
        technologie=(AppCompatButton) findViewById(R.id.tech);
        next= (AppCompatButton) findViewById(R.id.next_Btn);

        reference= FirebaseDatabase.getInstance().getReference().child("category");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    i=(int) snapshot.getChildrenCount();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(Topics_Choice.this,"Data not saved",Toast.LENGTH_SHORT).show();
            }
        });

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
            if(science.isSelected())
            {
                reference.child(String.valueOf(i+1)).setValue("Science");
            }
            else if(education.isSelected())
            {
                reference.child(String.valueOf(i+1)).setValue("Education");
            }
            else if(business.isSelected())
            {
                reference.child(String.valueOf(i+1)).setValue("Business");
            }
            else if(politique.isSelected())
            {
                reference.child(String.valueOf(i+1)).setValue("Politique");
            }
            else if(sports.isSelected())
            {
                reference.child(String.valueOf(i+1)).setValue("Sports");
            }
           }
       });

    }
}