package es.ulpgc.eite.cleancode.visitcanary.products;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;

// include this library dependency in build.gradle file
// implementation 'android.arch.lifecycle:extensions:1.1.1'
public class ProductListViewModel extends ViewModel {

  public CategoryItem category;
  public List<ProductItem> products;

}

