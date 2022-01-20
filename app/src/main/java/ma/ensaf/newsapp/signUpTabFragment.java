package ma.ensaf.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class signUpTabFragment extends Fragment {
AppCompatButton signUp;
FirebaseAuth mAuth;
EditText mail, password;
private DatabaseReference rootRef;
FloatingActionButton google;
GoogleSignInClient mGoogleSignInClient;
private static int RC_SIGN_IN=100;
private Button btn;

//    @Override
//       public void onStart () {
//        super.onStart ();
//        FirebaseUser user= mAuth.getCurrentUser();
//        if (user!=null) {
//            Intent intent = new Intent(getActivity(), Topics_Choice.class);
//            startActivity(intent);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mAuth=FirebaseAuth.getInstance();
        rootRef= FirebaseDatabase.getInstance().getReference();
        mail= (EditText)root.findViewById(R.id.email);
        password=(EditText)root.findViewById(R.id.pass);
        signUp= (AppCompatButton)root.findViewById(R.id.signup);
        google=(FloatingActionButton)root.findViewById(R.id.fab_google);



        signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               createUser();
           }
       });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        return root;
    }




    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getActivity(), Topics_Choice.class);
                            startActivity(intent);

                        } else {

                        }
                    }
                });
    }




    private void createUser()
    {
        String mailStr= mail.getText().toString();
        String passwordStr= password.getText().toString();

        if(TextUtils.isEmpty(mailStr))
        {
            mail.setError("Email cannot be empty");
            mail.requestFocus();
        }
        else if(TextUtils.isEmpty(passwordStr))
        {
            password.setError("Password cannot be empty");
            password.requestFocus();
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(mailStr,passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        String currentUserId= mAuth.getCurrentUser().getUid();
                        rootRef.child("Users").child(currentUserId).child("categories").setValue("");
                        rootRef.child("Users").child(currentUserId).child("favoris").setValue("");

                       // Toast.makeText(getActivity(),"user created successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), Topics_Choice.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getActivity(),"ERROR: "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }
}
