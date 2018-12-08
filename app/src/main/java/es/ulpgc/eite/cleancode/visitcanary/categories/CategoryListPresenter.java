package es.ulpgc.eite.cleancode.visitcanary.categories;

import java.lang.ref.WeakReference;

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

    viewModel.categories = model.fetchCategoryListData();
    view.get().displayCategoryListData(viewModel);

  }


  @Override
  public void selectCategoryListData(CategoryItem item) {
    router.passDataToProductListScreen(item);
    router.navigateToProductListScreen();
  }

}
