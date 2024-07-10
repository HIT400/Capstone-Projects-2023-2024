package com.codedev.modernfarmer.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.codedev.modernfarmer.Entities.Chicks_Placement;

@Dao
public interface ChicksPlacementDAO {

    @Insert
    void insert(Chicks_Placement chicks_placement);

    @Update
    void update(Chicks_Placement chicks_placement);

    @Delete
    void delete(Chicks_Placement chicks_placement);
}
