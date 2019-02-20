package es.ulpgc.eite.cleancode.visitcanary.product;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;


public class ProductDetailPresenter implements ProductDetailContract.Presenter {

  public static String TAG = ProductDetailPresenter.class.getSimpleName();

  private WeakReference<ProductDetailContract.View> view;
  private ProductDetailViewModel viewModel;
  private ProductDetailContract.Model model;
  private ProductDetailContract.Router router;

  public ProductDetailPresenter(ProductDetailState state) {
    viewModel = state;
  }

  /*
  public WeakReference<ProductDetailContract.View> view;
  public ProductDetailViewModel viewModel;
  public ProductDetailContract.Model model;
  public ProductDetailRouter router;
  */

  /*
  public ProductDetailPresenter(
      ProductDetailViewModel viewModel, ProductDetailContract.Router router) {

    this.viewModel = viewModel;
    this.router = router;
  }
  */

//  public ProductDetailPresenter(WeakReference<FragmentActivity> context) {
//    viewModel = ViewModelProviders
//        .of(context.get())
//        .get(ProductDetailViewModel.class);
//  }

  @Override
  public void injectView(WeakReference<ProductDetailContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ProductDetailContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(ProductDetailContract.Router router) {
    this.router = router;
  }

  @Override
  public void fetchProductDetailData() {
    // Log.e(TAG, "fetchProductDetailData()");

    // set passed state
    ProductItem product = router.getDataFromProductListScreen();
    if(product != null) {
        viewModel.product = product;
    }

    /*
    // call the model
    ProductItem item = model.fetchProductDetailData();
    viewModel.item = item;
    */

    view.get().displayProductDetailData(viewModel);

  }


}
