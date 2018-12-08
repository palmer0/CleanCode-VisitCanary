package es.ulpgc.eite.cleancode.visitcanary.product;

import java.lang.ref.WeakReference;

public class ProductDetailModel implements ProductDetailContract.Model {

  public static String TAG = ProductDetailModel.class.getSimpleName();

  private WeakReference<ProductDetailActivity> activity;

  public ProductDetailModel(WeakReference<ProductDetailActivity> activity) {
    this.activity = activity;
  }

  /*
  @Override
  public ProductItem fetchProductDetailData() {
    // Log.e(TAG, "fetchProductDetailData()");
    return null;
  }
  */
}
