package com.example.kilojoulecounter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<DiaryEntry> diary;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView1;
        public TextView mTextView2;


        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textViewTop);
            mTextView2 = itemView.findViewById(R.id.textViewBottom);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if (listener!=null){
                            int position = getAdapterPosition();
                            if(position!=RecyclerView.NO_POSITION){
                                listener.onItemClick(position);
                            }
                        }
                }
            });

        }
    }

public ExampleAdapter(ArrayList<DiaryEntry> diary){
    this.diary=diary;
}


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        DiaryEntry currentItem = diary.get(position);

        holder.mTextView1.setText(currentItem.getDateToday());
        holder.mTextView2.setText(""+currentItem.getNetTotal());
    }

    @Override
    public int getItemCount() {
        return diary.size();
    }



}
