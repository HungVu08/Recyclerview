package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ProductModel> mListProduct;
    private RecyclerView rvDemo;
    private ProductAdapter mProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        rvDemo = findViewById(R.id.rvDemo);
        mProductAdapter = new ProductAdapter(mListProduct);
        mProductAdapter.setCallback(iItemClick);

        rvDemo.setAdapter(mProductAdapter);

    }

    private IItemClick iItemClick = new IItemClick() {
        @Override
        public void onItemClick(int position) {

        }

        @Override
        public void onChangWishList(int position) {
            ProductModel productModel = mListProduct.get(position);
            productModel.setWish(!productModel.getWish());
            mListProduct.set(position,productModel);
            mProductAdapter.notifyDataSetChanged();

        }

        @Override
        public void onUpDate(int position) {
            ProductModel productModel = mListProduct.get(position);
            productModel.setProductName(productModel.getProductName()+"new");
            mListProduct.set(position,productModel);
            mProductAdapter.notifyDataSetChanged();

        }


    };


    private void initData() {
        mListProduct = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductModel productModel = new ProductModel();
            productModel.setProductName("Product" + i);
            productModel.setProductImage("https://hinhnen123.com/wp-content/uploads/2021/06/hinh-anh-avatar-dep-nhat-7.jpg");
            productModel.setProductPrice("$" + (i + 1 * 1000));
            productModel.setRate(new Random().nextInt(5) + "");
            productModel.setWish(false);

            mListProduct.add(productModel);
        }
    }
}