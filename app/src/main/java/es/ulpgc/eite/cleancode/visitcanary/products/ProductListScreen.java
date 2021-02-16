package es.ulpgc.eite.cleancode.visitcanary.products;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.app.CatalogMediator;
import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.RepositoryContract;

public class ProductListScreen {

  public static void configure(ProductListContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    //CatalogMediator mediator = (CatalogMediator) context.get().getApplication();
    CatalogMediator mediator = CatalogMediator.getInstance();
    //ProductListState state = mediator.getProductListState();
    RepositoryContract repository = CatalogRepository.getInstance(context.get());

    //ProductListContract.Router router = new ProductListRouter(mediator);
    //ProductListContract.Presenter presenter = new ProductListPresenter(state);
    ProductListContract.Presenter presenter = new ProductListPresenter(mediator);
    ProductListModel model = new ProductListModel(repository);
    presenter.injectView(new WeakReference<>(view));
    presenter.injectModel(model);
    //presenter.injectRouter(router);
    view.injectPresenter(presenter);

  }


}
