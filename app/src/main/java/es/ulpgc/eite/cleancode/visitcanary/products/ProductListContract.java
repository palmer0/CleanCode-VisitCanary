package es.ulpgc.eite.cleancode.visitcanary.products;

import java.util.List;

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
    List<ProductItem> fetchProductListData(CategoryItem category);
  }
}