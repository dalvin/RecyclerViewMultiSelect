package com.dalvinlabs.recyclerviewmultiselect;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import com.dalvinlabs.recyclerviewmultiselect.databinding.ItemRecyclerBinding;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

  private final List<Product> products;
  private SelectionTracker<Long> selectionTracker;

  Adapter(List<Product> products) {
    this.products = products;
  }

  public void setSelectionTracker(
      SelectionTracker<Long> selectionTracker) {
    this.selectionTracker = selectionTracker;
  }

  static class Details extends ItemDetailsLookup.ItemDetails<Long> {

    long position;

    Details() {
    }

    @Override
    public int getPosition() {
      return (int) position;
    }

    @Nullable
    @Override
    public Long getSelectionKey() {
      return position;
    }

    @Override
    public boolean inSelectionHotspot(@NonNull MotionEvent e) {
      return true;
    }
  }

  static class KeyProvider extends ItemKeyProvider<Long> {

    KeyProvider(RecyclerView.Adapter adapter) {
      super(ItemKeyProvider.SCOPE_MAPPED);
    }

    @Nullable
    @Override
    public Long getKey(int position) {
      return (long) position;
    }

    @Override
    public int getPosition(@NonNull Long key) {
      long value = key;
      return (int) value;
    }
  }

  static class DetailsLookup extends ItemDetailsLookup<Long> {

    private RecyclerView recyclerView;

    DetailsLookup(RecyclerView recyclerView) {
      this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
      View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
      if (view != null) {
        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
        if (viewHolder instanceof MyViewHolder) {
          return ((MyViewHolder) viewHolder).getItemDetails();
        }
      }
      return null;
    }
  }

  static class Predicate extends SelectionTracker.SelectionPredicate<Long> {

    @Override
    public boolean canSetStateForKey(@NonNull Long key, boolean nextState) {
      return true;
    }

    @Override
    public boolean canSetStateAtPosition(int position, boolean nextState) {
      return true;
    }

    @Override
    public boolean canSelectMultiple() {
      return true;
    }
  }

  class MyViewHolder extends RecyclerView.ViewHolder {

    private ItemRecyclerBinding binding;
    private Details details;

    MyViewHolder(@NonNull ItemRecyclerBinding itemRecyclerBinding) {
      super(itemRecyclerBinding.getRoot());
      binding = itemRecyclerBinding;
      details = new Details();
    }

    void bind(ViewModel viewModel, int position) {
      details.position = position;
      binding.setViewModel(viewModel);
      binding.executePendingBindings();
      if (selectionTracker != null) {
        if (Adapter.this.selectionTracker.isSelected(details.getSelectionKey())) {
          binding.getViewModel().textColor.set(
              binding.getRoot().getContext().getColor(R.color.colorAccent));
          binding.getRoot().setActivated(true);
        } else {
          binding.getViewModel().textColor.set(
              binding.getRoot().getContext().getColor(R.color.colorPrimary));
          binding.getRoot().setActivated(false);
        }
      }
    }

    Details getItemDetails() {
      return details;
    }
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MyViewHolder(
        ItemRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.bind(new ViewModel(products.get(position)), position);
  }

  @Override
  public int getItemCount() {
    return products.size();
  }
}
