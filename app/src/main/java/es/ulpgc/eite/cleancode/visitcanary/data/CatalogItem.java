package es.ulpgc.eite.cleancode.visitcanary.data;


public abstract class CatalogItem {
  public final int id;
  public final String content;

  public CatalogItem(int id, String content) {
    this.id = id;
    this.content = content;
  }


  @Override
  public String toString() {
    return content;
  }
}