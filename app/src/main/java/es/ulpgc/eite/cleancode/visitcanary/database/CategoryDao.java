package es.ulpgc.eite.cleancode.visitcanary.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;

@Dao
public interface CategoryDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertCategories(CategoryItem... categories);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertCategory(CategoryItem category);

  @Query("SELECT * FROM categories")
  List<CategoryItem> loadCategories();

  @Query("SELECT * FROM categories WHERE id = :id LIMIT 1")
  CategoryItem loadCategory(int id);
}
