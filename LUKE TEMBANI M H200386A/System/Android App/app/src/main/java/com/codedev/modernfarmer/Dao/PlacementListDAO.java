package com.codedev.modernfarmer.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.codedev.modernfarmer.Entities.PlacementList;

import java.util.List;

@Dao
public interface PlacementListDAO {

    @Insert
    void insert(PlacementList placementList);

    @Delete
    void delete(PlacementList placementList);

    @Update
    void update(PlacementList placementList);

    @Query("SELECT * from placement_list ORDER BY placement_date DESC")
    List<PlacementList> getAllPlacements();

    @Query("UPDATE placement_list SET placement_status = :status WHERE placement_id = :placement_id")
    void updatePlacement(String status, String placement_id);

}
