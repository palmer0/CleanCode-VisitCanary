package es.ulpgc.eite.cleancode.visitcanary.products;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;
import es.ulpgc.eite.cleancode.visitcanary.data.RepositoryContract;


public class ProductListPresenter implements ProductListContract.Presenter {

  public static String TAG = ProductListPresenter.class.getSimpleName();

  private WeakReference<ProductListContract.View> view;
  private ProductListState state;
  private ProductListContract.Model model;
  //private ProductListContract.Router router;
  private CatalogMediator mediator;

  public ProductListPresenter(CatalogMediator mediator) {
    this.mediator = mediator;
    state = mediator.getProductListState();
  }


//  public ProductListPresenter(ProductListState state) {
//    this.state = state;
//  }

  @Override
  public void injectView(WeakReference<ProductListContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ProductListContract.Model model) {
    this.model = model;
  }

//  @Override
//  public void injectRouter(ProductListContract.Router router) {
//    this.router = router;
//  }

  @Override
  public void fetchProductListData() {
    // Log.e(TAG, "fetchProductListData()");

    // set passed state
    CategoryItem category = getDataFromCategoryListScreen();
    //CategoryItem category = router.getDataFromCategoryListScreen();

    if (category != null) {
      state.category = category;
    }

    // call the model
    model.fetchProductListData(state.category,
        new RepositoryContract.GetProductListCallback() {

      @Override
      public void setProductList(List<ProductItem> products) {
        state.products = products;

        view.get().displayProductListData(state);
      }
    });

  }

  private void passDataToProductDetailScreen(ProductItem item) {

    mediator.setProduct(item);
  }

  private CategoryItem getDataFromCategoryListScreen() {
    CategoryItem category = mediator.getCategory();
    return category;
  }


  @Override
  public void selectProductListData(ProductItem item) {
    //router.passDataToProductDetailScreen(item);
    passDataToProductDetailScreen(item);
    //router.navigateToProductDetailScreen();
    view.get().navigateToProductDetailScreen();
  }


}
