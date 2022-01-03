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
    AppCompatButton nextNotifOn;
    TextView nextNotifOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        nextNotifOn=findViewById(R.id.next_Btn);
        nextNotifOff=findViewById(R.id.ignore_notif);
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        String userId= currentUser.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("notifications");


        nextNotifOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification not = new notification();
                not.setNotification("Off");
                reference.push().setValue(not);
                Intent intent = new Intent(notifications.this, MainActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        nextNotifOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification not = new notification();
                not.setNotification("On");
                reference.push().setValue(not);
                Intent intent = new Intent(notifications.this, MainActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}