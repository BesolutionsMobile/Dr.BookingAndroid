package com.besolutions.drbookingOriginal.Scenario.ScenarioMyProduct.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.besolutions.drbookingOriginal.R;
import com.besolutions.drbookingOriginal.Scenario.ScenarioMyProduct.Model.Product;
import com.besolutions.drbookingOriginal.Scenario.ScenarioProductDetails.Controller.Product_Details;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    ArrayList<Product> mylist;
    Context mContext;



    public MyProductAdapter(ArrayList<Product> mylist, Context context) {
        this.mylist = mylist;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_product_item,parent,false);
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
        final Product product  = mylist.get(i);


        MainItemHolder mainHolder =(MainItemHolder) holder;

        Glide.with(mContext)
                .load(product.getImage())
                .placeholder(R.drawable.asprine)
                .into(mainHolder.productImage);
        mainHolder.productName.setText(product.getName().toString());
        mainHolder.productPrice.setText(product.getPrice().toString());
        mainHolder.productitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Intent intent=new Intent(mContext, Product_Details.class);
                String id = product.getID();
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
        TextView productName,productPrice;
        ImageView productImage;
        LinearLayout productitem;

        public MainItemHolder(@NonNull View itemView)
        {
            super(itemView);
            productName=(TextView)itemView.findViewById(R.id.productName);
            productPrice=(TextView)itemView.findViewById(R.id.price);
            productImage=(ImageView)itemView.findViewById(R.id.imgproducts);
            productitem=(LinearLayout)itemView.findViewById(R.id.productitem);

        }

    }

}
