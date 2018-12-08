package es.ulpgc.eite.cleancode.visitcanary.data;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class CategoryItem extends CatalogItem {

  public final String details;

  @SerializedName("products")
  public final List<ProductItem> items;

  public CategoryItem(int id, String content, String details) {
    super(id, content);
    this.details = details;
    items = new ArrayList<>();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}