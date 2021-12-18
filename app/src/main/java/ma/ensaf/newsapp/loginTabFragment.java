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

public class loginTabFragment extends Fragment
{
    AppCompatButton login;
    FirebaseAuth mAuth;
    EditText mail, password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        mAuth=FirebaseAuth.getInstance();
        mail= (EditText)root.findViewById(R.id.email);
        password=(EditText)root.findViewById(R.id.pass);
        login= (AppCompatButton)root.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        return root;
    }

    private void loginUser()
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
                mAuth.signInWithEmailAndPassword(mailStr,passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getActivity(),"Logged in successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
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