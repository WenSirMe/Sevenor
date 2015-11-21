package org.sssta.sevenor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.sssta.sevenor.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserCenterActivity extends AppCompatActivity {

    @Bind(R.id.user_center_text_name)
    TextView userCenterTextName;
    @Bind(R.id.user_center_text_sex)
    TextView userCenterTextSex;
    @Bind(R.id.user_center_image_sex)
    ImageView userCenterImageSex;
    private Context mContext;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        intent = getIntent();
        ButterKnife.bind(this);
        mContext = this;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setElevation(0f);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usercenter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting_user) {
            Toast.makeText(mContext, "seeting", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
