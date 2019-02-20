package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.content.Context;
import android.content.Intent;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.products.ProductListActivity;


public class CategoryListRouter implements CategoryListContract.Router {

  public static String TAG = CategoryListRouter.class.getSimpleName();

  //public WeakReference<CategoryListActivity> activity;
  private CatalogMediator mediator;


  public CategoryListRouter(CatalogMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToProductListScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, ProductListActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToProductListScreen(CategoryItem item) {
    //CatalogMediator mediator = (CatalogMediator) context.get().getApplication();
    mediator.setCategory(item);
  }

  /*
  @Override
  public String getDataFromPreviousScreen() {
    //CatalogMediator mediator = (CatalogMediator) activity.get().getApplication();
    //String data = mediator.getData();
    //mediator.resetData();
    //return data;
    return null;
  }
  */
}
