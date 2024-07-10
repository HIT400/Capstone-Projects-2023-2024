package com.codedev.modernfarmer.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.codedev.modernfarmer.Entities.FeedInventory;

@Dao
public interface FeedInventoryDAO {

    @Insert
    void insert(FeedInventory feedInventory);

    @Update
    void update(FeedInventory feedInventory);

    @Delete
    void delete(FeedInventory feedInventory);
}
