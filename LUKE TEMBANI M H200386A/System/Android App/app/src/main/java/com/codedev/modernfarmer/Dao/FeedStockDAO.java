package com.codedev.modernfarmer.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.codedev.modernfarmer.Entities.FeedStock;

@Dao
public interface FeedStockDAO {

    @Insert
    void insert(FeedStock feedStock);

    @Update
    void update(FeedStock feedStock);

    @Delete
    void delete(FeedStock feedStock);
}
