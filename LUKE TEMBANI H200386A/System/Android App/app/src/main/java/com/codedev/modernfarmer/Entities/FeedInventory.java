package com.codedev.modernfarmer.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "feed_inventory")
public class FeedInventory {

    @PrimaryKey(autoGenerate = true)
    private long feed_inv_id;

    @ColumnInfo(name = "feed_type")
    private String feed_type;

    @ColumnInfo(name = "feed_quantity")
    private String feed_quantity;

    @ColumnInfo(name = "price")
    private Double price;

    public long getFeed_inv_id() {
        return feed_inv_id;
    }

    public void setFeed_inv_id(long feed_inv_id) {
        this.feed_inv_id = feed_inv_id;
    }

    public String getFeed_type() {
        return feed_type;
    }

    public void setFeed_type(String feed_type) {
        this.feed_type = feed_type;
    }

    public String getFeed_quantity() {
        return feed_quantity;
    }

    public void setFeed_quantity(String feed_quantity) {
        this.feed_quantity = feed_quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "FeedInventory{" +
                "feed_inv_id='" + feed_inv_id + '\'' +
                ", feed_type='" + feed_type + '\'' +
                ", feed_quantity='" + feed_quantity + '\'' +
                ", price=" + price +
                '}';
    }
}
