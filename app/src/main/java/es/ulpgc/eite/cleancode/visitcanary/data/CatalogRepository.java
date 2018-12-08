package es.ulpgc.eite.cleancode.visitcanary.data;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.database.CatalogDatabase;
import es.ulpgc.eite.cleancode.visitcanary.database.CategoryDao;
import es.ulpgc.eite.cleancode.visitcanary.database.ProductDao;


public class CatalogRepository {

  private static CatalogRepository INSTANCE;

  private final CategoryDao categoryDao;
  private  final ProductDao productDao;
  //private CatalogDatabase database;

  //private final List<CategoryItem> itemList = new ArrayList<>();
  private List<CategoryItem> itemList;
  private final int COUNT = 20;

  /*
  public static CatalogRepository getInstance() {
    if(INSTANCE == null){
      INSTANCE = new CatalogRepository();
    }

    return INSTANCE;
  }
  */

  public static CatalogRepository getInstance(Context context) {
    if(INSTANCE == null){
      INSTANCE = new CatalogRepository(context);
    }

    return INSTANCE;
  }

  private CatalogRepository(Context context) {
    CatalogDatabase database = Room.databaseBuilder(
        context, CatalogDatabase.class, "catalog.db"
    ).build();

    categoryDao = database.categoryDao();
    productDao = database.productDao();
  }


  /*
  private CatalogRepository() {
    // Add some sample items.
    for (int index = 1; index <= COUNT; index++) {
      addCategory(createCategory(index));
    }
  }
  */

  public void loadCatalogFromJSON(String json) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    try {

      JSONObject jsonObject = new JSONObject(json);
      JSONArray jsonArray = jsonObject.getJSONArray("categories");

      if (jsonArray.length() > 0) {
        itemList = Arrays.asList(
            gson.fromJson(jsonArray.toString(), CategoryItem[].class)
        );

        AsyncTask.execute(new Runnable() {

          @Override
          public void run() {
            for (CategoryItem category: itemList) {
              categoryDao.insertCategory(category);
            }

            for (CategoryItem category: itemList) {
              for (ProductItem product: category.items) {
                product.categoryId = category.id;
                productDao.insertProduct(product);
              }
            }
          }
        });

      }

    } catch (JSONException error) {
      itemList = new ArrayList<>();
    }

  }


  /*
  public List<CategoryItem> loadCatalogFromJSON(String json) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    JSONObject jsonObject = null;

    try {

      jsonObject = new JSONObject(json);

      JSONArray jsonArray = jsonObject.getJSONArray("categories");
      if (jsonArray.length() > 0) {
        List<CategoryItem> items = Arrays.asList(
            gson.fromJson(jsonArray.toString(), CategoryItem[].class)
        );

        return items;
      }

    } catch (JSONException error) {

    }

    return null;

  }
  */

  public List<ProductItem> getProductList(int id) {
    for (int index = 1; index <= COUNT; index++) {
      CategoryItem item = itemList.get(index);

      if(item.id == id) {
        return item.items;
      }
    }

    return new ArrayList<>();
  }


  public List<CategoryItem> getCategoryList() {
    //return itemList;
    return categoryDao.loadCategories();
  }


  /*
  private void addCategory(CategoryItem item) {
    itemList.add(item);
  }


  private void addProduct(List<ProductItem> itemList, ProductItem item) {
    itemList.add(item);
  }



  private ProductItem createProduct(int id, int position) {
    String content = "Product " + id + "." + position;

    return new ProductItem(
        position, content, fetchProductDetails(id, position)
    );

  }


  private CategoryItem createCategory(int position) {

    CategoryItem item = new CategoryItem(
        position, "Category " + position, fetchCategoryDetails(position)
    );


    for (int index = 1; index <= COUNT; index++) {
      addProduct(item.items, createProduct(item.id, index));
    }

    return item;
  }


  private String fetchCategoryDetails(int position) {
    StringBuilder builder = new StringBuilder();
    builder.append("Details about Category: ").append(position);

    for (int index = 0; index < position; index++) {
      builder.append("\nMore details information here.");
    }

    return builder.toString();
  }

  private String fetchProductDetails(int id, int position) {
    String content = "Details about Product:  " + id + "." + position;
    StringBuilder builder = new StringBuilder();
    builder.append(content);

    for (int index = 0; index < position; index++) {
      builder.append("\nMore details information here.");
    }

    return builder.toString();
  }
  */

}
