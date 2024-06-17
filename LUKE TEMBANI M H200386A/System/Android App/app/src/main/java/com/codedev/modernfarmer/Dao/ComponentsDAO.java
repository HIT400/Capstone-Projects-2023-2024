package com.codedev.modernfarmer.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.codedev.modernfarmer.Entities.Components;

import java.util.List;

@Dao
public interface ComponentsDAO {

    @Insert
    void insert(Components components);

    @Update
    void update (Components components);

    @Delete
    void delete(Components components);

    @Query("SELECT * FROM components")
    List<Components> getComponents();

    @Query("UPDATE components SET component_status = :status WHERE component_id = :component_id")
    void updateStatus(String status, Integer component_id);

    @Query("SELECT * FROM components WHERE component_id = :comp_id")
    Components getComponent(Integer comp_id);

    @Query("SELECT * FROM components WHERE component_status = 1 AND component_name LIKE '%Light%'")
    List<Components> getLightsOn();

    @Query("SELECT * FROM components WHERE component_status = 1 AND component_name LIKE '%Fan%'")
    List<Components> getFansOn();
}
