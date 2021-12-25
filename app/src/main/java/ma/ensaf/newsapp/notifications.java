package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class notifications extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseAuth mAuth;
    notification notif;
    TextView ignorenotif;
    AppCompatButton next_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ignorenotif=findViewById(R.id.ignore_notif);
        next_activity=findViewById(R.id.next_Btn);
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        String userId= currentUser.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("notifications");
        notif=new notification();
        ignorenotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notif.setNotification("Off");
                reference.push().setValue(notif);
                Intent intent = new Intent(notifications.this, MainActivity.class);
               // intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        next_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notif.setNotification("On");
                reference.push().setValue(notif);
                Intent intent = new Intent(notifications.this, MainActivity.class);
                // intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}