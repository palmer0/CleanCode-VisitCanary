package es.ulpgc.eite.cleancode.visitcanary.product;

import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class ProductDetailModel implements ProductDetailContract.Model {

  public static String TAG = ProductDetailModel.class.getSimpleName();

  //private WeakReference<ProductDetailActivity> activity;
  private WeakReference<FragmentActivity> context;

  /*
  public ProductDetailModel(WeakReference<ProductDetailActivity> activity) {
    this.activity = activity;
  }
  */

  public ProductDetailModel(WeakReference<FragmentActivity> context) {
    this.context = context;
  }

  /*
  @Override
  public ProductItem fetchProductDetailData() {
    // Log.e(TAG, "fetchProductDetailData()");
    return null;
  }
  */
}
