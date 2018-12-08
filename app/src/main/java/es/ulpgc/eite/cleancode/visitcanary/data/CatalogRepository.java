package es.ulpgc.eite.cleancode.visitcanary.data;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.categories.CategoryListModel;
import es.ulpgc.eite.cleancode.visitcanary.database.CatalogDatabase;
import es.ulpgc.eite.cleancode.visitcanary.database.CategoryDao;
import es.ulpgc.eite.cleancode.visitcanary.database.ProductDao;


public class CatalogRepository {

  public static String TAG = CategoryListModel.class.getSimpleName();

  public interface GetProductListCallback {
    void setProductList(List<ProductItem> products);
  }

  public interface GetProductCallback {
    void setProduct(ProductItem product);
  }

  public interface GetCategoryListCallback {
    void setCategoryList(List<CategoryItem> categories);
  }

  public interface GetCategoryCallback {
    void setCategory(CategoryItem category);
  }

  public static final String DB_FILE = "catalog.db";
  public static final String JSON_FILE = "catalog.json";
  public static final String JSON_ROOT = "categories";

  private static CatalogRepository INSTANCE;

  private CatalogDatabase database;
  //private final CategoryDao categoryDao;
  //private  final ProductDao productDao;


  //private final List<CategoryItem> itemList = new ArrayList<>();
  //private List<CategoryItem> itemList;
  //private final int COUNT = 20;

  private Context appContext;
  private boolean catalogLoading;
  private boolean catalogLoaded;

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

  /*
  private CatalogRepository(Context appContext) {
    this.appContext = appContext;

    CatalogDatabase database = Room.databaseBuilder(
        appContext, CatalogDatabase.class, DB_FILE
    ).build();

    //database.clearAllTables();

    categoryDao = database.categoryDao();
    productDao = database.productDao();

    //loadCatalogFromJSON(loadJSONFromAsset());

  }
  */

  private CatalogRepository(Context appContext) {
    this.appContext = appContext;

    database = Room.databaseBuilder(
        appContext, CatalogDatabase.class, DB_FILE
    ).build();

    //categoryDao = database.categoryDao();
    //productDao = database.productDao();
  }


  /*
  private CatalogRepository() {
    // Add some sample items.
    for (int index = 1; index <= COUNT; index++) {
      addCategory(createCategory(index));
    }
  }
  */

  private CategoryDao getCategoryDao() {
    return database.categoryDao();
  }

  private ProductDao getProductDao() {
    return database.productDao();
  }

//  private void loadCatalogFromJSON(String json) {
//
//    GsonBuilder gsonBuilder = new GsonBuilder();
//    Gson gson = gsonBuilder.create();
//
//    try {
//
//      JSONObject jsonObject = new JSONObject(json);
//      JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);
//
//      if (jsonArray.length() > 0) {
//        /*
//        itemList = Arrays.asList(
//            gson.fromJson(jsonArray.toString(), CategoryItem[].class)
//        );
//        */
//
//        final List<CategoryItem> categories = Arrays.asList(
//            gson.fromJson(jsonArray.toString(), CategoryItem[].class)
//        );
//
//        for (CategoryItem category: categories) {
//          //categoryDao.insertCategory(category);
//          getCategoryDao().insertCategory(category);
//        }
//
//        for (CategoryItem category: categories) {
//          for (ProductItem product: category.items) {
//            product.categoryId = category.id;
//            //productDao.insertProduct(product);
//            getProductDao().insertProduct(product);
//          }
//        }
//
//
//        /*
//        AsyncTask.execute(new Runnable() {
//
//          @Override
//          public void run() {
//            for (CategoryItem category: categories) {
//              categoryDao.insertCategory(category);
//            }
//
//            for (CategoryItem category: categories) {
//              for (ProductItem product: category.items) {
//                product.categoryId = category.id;
//                productDao.insertProduct(product);
//              }
//            }
//
//          }
//        });
//        */
//
//      }
//
//    } catch (JSONException error) {
//      Log.e(TAG, "error: " + error);
//      //itemList = new ArrayList<>();
//    }
//
//  }


  private boolean loadCatalogFromJSON(String json) {
    Log.e(TAG, "loadCatalogFromJSON()");

    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    try {

      JSONObject jsonObject = new JSONObject(json);
      JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);

      if (jsonArray.length() > 0) {

        final List<CategoryItem> categories = Arrays.asList(
            gson.fromJson(jsonArray.toString(), CategoryItem[].class)
        );

        for (CategoryItem category: categories) {
          //categoryDao.insertCategory(category);
          getCategoryDao().insertCategory(category);
        }

        for (CategoryItem category: categories) {
          for (ProductItem product: category.items) {
            product.categoryId = category.id;
            //productDao.insertProduct(product);
            getProductDao().insertProduct(product);
          }
        }

        return true;
      }

    } catch (JSONException error) {
      Log.e(TAG, "error: " + error);
    }

    return false;
  }

  private String loadJSONFromAsset() {
    Log.e(TAG, "loadJSONFromAsset()");

    String json = null;

    try {

      InputStream is = appContext.getAssets().open(JSON_FILE);
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");

    } catch (IOException error) {
      Log.e(TAG, "error: " + error);
    }

    return json;
  }

  /*
  public List<ProductItem> getProductList(int categoryId) {
    return productDao.loadProducts(categoryId);
  }

  public List<ProductItem> getProductList(CategoryItem category) {
    return productDao.loadProducts(category.id);
  }

  public ProductItem getProduct(int id) {
    return productDao.loadProduct(id);
  }

  public CategoryItem getCategory(int id) {
    return categoryDao.loadCategory(id);
  }

  public List<CategoryItem> getCategoryList() {
    //return itemList;
    return categoryDao.loadCategories();
  }
  */

  private void checkCatalogData() {
    if(catalogLoaded) {
      return;
    }

    if (!catalogLoading) {
      catalogLoading = true;

      database.clearAllTables();
      if(loadCatalogFromJSON(loadJSONFromAsset())) {
        catalogLoaded = true;
      }

      catalogLoading = false;
    }
  }

  public void getProductList(
      final int categoryId, final GetProductListCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        checkCatalogData();

        if(callback != null) {
          //callback.setProductList(productDao.loadProducts(categoryId));
          callback.setProductList(getProductDao().loadProducts(categoryId));
        }
      }
    });

  }


  public void getProduct(final int id, final GetProductCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        checkCatalogData();

        if(callback != null) {
          //callback.setProduct(productDao.loadProduct(id));
          callback.setProduct(getProductDao().loadProduct(id));
        }
      }
    });
  }

  public void getCategory(final int id, final GetCategoryCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        checkCatalogData();

        if(callback != null) {
          //callback.setCategory(categoryDao.loadCategory(id));
          callback.setCategory(getCategoryDao().loadCategory(id));
        }
      }
    });

  }

  public void getCategoryList(final GetCategoryListCallback callback) {
    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        checkCatalogData();

        if(callback != null) {
          //callback.setCategoryList(categoryDao.loadCategories());
          callback.setCategoryList(getCategoryDao().loadCategories());
        }
      }
    });

  }


  /*
  public List<ProductItem> getProductList(int id) {
    for (int index = 1; index <= COUNT; index++) {
      CategoryItem item = itemList.get(index);

      if(item.id == id) {
        return item.items;
      }
    }

    return new ArrayList<>();
  }
  */

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
