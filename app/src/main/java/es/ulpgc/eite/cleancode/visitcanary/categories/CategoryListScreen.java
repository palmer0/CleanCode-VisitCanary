package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.RepositoryContract;


public class CategoryListScreen {

  public static void configure(CategoryListContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    CatalogMediator mediator = (CatalogMediator) context.get().getApplication();
    CategoryListState state = mediator.getCategoryListState();
    RepositoryContract repository = CatalogRepository.getInstance(context.get());

    /*
    CategoryListViewModel viewModel =
        ViewModelProviders.of(context.get()).get(CategoryListViewModel.class);
    */

    CategoryListContract.Router router = new CategoryListRouter(mediator);
    /*
    CategoryListContract.Presenter presenter =
        new CategoryListPresenter(viewModel, router);
    */
    CategoryListContract.Presenter presenter=new CategoryListPresenter(state);
    CategoryListModel model = new CategoryListModel(repository);
    presenter.injectView(new WeakReference<>(view));
    presenter.injectModel(model);
    presenter.injectRouter(router);
    view.injectPresenter(presenter);

  }


}
