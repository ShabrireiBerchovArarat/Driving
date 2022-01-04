package com.paz.razabi.driving;

import android.view.LayoutInflater;
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
        private TextView nameTxt;
        private TextView idTxt;
        private TextView phoneTxt;
        private TextView upLTxt;
        private TextView ldTxt;
        private TextView addTxt;

        public MyViewHolder(final View view){
            super(view);
            nameTxt = view.findViewById(R.id.nameTxt);
            idTxt = view.findViewById(R.id.idTxt);
            phoneTxt = view.findViewById(R.id.phoneTxt);
            upLTxt = view.findViewById(R.id.uplTxt);
            ldTxt = view.findViewById(R.id.ldTxt);
            addTxt = view.findViewById(R.id.addTxt);
        }
    }
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = studentList.get(position).getName();
        String id = studentList.get(position).getId();
        String phone = studentList.get(position).getPhone();
        String upL = Integer.toString(studentList.get(position).getUnpaidLessonCount());
        String ld = Integer.toString(studentList.get(position).getLessonCount());
        String add = studentList.get(position).getName();
        holder.nameTxt.setText(name);
        holder.idTxt.setText(id);
        holder.phoneTxt.setText(phone);
        holder.upLTxt .setText(upL);
        holder.ldTxt.setText(ld);
        holder.addTxt.setText(add);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
