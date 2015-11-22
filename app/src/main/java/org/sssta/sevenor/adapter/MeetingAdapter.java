package org.sssta.sevenor.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sssta.sevenor.Constants;
import org.sssta.sevenor.R;
import org.sssta.sevenor.activity.PersonMeetingActivity;
import org.sssta.sevenor.model.Person;
import org.sssta.sevenor.ui.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林韬 on 2015/11/21.
 */
public class MeetingAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private Context mContext;
    private List<Person> persons;

    private ArrayList<Integer> chImageIdList = new ArrayList<>();

    public MeetingAdapter(List<Person> persons, Context context) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        this.persons = persons;
        int firstId = R.drawable.meeting_item_bg_five;
        chImageIdList.add(firstId);
        for (int i=1;i<7;i++) {
            chImageIdList.add(firstId+i);
        }
        persons.get(0).setResourceId(R.drawable.ic_native_1);
        persons.get(1).setResourceId(R.drawable.ic_native_2);
        persons.get(2).setResourceId(R.drawable.ic_native_3);
        persons.get(3).setResourceId(R.drawable.ic_native_4);
        persons.get(4).setResourceId(R.drawable.ic_native_3);
        persons.get(5).setResourceId(R.drawable.ic_native_2);
        persons.get(6).setResourceId(R.drawable.ic_native_1);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.meeting_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (position == 0 || position == 8) {
            myViewHolder.itemView.setVisibility(View.INVISIBLE);
        } else {
            myViewHolder.itemView.setVisibility(View.VISIBLE);
            Person person = persons.get(position-1);
            myViewHolder.userName.setText(person.getName());
            myViewHolder.userName.setAlpha(0.0f);
            myViewHolder.chImage.setImageResource(chImageIdList.get(position-1));
//            Picasso.with(mContext).load(person.getImage_url()).placeholder(R.drawable.user_image).into(myViewHolder.userImage);
            Picasso.with(mContext).load(person.getResourceId()).into(myViewHolder.userImage);
        }

    }

    @Override
    public int getItemCount() {
        return persons.size()+2;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CircleImageView userImage;
        public ImageView chImage;
        public TextView userName;

        public MyViewHolder(View itemView) {
            super(itemView);
            userImage = (CircleImageView) itemView.findViewById(R.id.meeting_item_user_image);
            chImage = (ImageView) itemView.findViewById(R.id.ch_image);
            userName = (TextView) itemView.findViewById(R.id.meeting_item_user_name);
            userImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = MyViewHolder.this.getLayoutPosition();
            Intent intent = new Intent(mContext, PersonMeetingActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.PERSON,persons.get(position-1));
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
    }

}
