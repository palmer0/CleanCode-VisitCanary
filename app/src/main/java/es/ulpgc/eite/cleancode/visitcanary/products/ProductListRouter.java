package es.ulpgc.eite.cleancode.visitcanary.products;

import android.content.Intent;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;
import es.ulpgc.eite.cleancode.visitcanary.product.ProductDetailActivity;


public class ProductListRouter {

  public static String TAG = ProductListRouter.class.getSimpleName();

  public WeakReference<ProductListActivity> activity;


  public void navigateToProductDetailScreen() {
    Intent intent = new Intent(activity.get(), ProductDetailActivity.class);
    activity.get().startActivity(intent);
  }

  public void passDataToProductDetailScreen(ProductItem item) {
    CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    mediator.setProduct(item);
  }


  public CategoryItem getDataFromCategoryListScreen() {
    CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    CategoryItem category = mediator.getCategory();
    return category;
  }

}
