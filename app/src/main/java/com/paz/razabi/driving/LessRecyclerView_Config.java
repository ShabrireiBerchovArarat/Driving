package com.paz.razabi.driving;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessRecyclerView_Config {
    private Context mContext;
    private LessRecyclerView_Config.LessonsAdapter mLessonAdapters;

    public void setConfig(RecyclerView recyclerView, Context context, List<Lesson> lessons, List<String> keys) {
        mContext = context;
        mLessonAdapters = new LessRecyclerView_Config.LessonsAdapter(lessons, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mLessonAdapters);

    }

    // step_9
    class LessonItemView extends RecyclerView.ViewHolder {
        private TextView mSName;
        private TextView mDate;
        private String key;

        public LessonItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.less_list_item, parent, false));
            mSName = (TextView) itemView.findViewById(R.id.tvLS);
            mDate = (TextView) itemView.findViewById((R.id.tvLD));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, LessonDetailsActivity.class);
                    intent.putExtra("less_key", key);
                    intent.putExtra("less_name", mSName.getText().toString());
                    intent.putExtra("less_date", mDate.getText().toString());

                    Log.i("BBBbb", key);
                    Log.d("TAGTAGTAG", mSName.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }

        public void lessBind(Lesson lesson, String key) {
            Log.d("LessBind", lesson.getStudent().getName());
            Log.d("LessBind", key);

            mSName.setText(lesson.getStudent().getName());
            mDate.setText(lesson.getDate());
            this.key = key;
        }

    }

    // step 10
    class LessonsAdapter extends RecyclerView.Adapter<LessonItemView> {
        private List<Lesson> mLessonList;
        private List<String> mKeys;

        public LessonsAdapter(List<Lesson> mLessonList, List<String> mKeys) {
            this.mLessonList = mLessonList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public LessonItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LessonItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LessonItemView holder, int position) {
            holder.lessBind(mLessonList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mLessonList.size();
        }
    }
}
