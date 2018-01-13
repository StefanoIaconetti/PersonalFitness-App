package com.stefanoiaconetti.personalfitness_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String isMetric = "Imperial";
    public static String name = "";
    public static boolean hasName = false;
    public static boolean showCals = false;
    public static String beforeMetric = "Imperial";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //Makes isMetric beforeMetric incase the string is empty
        isMetric = beforeMetric;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        final Button btnMetricImp = (Button) view.findViewById(R.id.btnMetricImp);
        final EditText nameEditText = (EditText) view.findViewById(R.id.nameEditText);
        final Switch showCaloriesSwitch = (Switch) view.findViewById(R.id.showCaloriesSwitch);
        Button btnConfirm = (Button) view.findViewById(R.id.confirmBtn);

        if(nameEditText.getText() != null){
            nameEditText.setText(name);
        }

        if (isMetric == "Metric"){
             btnMetricImp.setText(R.string.metricText);
             beforeMetric = "Metric";
             isMetric = "";
        }else if(isMetric == "Imperial") {
            btnMetricImp.setText(R.string.imperialText);
            beforeMetric = "Imperial";
            isMetric = "";
        }

        if(showCals == true){
            showCaloriesSwitch.setChecked(true);
        }


        btnMetricImp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(beforeMetric == "Imperial"){
                    btnMetricImp.setText(R.string.metricText);
                    beforeMetric = "Metric";
                }else if (beforeMetric == "Metric"){
                    btnMetricImp.setText(R.string.imperialText);
                    beforeMetric = "Imperial";
                }
            }
        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(beforeMetric == "Imperial"){
                    isMetric = "Imperial";
                }else{
                    isMetric = "Metric";
                }

                if(nameEditText.getText().equals("")){
                    hasName = false;
                }else{
                    name = nameEditText.getText() + "";
                    hasName = true;
                }

                if(showCaloriesSwitch.isChecked()){
                    showCals = true;
                }else{
                    showCals = false;
                }
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
