package es.ulpgc.eite.cleancode.visitcanary.products;

import es.ulpgc.eite.cleancode.visitcanary.data.CatalogRepository;
import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;

interface ProductListContract {

  interface View {
    void displayProductListData(ProductListViewModel viewModel);
  }

  interface Presenter {
    void fetchProductListData();
    void selectProductListData(ProductItem item);
  }

  interface Model {

    void fetchProductListData(
        CategoryItem category, CatalogRepository.GetProductListCallback callback);
  }
}