package es.ulpgc.eite.cleancode.visitcanary.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.visitcanary.R;
import es.ulpgc.eite.cleancode.visitcanary.data.ProductItem;

public class ProductListAdapter
    extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

  private List<ProductItem> itemList;
  private final View.OnClickListener clickListener;


  public ProductListAdapter(View.OnClickListener listener) {

    itemList = new ArrayList();
    clickListener = listener;
  }


  public void addItem(ProductItem item){
    itemList.add(item);
    notifyDataSetChanged();
  }

  public void addItems(List<ProductItem> items){
    itemList.addAll(items);
    notifyDataSetChanged();
  }

  public void setItems(List<ProductItem> items){
    itemList = items;
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.product_list_content, parent, false);
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
