package com.codedev.modernfarmer.API;

import com.codedev.modernfarmer.Classes.BuyingCompanies;
import com.codedev.modernfarmer.Classes.CompanyStockDC;
import com.codedev.modernfarmer.Classes.ComponentsSwitcher;
import com.codedev.modernfarmer.Classes.FeedStockParams;
import com.codedev.modernfarmer.Classes.FeedSuppliers;
import com.codedev.modernfarmer.Classes.SellChicks;
import com.codedev.modernfarmer.Classes.UpdateToken;
import com.codedev.modernfarmer.Classes.UserData;
import com.codedev.modernfarmer.Classes.UserLogin;
import com.codedev.modernfarmer.Classes.Veterinarians;
import com.codedev.modernfarmer.Entities.Chicks_Placement;
import com.codedev.modernfarmer.Entities.CompanyStock;
import com.codedev.modernfarmer.Entities.FeedStock;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/register")
    Call <Void> registerUser(@Body UserData userData);

    @POST("/login")
    Call <Void> userLogin(@Body UserLogin userLogin);


    @POST("/add_placement")
    Call<JsonObject> postPlacement(@Body Chicks_Placement chicks_placement);

    @POST("/procure")
    Call<List<CompanyStock>> getStock(@Body CompanyStockDC companyStockDC);


    @GET("/buying_companies")
    Call<List<BuyingCompanies>> getBuyers();


    @GET("/components")
    Call<List<ComponentsSwitcher>> getComponents();


    @POST("/sell_chicks")
    Call<Void> postChicks(@Body SellChicks sellChicks);


    @GET("/veterinarians")
    Call<List<Veterinarians>> getVeterinarians();


    @POST("/update_fcm_token")
    Call<Void> updateFCMToken(@Body UpdateToken updateToken);

    @GET("/feed_suppliers")
    Call<List<FeedSuppliers>> getSuppliers();


    @POST("/feed_stock")
    Call<List<FeedStock>> getFeedStock(@Body FeedStockParams feedStockParams);

    @POST("/light_switch")
    Call<Void> toggleLightStatus(@Body ComponentsSwitcher componentsSwitcher);


}
