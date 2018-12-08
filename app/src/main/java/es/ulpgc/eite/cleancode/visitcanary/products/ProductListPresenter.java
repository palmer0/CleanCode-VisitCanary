package es.ulpgc.eite.cleancode.visitcanary.products;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;


public class ProductListPresenter implements ProductListContract.Presenter {

  public static String TAG = ProductListPresenter.class.getSimpleName();

  public WeakReference<ProductListContract.View> view;
  public ProductListViewModel viewModel;
  public ProductListContract.Model model;
  public ProductListRouter router;

  @Override
  public void fetchProductListData() {
    // Log.e(TAG, "fetchProductListData()");

    // set passed state
    CategoryItem item = router.getDataFromCategoryListScreen();

    if (item != null) {
      viewModel.category = item;
    }

    // call the model
    viewModel.products = model.fetchProductListData(viewModel.category);

    view.get().displayProductListData(viewModel);

  }


  @Override
  public void selectProductListData(ProductItem item) {
    router.passDataToProductDetailScreen(item);
    router.navigateToProductDetailScreen();
  }


}
