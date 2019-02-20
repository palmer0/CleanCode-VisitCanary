package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.util.Log;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.RepositoryContract;

public class CategoryListModel implements CategoryListContract.Model {

  public static String TAG = CategoryListModel.class.getSimpleName();

  //private WeakReference<CategoryListActivity> activity;

  //private CatalogRepository repository;
  private RepositoryContract repository;

  public CategoryListModel(RepositoryContract repository) {
    this.repository = repository;

  }


//  public CategoryListModel(WeakReference<FragmentActivity> context) {
//    this.context = context;
//    repository = CatalogRepository.getInstance(context.get());
//
//  }


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
