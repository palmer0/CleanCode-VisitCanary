package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;


public class CategoryListScreen {

  public static void configure(CategoryListContract.View view) {

    WeakReference<FragmentActivity> activity =
        new WeakReference<>((FragmentActivity) view);

    CategoryListViewModel viewModel =
        ViewModelProviders.of(activity.get()).get(CategoryListViewModel.class);

    CategoryListContract.Router router = new CategoryListRouter(activity);
    CategoryListContract.Presenter presenter =
        new CategoryListPresenter(viewModel, router);
    CategoryListModel model = new CategoryListModel(activity);
    presenter.injectView(new WeakReference<>(view));
    presenter.injectModel(model);
    view.injectPresenter(presenter);

  }

  /*
  public static void configure(CategoryListActivity activity) {

    CategoryListRouter router = new CategoryListRouter();
    router.activity = new WeakReference<>(activity);

    CategoryListPresenter presenter = new CategoryListPresenter();
    presenter.viewModel =
        ViewModelProviders.of(activity).get(CategoryListViewModel.class);
    presenter.view = new WeakReference<CategoryListContract.View>(activity);
    presenter.router = router;

    CategoryListModel model =
        new CategoryListModel(new WeakReference<>(activity));
    presenter.model = model;

    if (activity.presenter == null) {
      activity.presenter = presenter;
    }

  }
  */

}
