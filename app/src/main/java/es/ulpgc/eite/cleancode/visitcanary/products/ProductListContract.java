package es.ulpgc.eite.cleancode.visitcanary.products;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;

interface ProductListContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayProductListData(ProductListViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);
    void injectRouter(Router router);

    void fetchProductListData();
    void selectProductListData(ProductItem item);
  }

  interface Model {

    void fetchProductListData(
        CategoryItem category, CatalogRepository.GetProductListCallback callback);
  }


  interface Router {

    void navigateToProductDetailScreen();
    void passDataToProductDetailScreen(ProductItem item);
    CategoryItem getDataFromCategoryListScreen();
  }
}