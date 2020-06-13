package com.example.madspild.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madspild.MainActivity;
import com.example.madspild.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;
import java.util.List;

public class FirebaseActivity extends AppCompatActivity {

  private static final int RC_SIGN_IN = 123;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    checkIfSignedIn();
    setContentView(R.layout.firebase_sign_in);
    List<AuthUI.IdpConfig> providers = Arrays.asList(
      new AuthUI.IdpConfig.EmailBuilder().build(),
      new AuthUI.IdpConfig.PhoneBuilder().build(),
      new AuthUI.IdpConfig.GoogleBuilder().build());

    Intent signInIntent = AuthUI.getInstance()
      .createSignInIntentBuilder()
      .setAvailableProviders(providers)
      .build();

    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  private void checkIfSignedIn() {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if (user != null)
      goToMainActivity();
  }

  private void goToMainActivity() {
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_SIGN_IN) {
      handleSignIn(resultCode);
    }
  }

  private void handleSignIn(int resultCode) {
    if (resultCode == RESULT_OK) {
      goToMainActivity();
    } else {
      Toast.makeText(this, "Could not Sign in", Toast.LENGTH_SHORT).show();
    }
  }
}
