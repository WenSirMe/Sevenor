package org.sssta.sevenor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.sssta.sevenor.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.welcome_button)
    Button welcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        final SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHAREDPREFERENCES, Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean(Constants.ISFIRSTLOGIN,false);
        if (!isLogin) {
            welcomeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    SharedPreferences.Editor mEditor = sharedPreferences.edit();
                    mEditor.putBoolean(Constants.ISFIRSTLOGIN,true);
                    mEditor.apply();
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
