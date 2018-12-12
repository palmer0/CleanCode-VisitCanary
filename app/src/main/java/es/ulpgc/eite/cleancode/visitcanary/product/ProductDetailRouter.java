package es.ulpgc.eite.cleancode.visitcanary.product;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;


public class ProductDetailRouter implements ProductDetailContract.Router {

  public static String TAG = ProductDetailRouter.class.getSimpleName();

  //public WeakReference<ProductDetailActivity> activity;
  private WeakReference<FragmentActivity> context;

  public ProductDetailRouter(WeakReference<FragmentActivity> context) {
    this.context = context;
  }

  @Override
  public void navigateToNextScreen() {
    Intent intent = new Intent(context.get(), ProductDetailActivity.class);
    context.get().startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(String data) {
    //CatalogMediator mediator = (CatalogMediator) context.get().getApplication();
    //mediator.setData(data);
  }

  @Override
  public ProductItem getDataFromProductListScreen() {
    CatalogMediator mediator = (CatalogMediator) context.get().getApplication();
    ProductItem product = mediator.getProduct();
    return product;
  }
}
