package com.stefanoiaconetti.personalfitness_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WorkoutViewPager.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WorkoutViewPager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutViewPager extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public WorkoutViewPager() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutViewPager.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutViewPager newInstance(String param1, String param2) {
        WorkoutViewPager fragment = new WorkoutViewPager();
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
        View view =  inflater.inflate(R.layout.fragment_workout_view_pager, container, false);

        CustomAdapter adapter = new CustomAdapter(getChildFragmentManager());
        ViewPager viewpager = (ViewPager) view.findViewById(R.id.viewPagerWorkoutst);
        viewpager.setAdapter(adapter);

        return view;

    }


    public class CustomAdapter extends FragmentPagerAdapter {

        public CustomAdapter(FragmentManager fm){
            super(fm);
        }

        public Fragment getItem(int position){
            switch(position){
                case 0: return WorkoutFragment.newInstance("Grab a weight, while standing still bring the weight to your chest using your bicep","Arm Workout",  "bicep");
                case 1: return WorkoutFragment.newInstance("Laydown then pull yourself upwards bringing your knees to your chest","Ab workout",  "ab_workout");
                case 3: return WorkoutFragment.newInstance("Set a desired weight then pull on the bar using your back","Back Workout",  "back");
                case 4: return WorkoutFragment.newInstance("From a standing position go to a sitting position while still in the air","Leg workout",  "leg");
                default: return WorkoutFragment.newInstance("Grab a desired weight and drag the weight so its leveled with your shoulders","Shoulder workout",  "shoulder");
            }
        }

        public int getCount(){
            return 5;
        }

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
