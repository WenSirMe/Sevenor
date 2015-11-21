package org.sssta.sevenor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sssta.sevenor.Constants;
import org.sssta.sevenor.R;
import org.sssta.sevenor.model.Person;
import org.sssta.sevenor.ui.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonMeetingActivity extends AppCompatActivity {

    @Bind(R.id.meeting_text_userName)
    TextView textUsername;
    @Bind(R.id.meeting_text_sex)
    TextView textSex;
    @Bind(R.id.user_center_image_sex)
    ImageView imageSex;
    @Bind(R.id.meeting_head_pic)
    CircleImageView meetingHeadPic;

    private Person person;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_meeting);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        person = (Person) bundle.get(Constants.PERSON);
        initView();
    }

    void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setElevation(0f);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (person != null) {
            textUsername.setText(person.getName());
            textSex.setText(Constants.sexs[person.getSex()]);
            Picasso.with(mContext).load(person.getImage_url()).placeholder(R.drawable.personal_center_photo)
                    .error(R.drawable.personal_center_photo).into(meetingHeadPic);
        }
    }

}
