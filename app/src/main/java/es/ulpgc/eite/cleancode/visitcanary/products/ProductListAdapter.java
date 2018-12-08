package es.ulpgc.eite.cleancode.visitcanary.products;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.R;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;

public class ProductListAdapter
    extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

  private final List<ProductItem> itemList;
  private final View.OnClickListener clickListener;


  public ProductListAdapter(
      List<ProductItem> items, View.OnClickListener listener) {
    itemList = items;
    clickListener = listener;
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.category_list_content, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.contentView.setText(itemList.get(position).content);

    holder.itemView.setTag(itemList.get(position));
    holder.itemView.setOnClickListener(clickListener);
  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final TextView contentView;

    ViewHolder(View view) {
      super(view);
      contentView = view.findViewById(R.id.content);
    }
  }
}
