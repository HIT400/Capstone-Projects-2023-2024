package com.codedev.modernfarmer.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.codedev.modernfarmer.Entities.MedsInventory;

@Dao
public interface MedsInventoryDAO {

    @Insert
    void insert(MedsInventory medsInventory);

    @Update
    void update(MedsInventory medsInventory);

    @Delete
    void delete(MedsInventory medsInventory);
}
