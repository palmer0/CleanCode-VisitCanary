package es.ulpgc.eite.cleancode.visitcanary.products;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;
import es.ulpgc.eite.cleancode.visitcanary.product.ProductDetailActivity;


public class ProductListRouter implements ProductListContract.Router {

  public static String TAG = ProductListRouter.class.getSimpleName();

  //public WeakReference<ProductListActivity> activity;
  private WeakReference<FragmentActivity> activity;

  public ProductListRouter(WeakReference<FragmentActivity> activity) {
    this.activity = activity;
  }

  @Override
  public void navigateToProductDetailScreen() {
    Intent intent = new Intent(activity.get(), ProductDetailActivity.class);
    activity.get().startActivity(intent);
  }

  @Override
  public void passDataToProductDetailScreen(ProductItem item) {
    CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    mediator.setProduct(item);
  }

  @Override
  public CategoryItem getDataFromCategoryListScreen() {
    CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    CategoryItem category = mediator.getCategory();
    return category;
  }

}
