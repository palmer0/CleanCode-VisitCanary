package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;


public class CategoryListScreen {

  public static void configure(CategoryListContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    /*
    CategoryListViewModel viewModel =
        ViewModelProviders.of(context.get()).get(CategoryListViewModel.class);
    */

    CategoryListContract.Router router = new CategoryListRouter(context);
    /*
    CategoryListContract.Presenter presenter =
        new CategoryListPresenter(viewModel, router);
    */
    CategoryListContract.Presenter presenter=new CategoryListPresenter(context);
    CategoryListModel model = new CategoryListModel(context);
    presenter.injectView(new WeakReference<>(view));
    presenter.injectModel(model);
    presenter.injectRouter(router);
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
