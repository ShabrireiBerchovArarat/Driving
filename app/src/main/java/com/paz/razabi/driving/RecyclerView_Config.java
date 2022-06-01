package com.paz.razabi.driving;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Step_6
public class RecyclerView_Config {
    // step_7
    private Context mContext;
    private StudentsAdapter mStudentAdapters;

    // step_8
    public void setConfig(RecyclerView recyclerView, Context context, List<Student> students, List<String> keys) {
        mContext = context;
        mStudentAdapters = new StudentsAdapter(students, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mStudentAdapters);
    }

    // step_9
    class StudentItemView extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mID;
        private TextView mPhone;
        private TextView mAdress;
        private TextView mLC;
        private TextView mUL;
        private String key;

        public StudentItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.list_item, parent, false));
            mName = (TextView) itemView.findViewById(R.id.tvName);
            mID = (TextView) itemView.findViewById(R.id.tvLC);
            mPhone = (TextView) itemView.findViewById(R.id.tvPhone);
            mAdress = (TextView) itemView.findViewById(R.id.tvUL);
            mLC = (TextView) itemView.findViewById(R.id.tvID);
            mUL = (TextView) itemView.findViewById(R.id.tvAddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, StudentDetailsActivity.class);
                    i.putExtra("key", key);
                    i.putExtra("name", mName.getText().toString());
                    i.putExtra("phone", mPhone.getText().toString());
                    i.putExtra("id", mID.getText().toString());
                    i.putExtra("address", mAdress.getText().toString());
                    i.putExtra("lc", Integer.parseInt(mLC.getText().toString()));
                    i.putExtra("ul", Integer.parseInt(mUL.getText().toString()));

                    mContext.startActivity(i);
                }
            });

        }

        public void bind(Student student, String key) {
            mName.setText(student.getName());
            mID.setText(student.getId());
            mPhone.setText(student.getPhone());
            mAdress.setText(student.getAddress());
            mLC.setText("" + student.getLessonCount());
            mUL.setText("" + student.getUnpaidLessonCount());
            this.key = key;
        }

    }

    // step_10
    class StudentsAdapter extends RecyclerView.Adapter<StudentItemView> {
        private List<Student> mStudentList;
        private List<String> mKeys;

        public StudentsAdapter(List<Student> mStudentList, List<String> mKeys) {
            this.mStudentList = mStudentList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public StudentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StudentItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentItemView holder, int position) {
            holder.bind(mStudentList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mStudentList.size();
        }
    }

}
