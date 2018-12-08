package es.ulpgc.eite.cleancode.visitcanary.products;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;

public class ProductListModel implements ProductListContract.Model {

  public static String TAG = ProductListModel.class.getSimpleName();

  private WeakReference<ProductListActivity> activity;

  private CatalogRepository repository;

  public ProductListModel(WeakReference<ProductListActivity> activity) {
    this.activity = activity;
    //repository = CatalogRepository.getInstance();
    repository = CatalogRepository.getInstance(
        activity.get().getApplicationContext()
    );
  }


  @Override
  public List<ProductItem> fetchProductListData(CategoryItem category) {
    Log.e(TAG, "fetchProductListData()");
    return repository.getProductList(category.id);
  }
}
