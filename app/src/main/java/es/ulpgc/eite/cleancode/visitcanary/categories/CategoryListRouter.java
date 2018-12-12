package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.products.ProductListActivity;


public class CategoryListRouter implements CategoryListContract.Router {

  public static String TAG = CategoryListRouter.class.getSimpleName();

  //public WeakReference<CategoryListActivity> activity;
  private WeakReference<FragmentActivity> activity;

  public CategoryListRouter(WeakReference<FragmentActivity> activity) {
    this.activity = activity;
  }

  @Override
  public void navigateToProductListScreen() {
    Intent intent = new Intent(activity.get(), ProductListActivity.class);
    activity.get().startActivity(intent);
  }

  @Override
  public void passDataToProductListScreen(CategoryItem item) {
    CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    mediator.setCategory(item);
  }

  @Override
  public String getDataFromPreviousScreen() {
    //CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    //String data = mediator.getData();
    //mediator.resetData();
    //return data;
    return null;
  }
}
