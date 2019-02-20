package es.ulpgc.eite.cleancode.visitcanary.product;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;


public class ProductDetailRouter implements ProductDetailContract.Router {

  public static String TAG = ProductDetailRouter.class.getSimpleName();

  private CatalogMediator mediator;

  /*
  //public WeakReference<ProductDetailActivity> activity;
  private WeakReference<FragmentActivity> context;

  public ProductDetailRouter(WeakReference<FragmentActivity> context) {
    this.context = context;
  }
  */

  public ProductDetailRouter(CatalogMediator mediator) {
    this.mediator = mediator;
  }

//  @Override
//  public void navigateToNextScreen() {
//    Context context = mediator.getApplicationContext();
//    Intent intent = new Intent(context, ProductDetailActivity.class);
//    context.startActivity(intent);
//  }
//
//  @Override
//  public void passDataToNextScreen(String data) {
//    //CatalogMediator mediator = (CatalogMediator) context.get().getApplication();
//    //mediator.setData(data);
//  }

  @Override
  public ProductItem getDataFromProductListScreen() {
    ProductItem product = mediator.getProduct();
    return product;
  }
}
