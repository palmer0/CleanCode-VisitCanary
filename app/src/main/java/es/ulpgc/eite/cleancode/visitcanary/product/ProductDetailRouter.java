package es.ulpgc.eite.cleancode.visitcanary.product;

import android.content.Intent;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;


public class ProductDetailRouter {

  public static String TAG = ProductDetailRouter.class.getSimpleName();

  public WeakReference<ProductDetailActivity> activity;


  public void navigateToNextScreen() {
    Intent intent = new Intent(activity.get(), ProductDetailActivity.class);
    activity.get().startActivity(intent);
  }

  public void passDataToNextScreen(String data) {
    //CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    //mediator.setData(data);
  }


  public ProductItem getDataFromProductListScreen() {
    CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    ProductItem product = mediator.getProduct();
    return product;
  }
}
