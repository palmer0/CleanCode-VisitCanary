package es.ulpgc.eite.cleancode.visitcanary.categories;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;

interface CategoryListContract {

  interface View {
    void displayCategoryListData(CategoryListViewModel viewModel);
  }

  interface Presenter {
    void fetchCategoryListData();
    void selectCategoryListData(CategoryItem item);
  }

  interface Model {
    //List<CategoryItem> fetchCategoryListData();
    void fetchCategoryListData(
        CatalogRepository.GetCategoryListCallback callback);
  }
}