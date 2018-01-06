package com.stefanoiaconetti.personalfitness_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WaterIntakeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WaterIntakeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterIntakeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText weight;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WaterIntakeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaterIntakeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WaterIntakeFragment newInstance(String param1, String param2) {
        WaterIntakeFragment fragment = new WaterIntakeFragment();
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
        View view = inflater.inflate(R.layout.fragment_water_intake, container, false);

        //spinner is now populated with waterintake spinner
        final Spinner spinner = (Spinner) view.findViewById(R.id.waterIntakeSpinner);


        //Textview for the calculation
        final TextView calculation = (TextView) view.findViewById(R.id.calculationsText);


        //String exerciseStats that is in the strings.xml is being called
        String[] exerArray = getResources().getStringArray(R.array.exerciseStats);
        //Adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, exerArray);

        //Layout for spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adding adapter to the spinner
        spinner.setAdapter(adapter);


        //Button being popoulated
        Button findOut = (Button) view.findViewById(R.id.findoutBtn);

        //Weight edit text being populated
        weight = (EditText) view.findViewById(R.id.weightEdit);


        String check = weight.getText().toString();

        if (check.isEmpty()) {
            calculation.setText("Ooooo");
        }else{
            //Calculations when button is pressed
            findOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Weightvalue is now parsed and turned into a double to ease calculations
                    double weightValue = Double.parseDouble(weight.getText().toString());

                    if (weightValue <= 0 || weightValue >= 500) {
                        calculation.setText("Sorry your weight cannot be calculated");
                    } else {
                        //Calculations will be your weight multiplyed by 0.02841308(.5 of a oz in litres)
                        double beforeExercise = weightValue * 0.5;

                        beforeExercise = beforeExercise * 0.02841308;

                        int userSelection = spinner.getSelectedItemPosition();
                        double userSelectionInt;

                        switch (userSelection) {
                            case 0:
                                userSelectionInt = 0;
                                break;
                            case 1:
                                userSelectionInt = 0.3;
                                break;
                            case 2:
                                userSelectionInt = 0.5;
                                break;
                            case 3:
                                userSelectionInt = 0.8;
                                break;
                            case 4:
                                userSelectionInt = 1;
                                break;
                            case 5:
                                userSelectionInt = 2;
                                break;
                            default:
                                userSelectionInt = 0;
                                break;
                        }

                        double afterExercise = beforeExercise + userSelection;


                        calculation.setText(afterExercise + "Litres");
                    }
                }

            });

        }

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
