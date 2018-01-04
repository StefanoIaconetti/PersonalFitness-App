package com.stefanoiaconetti.personalfitness_app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Stefano on 12/17/2017.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<WorkoutItem> myWorkout;

    public CustomRecyclerViewAdapter(ArrayList<WorkoutItem> myWorkout){
            this.myWorkout = myWorkout;
}

@Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, null);
         CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
}

@Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position){

        WorkoutItem workout = myWorkout.get(position);

         ((CustomViewHolder) holder).step.setText(workout.getStep());
         ((CustomViewHolder) holder).description.setText(workout.getDesc());
}


    @Override
    public int getItemCount() {
        if(myWorkout != null){
            return myWorkout.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView step;
        protected TextView description;

        public CustomViewHolder(View view){
            super(view);
            this.step = view.findViewById(R.id.step);
            this.description = view.findViewById(R.id.description);
        }

    }

}
