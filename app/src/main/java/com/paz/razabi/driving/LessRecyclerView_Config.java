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

public class LessRecyclerView_Config {
    // step_7
    private Context mContext;
    private LessRecyclerView_Config.LessonsAdapter mLessonAdapters;
    // step_8
    public void setConfig(RecyclerView recyclerView, Context context, List<Lesson> lessons, List<String> keys)
    {
        mContext = context;
        mLessonAdapters =new LessRecyclerView_Config.LessonsAdapter(lessons,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mLessonAdapters);

    }
    // step_9
    class LessonItemView extends RecyclerView.ViewHolder{
        private TextView mSName;
        private TextView mDate;
        private String key ;

        public LessonItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.less_list_item,parent,false));
            mSName = (TextView)itemView.findViewById(R.id.tvLS);
            mDate = (TextView)itemView.findViewById((R.id.tvLD));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, LessonDetailsActivity.class);
                    i.putExtra("less_key", key);
                    i.putExtra("less_name", mSName.getText().toString());
                    i.putExtra("less_date", mDate.getText().toString());

                    mContext.startActivity(i);
                }
            });

        }
        public void lessBind(Lesson lesson,String key){
            mSName.setText(lesson.getStudent().getName());
            mDate.setText(lesson.getDate());
            this.key=key;
        }

    }
    // step_10
    class LessonsAdapter extends RecyclerView.Adapter<LessRecyclerView_Config.LessonItemView>{
        private List<Lesson> mLessonList;
        private List<String> mKeys;
        public LessonsAdapter( List<Lesson> mLessonList,List<String> mKeys){
            this.mLessonList = mLessonList;
            this.mKeys = mKeys;
        }
        @NonNull
        @Override
        public LessRecyclerView_Config.LessonItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LessRecyclerView_Config.LessonItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LessRecyclerView_Config.LessonItemView holder, int position) {
            holder.lessBind(mLessonList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mLessonList.size();
        }
    }

}
