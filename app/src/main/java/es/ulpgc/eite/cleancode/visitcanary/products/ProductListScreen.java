package es.ulpgc.eite.cleancode.visitcanary.products;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class ProductListScreen {

  public static void configure(ProductListContract.View view) {

    WeakReference<FragmentActivity> activity =
        new WeakReference<>((FragmentActivity) view);

    ProductListViewModel viewModel =
        ViewModelProviders.of(activity.get()).get(ProductListViewModel.class);

    ProductListContract.Router router = new ProductListRouter(activity);
    ProductListContract.Presenter presenter =
        new ProductListPresenter(viewModel, router);
    ProductListModel model = new ProductListModel(activity);
    presenter.injectView(new WeakReference<>(view));
    presenter.injectModel(model);
    view.injectPresenter(presenter);

  }

  /*
  public static void configure(ProductListActivity activity) {

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
  */

}
