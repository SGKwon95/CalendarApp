package com.example.calendarapp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class AuthActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Button getAuthButton = (Button)findViewById(R.id.button2);
        getAuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setTheme(AuthUI.getDefaultTheme())
                        .setLogo(AuthUI.NO_LOGO)
                        .setAvailableProviders(getSelectedProviders())
                        .setTosAndPrivacyPolicyUrls(
                                "https://naver.com",
                                "https://google.com")
                        .setIsSmartLockEnabled(true)
                        .build(),
                RC_SIGN_IN
        );
    }

    private List<AuthUI.IdpConfig> getSelectedProviders()
    {
        List<AuthUI.IdpConfig> selectedProviders = new ArrayList<>();
        CheckBox googlechk = (CheckBox)findViewById(R.id.google_provider);

        if(googlechk.isChecked())
            selectedProviders.add(new AuthUI.IdpConfig.GoogleBuilder().build());

        return selectedProviders;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if(requestCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            } else {
                //sign in failed
            }
        }
    }
}