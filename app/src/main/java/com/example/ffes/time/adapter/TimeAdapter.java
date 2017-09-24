package com.example.ffes.time.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ffes.time.R;
import com.example.ffes.time.data.Record;
import com.example.ffes.time.data.Time;
import com.example.ffes.time.view.RecordDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ffes on 2017/9/23.
 */

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> implements RecordDataModel{

    Context context;
    List<Record> recordList;

    public TimeAdapter(Context context){
        this.context=context;
        recordList=new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_view_holder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Record record=get(position);
        holder.setCurrentTime(record.getCurrent());
        holder.setloopTime(record.getLoop());
        holder.setTotalTime(record.getTotal());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    @Override
    public void add(Record record) {
        recordList.add(record);
        notifyItemInserted(recordList.indexOf(record));
    }

    @Override
    public void update(int position, Record record) {

        recordList.set(position,record);
        notifyItemChanged(position);
    }

    @Override
    public Record get(int position) {
        return recordList.get(position);
    }

    @Override
    public void clear() {
        recordList.clear();
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.loop)
        TextView loop;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.current)
        TextView current;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setCurrentTime(Time time) {
            current.setText(time.toString());
        }
        public void setTotalTime(Time time) {
            total.setText(time.toString());
        }
        public void setloopTime(int loop) {
            this.loop.setText("第"+loop+"圈");
        }
    }
}
