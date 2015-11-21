package org.sssta.sevenor.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.util.Log;
import android.widget.ImageView;

import org.sssta.sevenor.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageReadActivity extends AppCompatActivity {

    @Bind(R.id.read_image_view)
    ImageView readImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_read);
        ButterKnife.bind(this);
        getWindow().setSharedElementEnterTransition(new ChangeBounds());
        getWindow().setSharedElementReturnTransition(new ChangeBounds());
        readImageView.setImageResource(getIntent().getIntExtra("resId",0));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
}
