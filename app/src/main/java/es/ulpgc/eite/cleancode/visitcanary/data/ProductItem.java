package es.ulpgc.eite.cleancode.visitcanary.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
    tableName = "products",
    foreignKeys = @ForeignKey(
        entity = CategoryItem.class,
        parentColumns = "id",
        childColumns = "category_id",
        onDelete = CASCADE
    )
)
public class ProductItem {

  @PrimaryKey
  public int id;

  public String content;
  public String details;
  public String picture;

  @ColumnInfo(name = "category_id")
  public int categoryId;

  /*
  public ProductItem() {

  }


  public ProductItem(int id, String content, String details, String picture) {
    this.id = id;
    this.content = content;
    this.details = details;
    this.picture = picture;
  }


  public ProductItem(int id, String content, String details) {
    this.id = id;
    this.content = content;
    this.details = details;
  }
  */


  @Override
  public String toString() {
    return content;
  }
}