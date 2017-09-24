package com.example.ffes.time.view;

import com.example.ffes.time.data.Record;

/**
 * Created by Ffes on 2017/9/23.
 */

public interface RecordDataModel {
    void add(Record record);
    void update(int position,Record record);
    Record get(int position);
    int getItemCount();
    void clear();
}
