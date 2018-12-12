package es.ulpgc.eite.cleancode.visitcanary.products;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;

public class ProductListModel implements ProductListContract.Model {

  public static String TAG = ProductListModel.class.getSimpleName();

  //private WeakReference<ProductListActivity> activity;
  private WeakReference<FragmentActivity> context;

  private CatalogRepository repository;

  /*
  public ProductListModel(WeakReference<ProductListActivity> activity) {
    this.activity = activity;

    repository = CatalogRepository.getInstance(
        activity.get().getApplicationContext()
    );
  }
  */

  public ProductListModel(WeakReference<FragmentActivity> context) {
    this.context = context;
    repository = CatalogRepository.getInstance(context.get());

    /*
    repository = CatalogRepository.getInstance(
        context.get().getApplicationContext()
    );
    */
  }


  @Override
  public void fetchProductListData(
      CategoryItem category, CatalogRepository.GetProductListCallback callback) {

    Log.e(TAG, "fetchProductListData()");
    repository.getProductList(category, callback);
  }

}
