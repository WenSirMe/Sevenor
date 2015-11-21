package org.sssta.sevenor.activity.fragment;

import android.app.usage.ConfigurationStats;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;

import org.sssta.sevenor.Constants;
import org.sssta.sevenor.R;
import org.sssta.sevenor.adapter.MeetingAdapter;
import org.sssta.sevenor.model.Person;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Use the {@link MeetingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingFragment extends Fragment {

    private int centerX;
    private int halfItemWidth;

    public MeetingFragment() {
        centerX = Constants.window_width/2;
    }



    private RecyclerView mRecyclerView;
    private Context mContext;
    private LinearLayoutManager layoutManager;

    private AlphaAnimation alphaAnimation;

    private int index = 0;
    private int scaleRange = 200;
    private int firstVisibilityIndex=0,lastvisibilityyIndex=2;
    private ScaleAnimation scaleAnimation;
    private final float aFloat1 = 1.0f/scaleRange,aFloat2 = 0.2f/scaleRange;

    MeetingAdapter.MyViewHolder frontView;

    ArrayList<View> viewArrayList;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingFragment newInstance(String s1,String s2) {
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);
        initFragment(view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    void initFragment(final View view) {
        alphaAnimation = new AlphaAnimation(0f,1f);
        alphaAnimation.setDuration(500);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.meeting_recyclerView);
        viewArrayList = new ArrayList<>();
        List<Person> persons;
        // 初始化persons ，删除下面那句代码
        persons = new ArrayList<>();
        for (int i=1;i<8;i++) {
            persons.add(new Person("person"+i,null,0));
        }
        MeetingAdapter meetingAdapter = new MeetingAdapter(persons,mContext);
        layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(meetingAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (viewArrayList.size()<1) {
                    for (int i=0;i<3;i++) {
                        viewArrayList.add(layoutManager.getChildAt(i));
                    }
                }
                if (halfItemWidth <1) {
                    halfItemWidth = viewArrayList.get(0).getWidth()/2;
                }
                Log.d("position",layoutManager.findFirstVisibleItemPosition()+"");
                int first_position = layoutManager.findFirstVisibleItemPosition();
                int last_position = layoutManager.findLastVisibleItemPosition();
                if (firstVisibilityIndex < first_position) {
                    firstVisibilityIndex = first_position;
                    viewArrayList.remove(0);
                    viewArrayList.add(layoutManager.findViewByPosition(firstVisibilityIndex+2));
                    if (!viewArrayList.get(1).equals(layoutManager.findViewByPosition(firstVisibilityIndex+1))) {
                        viewArrayList.remove(1);
                        viewArrayList.add(1,layoutManager.findViewByPosition(firstVisibilityIndex+1));
                    }
                    lastvisibilityyIndex=firstVisibilityIndex+2;
                } else if (lastvisibilityyIndex > last_position) {
                    lastvisibilityyIndex = last_position;
                    viewArrayList.remove(2);
                    viewArrayList.add(0,layoutManager.findViewByPosition(lastvisibilityyIndex-2));
                    if (!viewArrayList.get(1).equals(lastvisibilityyIndex-1)) {
                        viewArrayList.remove(1);
                        viewArrayList.add(1,layoutManager.findViewByPosition(lastvisibilityyIndex-1));
                    }
                    firstVisibilityIndex=lastvisibilityyIndex-2;
                }
                for (View view1:viewArrayList) {
                    if (view1!=null) {
                        float difference = view1.getX()+halfItemWidth-centerX;
                        MeetingAdapter.MyViewHolder viewHolder = (MeetingAdapter.MyViewHolder) recyclerView.getChildViewHolder(view1);
                        if (Math.abs(difference)<scaleRange) {
                            float f = scaleRange-Math.abs(difference);
                            viewHolder.userName.setAlpha(aFloat1*f);
                            Log.d("diffrences",difference+"");
                            view1.setScaleX(1 + aFloat2*f);
                            view1.setScaleY(1+ aFloat2*f);
                        } else {
                            viewHolder.userName.setAlpha(0.0f);
                            view1.setScaleX(1);
                            view1.setScaleY(1);
                        }
                    }
                }
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
