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

import es.ulpgc.eite.cleancode.visitcanary.database.CatalogDatabase;
import es.ulpgc.eite.cleancode.visitcanary.database.CategoryDao;
import es.ulpgc.eite.cleancode.visitcanary.database.ProductDao;


public class CatalogRepository {

  public static String TAG = CatalogRepository.class.getSimpleName();

  public interface FetchCatalogDataCallback {
    void onCatalogDataFetched(boolean error);
  }


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

  public interface DeleteCategoryCallback {
    void onCategoryDeleted();
  }

  public interface UpdateCategoryCallback {
    void onCategoryUpdated();
  }

  public interface DeleteProductCallback {
    void onProductDeleted();
  }

  public interface UpdateProductCallback {
    void onProductUpdated();
  }

  public static final String DB_FILE = "catalog.db";
  public static final String JSON_FILE = "catalog.json";
  public static final String JSON_ROOT = "categories";

  private static CatalogRepository INSTANCE;

  private CatalogDatabase database;
  private Context appContext;


  public static CatalogRepository getInstance(Context context) {
    if(INSTANCE == null){
      INSTANCE = new CatalogRepository(context);
    }

    return INSTANCE;
  }


  private CatalogRepository(Context appContext) {
    this.appContext = appContext;

    database = Room.databaseBuilder(
        appContext, CatalogDatabase.class, DB_FILE
    ).build();

  }


  public void loadCatalog(
      final boolean clearFirst, final FetchCatalogDataCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(clearFirst) {
          database.clearAllTables();
        }

        boolean error = false;
        if(getCategoryDao().loadCategories().size() == 0 ) {
          error = !loadCatalogFromJSON(loadJSONFromAsset());
        }

        if(callback != null) {
          callback.onCatalogDataFetched(error);
        }
      }
    });

  }

  public void getProductList(
      final CategoryItem category, final GetProductListCallback callback) {

    getProductList(category.id, callback);
  }


  public void getProductList(
      final int categoryId, final GetProductListCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(callback != null) {
          callback.setProductList(getProductDao().loadProducts(categoryId));
        }
      }
    });

  }


  public void getProduct(final int id, final GetProductCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(callback != null) {
          callback.setProduct(getProductDao().loadProduct(id));
        }
      }
    });
  }

  public void getCategory(final int id, final GetCategoryCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(callback != null) {
          callback.setCategory(getCategoryDao().loadCategory(id));
        }
      }
    });

  }

  public void getCategoryList(final GetCategoryListCallback callback) {
    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(callback != null) {
          callback.setCategoryList(getCategoryDao().loadCategories());
        }
      }
    });

  }


  public void deleteProduct(
      final ProductItem product, final DeleteProductCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(callback != null) {
          getProductDao().deleteProduct(product);
          callback.onProductDeleted();
        }
      }
    });
  }

  public void updateProduct(
      final ProductItem product, final UpdateProductCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(callback != null) {
          getProductDao().updateProduct(product);
          callback.onProductUpdated();
        }
      }
    });
  }



  public void deleteCategory(
      final CategoryItem category, final DeleteCategoryCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(callback != null) {
          getCategoryDao().deleteCategory(category);
          callback.onCategoryDeleted();
        }
      }
    });
  }

  public void updateCategory(
      final CategoryItem category, final UpdateCategoryCallback callback) {

    AsyncTask.execute(new Runnable() {

      @Override
      public void run() {
        if(callback != null) {
          getCategoryDao().updateCategory(category);
          callback.onCategoryUpdated();
        }
      }
    });
  }


  private CategoryDao getCategoryDao() {
    return database.categoryDao();
  }

  private ProductDao getProductDao() {
    return database.productDao();
  }


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
          getCategoryDao().insertCategory(category);
        }

        for (CategoryItem category: categories) {
          for (ProductItem product: category.items) {
            product.categoryId = category.id;
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
    //Log.e(TAG, "loadJSONFromAsset()");

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

}
