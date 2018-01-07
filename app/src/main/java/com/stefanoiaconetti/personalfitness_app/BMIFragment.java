package com.stefanoiaconetti.personalfitness_app;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BMIFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BMIFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BMIFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText height;
    EditText weight;
    Button calculateBMI;
    TextView result;

    private OnFragmentInteractionListener mListener;

    public BMIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BMIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BMIFragment newInstance(String param1, String param2) {
        BMIFragment fragment = new BMIFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        height = (EditText) view.findViewById(R.id.height);
        weight = (EditText) view.findViewById(R.id.weight);
        result = (TextView) view.findViewById(R.id.BMIResult);
        calculateBMI = (Button) view.findViewById(R.id.calculateBMI);


        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkWeight = weight.getText().toString();
                String checkHeight = height.getText().toString();

                result.setText("");

                if ((checkWeight.isEmpty()) && (checkHeight.isEmpty())) {
                    result.setText("You have not entered a weight or height");
                    weight.setHintTextColor(Color.RED);
                    height.setHintTextColor(Color.RED);
                }else if (checkWeight.isEmpty()) {
                    result.setText("You have not entered a weight");
                    weight.setHintTextColor(Color.RED);
                } else if (checkHeight.isEmpty()) {
                    result.setText("You have not entered a height");
                    height.setHintTextColor(Color.RED);
                } else{

                    //Grab the values entered into the number inputs
                double heightValue = Double.parseDouble(height.getText().toString());
                double weightValue = Double.parseDouble(weight.getText().toString());

                //Create a variable that will hold the number of feet entered for the height
                double feet = Math.floor(heightValue);

                //Convert the height entered into inches
                double inches = Math.round((heightValue - feet) * 10);
                double convertedHeightValue = (feet * 12) + inches;

                //Squaring the converted height value
                convertedHeightValue = convertedHeightValue * convertedHeightValue;

                DecimalFormat decimalPlace = new DecimalFormat("0.0");

                double BMI = (weightValue / convertedHeightValue) * 703;

                String bodyMass = decimalPlace.format(BMI) + "%";
                result.setText(bodyMass);
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
