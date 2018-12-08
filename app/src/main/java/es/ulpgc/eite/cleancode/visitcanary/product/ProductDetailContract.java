package es.ulpgc.eite.cleancode.visitcanary.product;

interface ProductDetailContract {

  interface View {
    void displayProductDetailData(ProductDetailViewModel viewModel);
  }

  interface Presenter {
    void fetchProductDetailData();
  }

  interface Model {
    //CatalogItem fetchProductDetailData();
  }
}