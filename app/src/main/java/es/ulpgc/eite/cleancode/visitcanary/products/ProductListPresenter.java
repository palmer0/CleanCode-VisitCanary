package es.ulpgc.eite.cleancode.visitcanary.products;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;


public class ProductListPresenter implements ProductListContract.Presenter {

  public static String TAG = ProductListPresenter.class.getSimpleName();

  /*
  public WeakReference<ProductListContract.View> view;
  public ProductListViewModel viewModel;
  public ProductListContract.Model model;
  public ProductListRouter router;
  */

  private WeakReference<ProductListContract.View> view;
  private ProductListViewModel viewModel;
  private ProductListContract.Model model;
  private ProductListContract.Router router;

  public ProductListPresenter(ProductListState state) {
    viewModel = state;
  }

  /*
  public ProductListPresenter(
      ProductListViewModel viewModel, ProductListContract.Router router) {

    this.viewModel = viewModel;
    this.router = router;
  }
  */

//  public ProductListPresenter(WeakReference<FragmentActivity> context) {
//    viewModel = ViewModelProviders
//        .of(context.get())
//        .get(ProductListViewModel.class);
//  }

  @Override
  public void injectView(WeakReference<ProductListContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ProductListContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(ProductListContract.Router router) {
    this.router = router;
  }

  @Override
  public void fetchProductListData() {
    // Log.e(TAG, "fetchProductListData()");

    // set passed state
    CategoryItem item = router.getDataFromCategoryListScreen();

    if (item != null) {
      viewModel.category = item;
    }

    // call the model
    model.fetchProductListData(viewModel.category,
        new CatalogRepository.GetProductListCallback() {

      @Override
      public void setProductList(List<ProductItem> products) {
        viewModel.products = products;

        view.get().displayProductListData(viewModel);
      }
    });

  }


  @Override
  public void selectProductListData(ProductItem item) {
    router.passDataToProductDetailScreen(item);
    router.navigateToProductDetailScreen();
  }


}
