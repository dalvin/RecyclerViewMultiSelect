package com.dalvinlabs.recyclerviewmultiselect;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import com.dalvinlabs.recyclerviewmultiselect.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    Adapter adapter = new Adapter(Product.getProducts());
    binding.recyclerView.setAdapter(adapter);
    SelectionTracker<Long> selectionTracker = new SelectionTracker.Builder<>("my_selection",
        binding.recyclerView,
        new Adapter.KeyProvider(binding.recyclerView.getAdapter()),
        new Adapter.DetailsLookup(binding.recyclerView),
        StorageStrategy.createLongStorage())
        .withSelectionPredicate(new Adapter.Predicate())
        .build();
    adapter.setSelectionTracker(selectionTracker);
  }
}
