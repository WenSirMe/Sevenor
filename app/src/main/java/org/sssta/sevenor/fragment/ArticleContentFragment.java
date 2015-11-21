package org.sssta.sevenor.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.sssta.sevenor.R;
import org.sssta.sevenor.activity.ArticleReadActivity;
import org.sssta.sevenor.util.ContentUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArticleContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArticleContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleContentFragment extends BaseFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int index;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ArticleContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleContentFragment newInstance(int param1, String param2) {
        ArticleContentFragment fragment = new ArticleContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_about_us2, container, false);
//        View textView = v.findViewById(R.id.about_us_text);
//        ViewGroup vp = (ViewGroup)textView.getParent();
//        vp.setOnClickListener(this);
//        if (index == 2)
//            vp.setBackgroundResource(R.drawable.bg_about_pager_pink);
//        else
//            vp.setBackgroundResource(R.drawable.bg_about_pager_blue);
//        ((TextView) textView).setText(AboutPeopleList[index]);
        View v = inflater.inflate(R.layout.item_article, container, false);
        TextView textView = (TextView) v.findViewById(R.id.article_text_view);
        textView.setBackgroundResource(R.drawable.bg_article_card_1);
        textView.getBackground().setAlpha(55);
        textView.setText(ContentUtil.getTextContent().get(index).getText());
        textView.setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void updateContent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.article_text_view:
                Intent intent = new Intent(getActivity(), ArticleReadActivity.class);
                intent.putExtra("index",index);
                startActivity(intent);
                break;

        }
    }
}
