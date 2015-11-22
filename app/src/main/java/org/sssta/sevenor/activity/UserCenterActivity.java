package org.sssta.sevenor.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.sssta.sevenor.Constants;
import org.sssta.sevenor.R;
import org.sssta.sevenor.model.Person;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserCenterActivity extends AppCompatActivity {

    @Bind(R.id.user_center_text_name)
    TextView textUsername;
    @Bind(R.id.user_center_text_sex)
    TextView textSex;
    @Bind(R.id.user_center_image_sex)
    ImageView personalCenterHeadPic;
    private Context mContext;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        ButterKnife.bind(this);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        person = (Person) bundle.get(Constants.PERSON);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setElevation(0f);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
    }

    private void initView() {
        if (person != null) {
            textUsername.setText(person.getName());
            String[] strings = getResources().getStringArray(R.array.user_sex);
            textSex.setText(strings[person.getSex()]);
            Picasso.with(mContext).load(person.getImage_url()).placeholder(R.drawable.meeting_photo)
                    .error(R.drawable.meeting_photo).into(personalCenterHeadPic);
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
