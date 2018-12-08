package es.ulpgc.eite.cleancode.visitcanary.products;

import android.arch.lifecycle.ViewModelProviders;

import java.lang.ref.WeakReference;

// include this library dependency in build.gradle file
// implementation 'android.arch.lifecycle:extensions:1.1.1'
public enum ProductListConfigurator {

  INSTANCE;

  public void configure(ProductListActivity activity) {

    ProductListRouter router = new ProductListRouter();
    router.activity = new WeakReference<>(activity);

    ProductListPresenter presenter = new ProductListPresenter();
    presenter.viewModel =
        ViewModelProviders.of(activity).get(ProductListViewModel.class);
    presenter.view = new WeakReference<ProductListContract.View>(activity);
    presenter.router = router;

    ProductListModel model = new ProductListModel(new WeakReference<>(activity));
    presenter.model = model;

    if (activity.presenter == null) {
      activity.presenter = presenter;
    }

  }
}
