package es.ulpgc.eite.cleancode.visitcanary.product;

import android.arch.lifecycle.ViewModelProviders;

import java.lang.ref.WeakReference;

// include this library dependency in build.gradle file
// implementation 'android.arch.lifecycle:extensions:1.1.1'
public enum ProductDetailConfigurator {

  INSTANCE;

  public void configure(ProductDetailActivity activity) {

    ProductDetailRouter router = new ProductDetailRouter();
    router.activity = new WeakReference<>(activity);

    ProductDetailPresenter presenter = new ProductDetailPresenter();
    presenter.viewModel =
        ViewModelProviders.of(activity).get(ProductDetailViewModel.class);
    presenter.view = new WeakReference<ProductDetailContract.View>(activity);
    presenter.router = router;

    ProductDetailModel model =
        new ProductDetailModel(new WeakReference<>(activity));
    presenter.model = model;

    if (activity.presenter == null) {
      activity.presenter = presenter;
    }

  }
}
