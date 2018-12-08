package es.ulpgc.eite.cleancode.visitcanary.categories;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;


public class CategoryListPresenter implements CategoryListContract.Presenter {

  public static String TAG = CategoryListPresenter.class.getSimpleName();

  public WeakReference<CategoryListContract.View> view;
  public CategoryListViewModel viewModel;
  public CategoryListContract.Model model;
  public CategoryListRouter router;

  @Override
  public void fetchCategoryListData() {
    // Log.e(TAG, "fetchCategoryListData()");

    /*
    // set passed state
    String data = router.getDataFromPreviousScreen();
    if(data != null) {
        viewModel.data = data;
    }
    */

    // call the model
    model.fetchCategoryListData(new CatalogRepository.GetCategoryListCallback() {

      @Override
      public void setCategoryList(List<CategoryItem> categories) {
        viewModel.categories = categories;

        view.get().displayCategoryListData(viewModel);
      }
    });

  }


  @Override
  public void selectCategoryListData(CategoryItem item) {
    router.passDataToProductListScreen(item);
    router.navigateToProductListScreen();
  }

}
