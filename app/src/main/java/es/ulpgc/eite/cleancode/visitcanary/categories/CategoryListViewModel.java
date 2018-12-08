package es.ulpgc.eite.cleancode.visitcanary.categories;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.data.CategoryItem;

// include this library dependency in build.gradle file
// implementation 'android.arch.lifecycle:extensions:1.1.1'
public class CategoryListViewModel extends ViewModel {

  public List<CategoryItem> categories;
}

