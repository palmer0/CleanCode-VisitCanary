package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;

public class CategoryListModel implements CategoryListContract.Model {

  public static String TAG = CategoryListModel.class.getSimpleName();

  private WeakReference<CategoryListActivity> activity;
  private CatalogRepository repository;


  public CategoryListModel(WeakReference<CategoryListActivity> activity) {
    this.activity = activity;
    repository = CatalogRepository.getInstance();
    repository.loadCatalogFromJSON(loadJSONFromAsset());
  }

  @Override
  public List<CategoryItem> fetchCategoryListData() {
    Log.e(TAG, "fetchCategoryListData()");

    /*
    List<CategoryItem> items =
        repository.loadCatalogFromJSON(loadJSONFromAsset());
    Log.e(TAG, "categories: " + items);

    for(CategoryItem item: items){
      Log.e(TAG, "category: " + item);
      Log.e(TAG, "products: " + item.items);
    }
    */

    return repository.getCategoryList();
  }

  private String loadJSONFromAsset() {
    Log.e(TAG, "loadJSONFromAsset()");

    String json = null;

    try {

      InputStream is = activity.get().getAssets().open("catalog.json");
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
