package ma.ensaf.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.DatabaseError;
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
    ArrayList<String> ChosenCategories= new ArrayList<String>();
    Boolean isClicked=false;
    String text1="science";
    String text2="education";
    String text3="business";
    String text4="politics";
    String text5="sports";
    String text6="technologie";
    String text7="divertissement";
    String text8="santé";




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
            int check=0;
            @Override
            public void onClick(View view) {
               if(check == 1){
                   unclick(science);
                   ChosenCategories.remove(text1);
                   Query queryRef = reference.orderByChild("text").equalTo(text1);
                   queryRef.addChildEventListener(new ChildEventListener() {
                       @Override
                       public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                           snapshot.getRef().setValue(null);
                       }

                       @Override
                       public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                       }

                       @Override
                       public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                       }

                       @Override
                       public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });
                    check = 0;
                }else{
                    click(science);
                    ChosenCategories.add(text1);
                    categories.setText(text1);
                    reference.push().setValue(categories);
                    check = 1;
                }
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            int check1=0;
            @Override
            public void onClick(View view) {
                if(check1 == 1){
                    unclick(education);
                    ChosenCategories.remove(text2);
                    Query queryRef = reference.orderByChild("text").equalTo(text2);
                    queryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                            snapshot.getRef().setValue(null);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    check1 = 0;
                }else{
                    click(education);
                    ChosenCategories.add(text2);
                    categories.setText(text2);
                    reference.push().setValue(categories);
                    check1 = 1;
                }
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            int check=0;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    ChosenCategories.remove(text3);
                    unclick(business);
                    Query queryRef = reference.orderByChild("text").equalTo(text3);
                    queryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                            snapshot.getRef().setValue(null);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    check = 0;
                }else{
                    click(business);
                    ChosenCategories.add(text3);
                    categories.setText(text3);
                    reference.push().setValue(categories);
                    check = 1;
                }
            }
        });
        politique.setOnClickListener(new View.OnClickListener() {
            int check=0;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    unclick(politique);
                    ChosenCategories.remove(text4);
                    Query queryRef = reference.orderByChild("text").equalTo(text4);
                    queryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                            snapshot.getRef().setValue(null);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    check = 0;
                }else{
                    click(politique);
                    ChosenCategories.add(text4);
                    categories.setText(text4);
                    reference.push().setValue(categories);
                    check = 1;
                }
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            int check=0;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    unclick(sports);
                    ChosenCategories.remove(text5);
                    Query queryRef = reference.orderByChild("text").equalTo(text5);
                    queryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                            snapshot.getRef().setValue(null);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    check = 0;
                }else{
                    click(sports);
                    ChosenCategories.add(text5);
                    categories.setText(text5);
                    reference.push().setValue(categories);
                    check = 1;
                }
            }
        });
        technologie.setOnClickListener(new View.OnClickListener() {
            int check=0;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    unclick(technologie);
                    ChosenCategories.remove(text6);
                    Query queryRef = reference.orderByChild("text").equalTo(text6);
                    queryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                            snapshot.getRef().setValue(null);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    check = 0;
                }else{
                    click(technologie);
                    ChosenCategories.add(text6);
                    categories.setText(text6);
                    reference.push().setValue(categories);
                    check = 1;
                }
            }
        });
        divertissement.setOnClickListener(new View.OnClickListener() {
            int check=0;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    unclick(divertissement);
                    ChosenCategories.remove(text7);
                    Query queryRef = reference.orderByChild("text").equalTo(text7);
                    queryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                            snapshot.getRef().setValue(null);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    check = 0;
                }else{
                    click(divertissement);
                    ChosenCategories.add(text7);
                    categories.setText(text7);
                    reference.push().setValue(categories);
                    check = 1;
                }
            }
        });
        santé.setOnClickListener(new View.OnClickListener() {
            int check=0;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    unclick(santé);
                    ChosenCategories.remove(text8);
                    Query queryRef = reference.orderByChild("text").equalTo(text8);
                    queryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                            snapshot.getRef().setValue(null);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    check = 0;
                }else{
                    click(santé);
                    ChosenCategories.add(text8);
                    categories.setText(text8);
                    reference.push().setValue(categories);
                    check = 1;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Topics_Choice.this,"signed up successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Topics_Choice.this, notifications.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle b=new Bundle();
                b.putStringArrayList("key", ChosenCategories);
                intent.putExtras(b);
                startActivity(intent);
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