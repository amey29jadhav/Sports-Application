package com.amey.sports_android.view.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amey.sports_android.R;
import com.amey.sports_android.utilities.AppConstant;
import com.amey.sports_android.utilities.TypeFaceHelper;
import com.amey.sports_android.view.callback.ClickCallback;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MembershipFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembershipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembershipFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String tag = "MembershipFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    Context context;
    Typeface fontAwesomeFont;
    Typeface robotoRegular;


    private OnFragmentInteractionListener mListener;
    AppCompatTextView feature1image, feature2image, feature3image, feature4image;
    AppCompatTextView price, membershipTextView, month, feature1, feature2, feature3, feature4;
    AppCompatButton signup;
    ClickCallback clickCallback;
    MainActivity mainActivity;


    public MembershipFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MembershipFragment newInstance() {
        MembershipFragment fragment = new MembershipFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mainActivity.headertextview.setText(AppConstant.MEMBERSHIP);

        fontAwesomeFont = ResourcesCompat.getFont(context,R.font.fontawesome);
        robotoRegular = TypeFaceHelper.getInstance(context).getStyleTypeFace(TypeFaceHelper.MEDIUM);


        view = inflater.inflate(R.layout.fragment_membership, container, false);
        feature1image = view.findViewById(R.id.feature1image);
        feature2image = view.findViewById(R.id.feature2image);
        feature3image = view.findViewById(R.id.feature3image);
        feature4image = view.findViewById(R.id.feature4image);

        feature1image.setTypeface(fontAwesomeFont);
        feature2image.setTypeface(fontAwesomeFont);
        feature3image.setTypeface(fontAwesomeFont);
        feature4image.setTypeface(fontAwesomeFont);

        price = view.findViewById(R.id.price);
        membershipTextView = view.findViewById(R.id.membershipTextView);
        month = view.findViewById(R.id.month);
        feature1 = view.findViewById(R.id.feature1);
        feature2 = view.findViewById(R.id.feature2);
        feature3 = view.findViewById(R.id.feature3);
        feature4 = view.findViewById(R.id.feature4);

        price.setTypeface(robotoRegular);
        membershipTextView.setTypeface(robotoRegular);
        month.setTypeface(robotoRegular);
        feature1.setTypeface(robotoRegular);
        feature2.setTypeface(robotoRegular);
        feature3.setTypeface(robotoRegular);
        feature4.setTypeface(robotoRegular);

        signup = view.findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickCallback !=null)
                clickCallback.onClick(null);
            }
        });


        return view;
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
        this.context = context;
        this.mainActivity = (MainActivity) context;

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;

    }
}
