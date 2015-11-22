package org.sssta.sevenor.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.sssta.sevenor.R;
import org.sssta.sevenor.util.ContentUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArticleReadActivity extends AppCompatActivity {

    @Bind(R.id.article_read_text)
    TextView articleReadText;
    @Bind(R.id.article_read_title)
    TextView articleReadTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_read);
        ButterKnife.bind(this);
        articleReadText.setText(ContentUtil
                .getTextContent()
                .get(getIntent().getIntExtra("index", 0))
                .getText());
        articleReadTitle.setText(ContentUtil
                .getTextContent()
                .get(getIntent().getIntExtra("index", 0))
                .getTitle());
    }

}
