package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProductFragment.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProductFragment.Model.ModelProduct;
import com.besolutions.drbookingOriginal.Scenario.ScenarioProductDetails.Controller.Product_Details;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    ArrayList<ModelProduct> mylist;
    Context mContext;



    public ProductAdapter(ArrayList<ModelProduct> mylist, Context context) {
        this.mylist = mylist;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        MainItemHolder mainHolder = new MainItemHolder(ads) ;


        return mainHolder;
    }

    public class MainHolder extends RecyclerView.ViewHolder{
        public MainHolder(View itemview) {
            super(itemview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {

        int viewType = getItemViewType(i);
        final ModelProduct product  = mylist.get(i);


        MainItemHolder mainHolder =(MainItemHolder) holder;

        Glide.with(mContext)
                .load(product.getImage())
                .placeholder(R.drawable.asprine)
                .into(mainHolder.productImage);
        mainHolder.productName.setText(product.getName());
        mainHolder.productPrice.setText(product.getPrice());
        mainHolder.productitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent intent=new Intent(mContext, Product_Details.class);
                String id = product.getId();
                String name = mainHolder.productName.getText().toString();
                String productPrice = mainHolder.productPrice.getText().toString();
                b.putString("id", String.valueOf(id));
                b.putString("productPrice", productPrice);
                b.putString("name", name);
                intent.putExtras(b);
                v.getContext().startActivity(intent);
            }
        });
        mainHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent intent=new Intent(mContext, Product_Details.class);
                String id = product.getId();
                String name = mainHolder.productName.getText().toString();
                String productPrice = mainHolder.productPrice.getText().toString();
                b.putString("id", String.valueOf(id));
                b.putString("productPrice", productPrice);
                b.putString("name", name);
                intent.putExtras(b);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder
    {
        TextView productName, productPrice;
        ImageView productImage;
        LinearLayout productitem;
        Button buy;
        public MainItemHolder(@NonNull View itemView)
        {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.price);
            productImage = itemView.findViewById(R.id.imgproducts);
            productitem = itemView.findViewById(R.id.productitem);
            buy = itemView.findViewById(R.id.buy);

        }

    }

}
