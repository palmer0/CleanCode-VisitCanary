package es.ulpgc.eite.cleancode.visitcanary.data;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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

  /*
  public CategoryItem() {

  }

  public CategoryItem(
      int id, String content, String details, List<ProductItem> items) {
    this.id = id;
    this.content = content;
    this.details = details;
    this.items = items;
  }


  public CategoryItem(int id, String content, String details) {
    this.id = id;
    this.content = content;
    this.details = details;
    items = new ArrayList<>();
  }
  */


  @Override
  public String toString() {
    return content;
  }
}