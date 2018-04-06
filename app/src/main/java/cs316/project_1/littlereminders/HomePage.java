package cs316.project_1.littlereminders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    @VisibleForTesting
    private static final String TAG = "EmailPassward" ;
    private FirebaseAuth mAuth;
    private Button signinButtton;
    private Button signupButtton;
    private EditText mEmailField;
    private EditText mPasswordField;
    public ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();

        signinButtton = (Button) findViewById(R.id.signin);
        signupButtton = (Button) findViewById(R.id.signup);
        mEmailField = (EditText) findViewById(R.id.emailData);
        mPasswordField = (EditText) findViewById(R.id.passdata2);


        findViewById(R.id.signin).setOnClickListener(this);
        findViewById(R.id.signup).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void startAct() {

        Intent gohome = new Intent(this, navigation.class);
        startActivity(gohome);
    }

    private void createNewUser(String email,String password)
    {
        // TODO: 4/3/2018 "validation"

        showProgressDialog();
        
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(HomePage.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startAct();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(HomePage.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void signIn(String email, String password) {

        // TODO: 4/3/2018 "Validation"

        showProgressDialog();
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startAct();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(HomePage.this, "Invalid Email or Password",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [START_EXCLUDE]

                        if (!task.isSuccessful()) {

                            Toast.makeText(HomePage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        hideProgressDialog();

                        // [END_EXCLUDE]

                    }

                });

        // [END sign_in_with_email]

    }

    private void signOut() {
        mAuth.signOut();
        Toast.makeText(HomePage.this, "You Have Signed Out",
                Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {

        int i = v.getId();

        if (i == R.id.signup) {

            createNewUser(mEmailField.getText().toString(), mPasswordField.getText().toString());

        } else if (i == R.id.signin) {

            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());

        } else if (i == R.id.sign_out_button) {

            signOut();

        }

    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }



    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
