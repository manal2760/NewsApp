package ma.ensaf.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    private  LinearLayout lin1;
    private  LinearLayout linpref;
    private RelativeLayout rel1;
    private RelativeLayout aboutRel;
    private RelativeLayout faq;
    ImageView ProfilePicture;
    FirebaseAuth mAuth;
    AppCompatButton modifierprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ProfilePicture= findViewById(R.id.profilepicture);
        lin1= (LinearLayout) findViewById(R.id.linId);
        linpref= (LinearLayout) findViewById(R.id.linPref);
        rel1= (RelativeLayout) findViewById(R.id.relaId);
        aboutRel=(RelativeLayout)findViewById(R.id.about);
        faq=(RelativeLayout)findViewById(R.id.faq);
        modifierprofile=findViewById(R.id.modifierprofile);
        mAuth= FirebaseAuth.getInstance();
        linpref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, modifCategActivity.class));
                finish();

            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, FaqActivity.class));
                finish();

            }
        });
        aboutRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, aboutActivity.class));
                finish();
            }
        });
        modifierprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);

            }
        });
        rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));

            }
        });

        lin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageuri = data.getData();
                ProfilePicture.setImageURI(imageuri);
            }
        }
    }

}