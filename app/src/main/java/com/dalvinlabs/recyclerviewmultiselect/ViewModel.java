package com.dalvinlabs.recyclerviewmultiselect;

import android.databinding.ObservableInt;

public class ViewModel {

  public final String name;
  public final String size;
  public final String color;
  public final String price;

  public final ObservableInt textColor = new ObservableInt(R.color.colorPrimary);

  public ViewModel(Product product) {
    this.name = product.name;
    this.size = product.size;
    this.color = product.color;
    this.price = product.price;
  }
}


