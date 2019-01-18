package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.RepositoryContract;

public class CategoryListModel implements CategoryListContract.Model {

  public static String TAG = CategoryListModel.class.getSimpleName();

  //private WeakReference<CategoryListActivity> activity;
  private WeakReference<FragmentActivity> context;

  //private CatalogRepository repository;
  private RepositoryContract repository;

  /*
  public CategoryListModel(WeakReference<CategoryListActivity> activity) {
    this.activity = activity;

    repository = CatalogRepository.getInstance(
        activity.get().getApplicationContext()
    );
  }
  */

  public CategoryListModel(WeakReference<FragmentActivity> context) {
    this.context = context;
    repository = CatalogRepository.getInstance(context.get());

    /*
    repository = CatalogRepository.getInstance(
        context.get().getApplicationContext()
    );
    */
  }


  @Override
  public void fetchCategoryListData(
      final CatalogRepository.GetCategoryListCallback callback) {

    Log.e(TAG, "fetchCategoryListData()");

    repository.loadCatalog(
        true, new CatalogRepository.FetchCatalogDataCallback() {

      @Override
      public void onCatalogDataFetched(boolean error) {
        if(!error) {
          repository.getCategoryList(callback);
        }
      }
    });

  }

}
