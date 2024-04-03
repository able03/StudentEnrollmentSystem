package com.example.studentenrollmentsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>
{

    Context context;


    List<StudentAdapterModel> students;

    public StudentAdapter(Context context) {
        this.context = context;
    }
    public void setStudents(List<StudentAdapterModel> students)
    {
        this.students = students;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_layout_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            int id = students.get(position).getId();
            String studName = students.get(position).getStudentName();
            String course = students.get(position).getCourse();

            holder.tv_name.setText(studName);
            holder.tv_id.setText(String.valueOf(id));
            holder.tv_course.setText(course);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_subject,tv_name, tv_course, tv_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_course = itemView.findViewById(R.id.tvStudCourse);
            tv_name = itemView.findViewById(R.id.tvStudName);
            tv_id = itemView.findViewById(R.id.tvStudId);
        }
    }
}
