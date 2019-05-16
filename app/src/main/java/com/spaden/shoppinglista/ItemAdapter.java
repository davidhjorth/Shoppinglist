package com.spaden.shoppinglista;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ListItemHolder> {


    private List<Item> mItemList;
    private MainActivity mMainActivity;


    public ItemAdapter(MainActivity mainActivity, List<Item> itemList){
        mMainActivity = mainActivity;
        mItemList = itemList;
    }


    @NonNull
    @Override
    public ItemAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);

        return new ListItemHolder(itemView);

    }



    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ListItemHolder holder, int position) {
        Item item = mItemList.get(position);
        holder.mStatus.setText(item.getToBuy());
        if (item.getToBuy().length() > 15) {
            holder.mStatus.setText(item.getToBuy().substring(0, 15));
        } else {
            holder.mStatus.setText(item.getToBuy());
        }
        final ListItemHolder finalHolder = holder;
        final int finalposition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item selectedItem = mItemList.get(finalposition);
                selectedItem.setChecked(!selectedItem.getChecked());
                if (selectedItem.getChecked()){
                    finalHolder.itemView.setBackgroundColor(Color.RED);
                }
                else finalHolder.itemView.setBackgroundColor(Color.WHITE);

            }
        });
            holder.mQty.setText(item.getQty());
            holder.btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemList.remove(finalposition);
                    notifyDataSetChanged();
                }
            });

    }







    @Override
    public int getItemCount () {
        return mItemList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView mStatus;
        TextView mQty;
        Button btnDel;

        public ListItemHolder(View view) {
            super(view);
            mStatus = (TextView)
                    view.findViewById(R.id.textViewStatusName);
            mQty = (TextView)
                    view.findViewById(R.id.textViewStatsuQty);
            btnDel = view.findViewById(R.id.btnDelete);
            view.setClickable(true);
            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            mMainActivity.showItem(getAdapterPosition());

        }


    }



}
