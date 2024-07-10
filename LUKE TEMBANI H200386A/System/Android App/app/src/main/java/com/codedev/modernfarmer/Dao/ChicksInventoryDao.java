package com.codedev.modernfarmer.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.codedev.modernfarmer.Entities.ChicksInventory;

@Dao
public interface ChicksInventoryDao {

    @Insert
    void insert(ChicksInventory chicksInventory);

    @Delete
    void delete(ChicksInventory chicksInventory);

    @Update
    void update(ChicksInventory chicksInventory);
}
