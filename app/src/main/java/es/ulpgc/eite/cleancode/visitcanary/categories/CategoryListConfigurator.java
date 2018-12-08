package es.ulpgc.eite.cleancode.visitcanary.categories;

import java.lang.ref.WeakReference;

import android.arch.lifecycle.ViewModelProviders;

// include this library dependency in build.gradle file
// implementation 'android.arch.lifecycle:extensions:1.1.1'
public enum CategoryListConfigurator {

  INSTANCE;

  public void configure(CategoryListActivity activity) {

    CategoryListRouter router = new CategoryListRouter();
    router.activity = new WeakReference<>(activity);

    CategoryListPresenter presenter = new CategoryListPresenter();
    presenter.viewModel =
        ViewModelProviders.of(activity).get(CategoryListViewModel.class);
    presenter.view = new WeakReference<CategoryListContract.View>(activity);
    presenter.router = router;

    CategoryListModel model = new CategoryListModel(new WeakReference<>(activity));
    presenter.model = model;

    if (activity.presenter == null) {
      activity.presenter = presenter;
    }

  }
}
