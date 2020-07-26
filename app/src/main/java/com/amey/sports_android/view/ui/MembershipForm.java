package com.amey.sports_android.view.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amey.sports_android.R;
import com.amey.sports_android.utilities.KeyboardUtils;
import com.amey.sports_android.utilities.Prefs;
import com.amey.sports_android.utilities.TypeFaceHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MembershipForm.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembershipForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembershipForm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String tag = "MembershipForm";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context context;
    View view;
    AppCompatTextView nametextview, emailtextview;
    private OnFragmentInteractionListener mListener;
    Typeface fontAwesomeFont;
    Typeface robotoRegular;
    AppCompatEditText name_edit_text, email_edit_text;
    AppCompatButton submitbutton;
    private AlertDialog alertDialog;
    MainActivity mainActivity;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    public MembershipForm() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MembershipForm newInstance() {
        MembershipForm fragment = new MembershipForm();

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
        fontAwesomeFont = ResourcesCompat.getFont(context,R.font.fontawesome);
        robotoRegular = TypeFaceHelper.getInstance(context).getStyleTypeFace(TypeFaceHelper.MEDIUM);
        view = inflater.inflate(R.layout.fragment_membership_form, container, false);
        nametextview = view.findViewById(R.id.nametextview);
        nametextview.setTypeface(fontAwesomeFont);
        emailtextview = view.findViewById(R.id.emailtextview);
        emailtextview.setTypeface(fontAwesomeFont);

        name_edit_text = view.findViewById(R.id.name_edit_text);
        name_edit_text.setHint("Name");
        email_edit_text = view.findViewById(R.id.email_edit_text);
        email_edit_text.setHint("Email");

        submitbutton = view.findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name_edit_text.getText().toString().isEmpty()) {
                    Toast.makeText(context,"Please Enter Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email_edit_text.getText().toString().isEmpty()) {
                    Toast.makeText(context,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;

                }
                String email = email_edit_text.getText().toString().trim();

                if (email.matches(emailPattern))
                {
                    KeyboardUtils.hideSoftKeyboard(mainActivity);
                    alertDialog.show();
                }
                else
                {
                    Toast.makeText(context,"Invalid email address", Toast.LENGTH_SHORT).show();
                }

            }
        });

        alertDialog = new AlertDialog.Builder(context,R.style.AppCompatAlertDialogStyle)
                .setTitle("")
                .setMessage("Thank you for Contacting us. We will get back to you soon.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(mainActivity!= null){
                            mainActivity.openHomeFragment(Prefs.getTeamId(getActivity()));
                            mainActivity.bottom_navigation.setSelectedItemId(0);
                        }
                    }
                }). setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        alertDialog.setCancelable(false);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextSize(15);
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextSize(15);
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
        this.context =context;
        this.mainActivity = (MainActivity)context;
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
}
