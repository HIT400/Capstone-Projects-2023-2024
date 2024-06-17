package com.codedev.modernfarmer.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.codedev.modernfarmer.Entities.CompanyStock;

import java.util.List;

@Dao
public interface CompanyStockDAO {

    @Insert
    void insert(CompanyStock companyStock);

    @Delete
    void delete(CompanyStock companyStock);

    @Update
    void update(CompanyStock companyStock);

    @Query("SELECT * FROM company_stock WHERE company_name = :cname AND product_name = :pname")
    List<CompanyStock> getCompanyStock(String cname, String pname);



}
