package es.ulpgc.eite.cleancode.visitcanary.categories;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;

interface CategoryListContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayCategoryListData(CategoryListViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void fetchCategoryListData();
    void selectCategoryListData(CategoryItem item);
  }

  interface Model {
    void fetchCategoryListData(
        CatalogRepository.GetCategoryListCallback callback);
  }


  interface Router {

    void navigateToProductListScreen();
    void passDataToProductListScreen(CategoryItem item);
    String getDataFromPreviousScreen();
  }

}