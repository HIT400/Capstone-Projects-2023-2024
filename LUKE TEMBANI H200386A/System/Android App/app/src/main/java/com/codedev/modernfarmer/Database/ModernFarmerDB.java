package com.codedev.modernfarmer.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.codedev.modernfarmer.Dao.ChicksInventoryDao;
import com.codedev.modernfarmer.Dao.ChicksPlacementDAO;
import com.codedev.modernfarmer.Dao.CompanyStockDAO;
import com.codedev.modernfarmer.Dao.ComponentsDAO;
import com.codedev.modernfarmer.Dao.FeedInventoryDAO;
import com.codedev.modernfarmer.Dao.FeedStockDAO;
import com.codedev.modernfarmer.Dao.MedsInventoryDAO;
import com.codedev.modernfarmer.Dao.PlacementListDAO;
import com.codedev.modernfarmer.Entities.ChicksInventory;
import com.codedev.modernfarmer.Entities.Chicks_Placement;
import com.codedev.modernfarmer.Entities.CompanyStock;
import com.codedev.modernfarmer.Entities.Components;
import com.codedev.modernfarmer.Entities.FeedInventory;
import com.codedev.modernfarmer.Entities.FeedStock;
import com.codedev.modernfarmer.Entities.MedsInventory;
import com.codedev.modernfarmer.Entities.PlacementList;
import com.codedev.modernfarmer.Utils.DateConverter;

@Database(entities = {FeedInventory.class, FeedStock.class, PlacementList.class,Components.class, Chicks_Placement.class, CompanyStock.class, MedsInventory.class, ChicksInventory.class},version = 2,exportSchema = false)
@TypeConverters(DateConverter.class)

public abstract class ModernFarmerDB extends RoomDatabase {

    public abstract FeedInventoryDAO feedInventoryDAO();

    public abstract ChicksPlacementDAO chicksPlacementDAO();

    public abstract CompanyStockDAO companyStockDAO();


    public abstract MedsInventoryDAO medsInventoryDAO();

    public abstract ChicksInventoryDao chicksInventoryDao();


    public abstract ComponentsDAO componentsDAO();


    public abstract PlacementListDAO placementListDAO();


    public abstract FeedStockDAO feedStockDAO();

}
