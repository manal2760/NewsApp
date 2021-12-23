package ma.ensaf.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class signUpTabFragment extends Fragment {
AppCompatButton signUp;
FirebaseAuth mAuth;
EditText mail, password;
DatabaseReference ref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        mAuth=FirebaseAuth.getInstance();
        mail= (EditText)root.findViewById(R.id.email);
        password=(EditText)root.findViewById(R.id.pass);
        signUp= (AppCompatButton)root.findViewById(R.id.signup);

        signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               createUser();
           }
       });
        return root;
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
                        String UserID =  FirebaseAuth.getInstance().getCurrentUser().getUid();
                        Map<String, Object> map = new HashMap<>();

                        map.put("email", mail);
                        map.put("password", password);
                        ref.child("users").child(UserID ).setValue(map);

                        Toast.makeText(getActivity(),"user crated successfully",Toast.LENGTH_SHORT).show();
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
