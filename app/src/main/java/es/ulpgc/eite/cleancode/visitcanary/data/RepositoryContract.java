package es.ulpgc.eite.cleancode.visitcanary.data;

public interface RepositoryContract {

  void loadCatalog(
      boolean clearFirst, CatalogRepository.FetchCatalogDataCallback callback);

  void getProductList(
      CategoryItem category, CatalogRepository.GetProductListCallback callback);

  void getProductList(
      int categoryId, CatalogRepository.GetProductListCallback callback);

  void getProduct(int id, CatalogRepository.GetProductCallback callback);
  void getCategory(int id, CatalogRepository.GetCategoryCallback callback);
  void getCategoryList(CatalogRepository.GetCategoryListCallback callback);

  void deleteProduct(
      ProductItem product, CatalogRepository.DeleteProductCallback callback);

  void updateProduct(
      ProductItem product, CatalogRepository.UpdateProductCallback callback);

  void deleteCategory(
      CategoryItem category, CatalogRepository.DeleteCategoryCallback callback);

  void updateCategory(
      CategoryItem category, CatalogRepository.UpdateCategoryCallback callback);
}
