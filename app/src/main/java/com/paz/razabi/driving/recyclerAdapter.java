package com.paz.razabi.driving;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<Student> studentList;

    public recyclerAdapter(ArrayList<Student> studentList){
        this.studentList = studentList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt:
        private TextView idTxt:
        private TextView phoneTxt:
        private TextView upLTxt:
        private TextView ldTxt:
        private TextView addTxt:

        public MyViewHolder(final View view){
            super(view);
            nameTxt = view.findViewById(R.id.)
        }
    }
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
