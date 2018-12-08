package es.ulpgc.eite.cleancode.visitcanary.data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;


@Entity(tableName = "categories")
public class CategoryItem {

  @PrimaryKey
  public int id;

  public String content;
  public String details;

  @Ignore
  @SerializedName("products")
  public List<ProductItem> items;

  @Override
  public String toString() {
    return content;
  }
}