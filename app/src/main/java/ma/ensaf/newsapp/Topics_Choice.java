package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Topics_Choice extends AppCompatActivity {
    AppCompatButton science, education, business,divertissement,santé,politique,sports,technologie,next;
    //List<AppCompatButton> categories = new ArrayList<>();
    DatabaseReference reference;
    FirebaseAuth mAuth;
    Categories categories= new Categories();
    Boolean isClicked=false;
    String text1="science";
    String text2="education";
    String text3="business";
    String text4="politics";
    String text5="sports";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_choice);

        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        String userId= currentUser.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("categories");
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
            int check = 1;
            @Override
            public void onClick(View view) {
              /* if(check == 1){
                    unclick(science);
                   Query queryRef = reference.orderByChild("text").equalTo(text1);
                   queryRef.addChildEventListener(new ChildEventListener() {
                       @Override
                       public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                           snapshot.getRef().setValue(null);
                       }
                   });
                    check = 0;
                }else{
                    click(science);
                    categories.setText(text1);
                    reference.push().setValue(categories);
                    check = 1;
                }*/


                if (isClicked==false){
                    click(science);
                    categories.setText(text1);
                    reference.push().setValue(categories);

                }

                //else{
                    //unclick(science);
                    //reference.removeValue();


               // }
                //isClicked=!isClicked;
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (isClicked==false){
                    click(education);
                categories.setText(text2);
                reference.push().setValue(categories);


                // }

                //else{
                   // unclick(education);
                    //reference.removeValue();

               // }
               // isClicked=!isClicked;

            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if (isClicked==false){
                    click(business);
                    categories.setText(text3);
                    reference.push().setValue(categories);
                //}

                //else{
                    //unclick(business);
                    //reference.removeValue();

              //  }
               // isClicked=!isClicked;

            }
        });
        politique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if (isClicked==false){
                    click(politique);
                    categories.setText(text4);
                    reference.push().setValue(categories);
               // }

               // else{
                  //  unclick(politique);
                   // reference.removeValue();

              //  }
              //  isClicked=!isClicked;

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Topics_Choice.this,"signed up successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Topics_Choice.this, notification.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (isClicked==false){
                    click(sports);
                    categories.setText(text5);
                    reference.push().setValue(categories);
               // }

                //else{
                  //  unclick(sports);
                    //reference.removeValue();

                //}
                //isClicked=!isClicked;

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