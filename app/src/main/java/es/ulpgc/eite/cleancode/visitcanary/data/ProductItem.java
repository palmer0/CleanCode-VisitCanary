package es.ulpgc.eite.cleancode.visitcanary.data;


public class ProductItem extends CatalogItem {

  public final String details;
  public String picture;

  public ProductItem(int id, String content, String details) {
    super(id, content);
    this.details = details;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}