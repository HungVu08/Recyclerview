package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<ProductModel> mListData;
    private Context mContext;
    private IItemClick mCallback;

    public void setCallback(IItemClick Callback) {
        this.mCallback = Callback;
    }

    public ProductAdapter(ArrayList<ProductModel> mListData) {
        this.mListData = mListData;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_product_item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel productModel = mListData.get(position);

        holder.tvProductName.setText(productModel.getProductName());
        holder.tvPrice.setText(productModel.getProductPrice());
        holder.tvRating.setText(productModel.getRate());



        Glide.with(mContext).load(productModel.getProductImage()).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return mListData != null? mListData.size():0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvPrice, tvRating;
        ImageView imgProduct, imgWish;
        CardView cvProductItem;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgWish = itemView.findViewById(R.id.imgWish);
            cvProductItem = itemView.findViewById(R.id.cvProductItem);

            cvProductItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    mCallback.onItemClick(position);
                }
            });
            imgWish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onChangWishList(getAdapterPosition());
                }
            });
            tvProductName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onUpDate(getAdapterPosition());
                }
            });

        }




    }
}
