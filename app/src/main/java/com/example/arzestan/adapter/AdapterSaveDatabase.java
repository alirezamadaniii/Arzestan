package com.example.arzestan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arzestan.R;
import com.example.arzestan.model.DataItem;
import com.example.arzestan.model.ModelDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdapterSaveDatabase extends RecyclerView.Adapter<AdapterSaveDatabase.Holder> {


    List<ModelDatabase> dataItems;
    private Context context;

    public AdapterSaveDatabase(List<ModelDatabase> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterSaveDatabase.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new AdapterSaveDatabase.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSaveDatabase.Holder holder, int position) {

        holder.bind(dataItems.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("name",dataItems.get(position).getName());
                bundle.putString("symbol",dataItems.get(position).getSymbol());
                bundle.putString("price",holder.textViewPrice.getText().toString());
                bundle.putInt("id",dataItems.get(position).getId());
                bundle.putString("high24",dataItems.get(position).getHigh24()+"");
                bundle.putString("low24",dataItems.get(position).getLow24()+"");
                bundle.putString("marketCap",dataItems.get(position).getMarketCap()+"");
                bundle.putString("change24",dataItems.get(position).getChange24()+"");
                bundle.putString("change",holder.textViewChange.getText().toString());

                Navigation.findNavController(v).navigate(R.id.action_fragmentWatchList_to_fragmentItemSelect,bundle);


            }
        });

    }




    @Override
    public int getItemCount() {
        return dataItems.size();
    }


    public class Holder extends RecyclerView.ViewHolder{
        ImageView imageLogo;
        TextView textViewName,textViewSymbol;
        TextView textViewPrice,textViewChange;
        ImageView imageChange;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageLogo=itemView.findViewById(R.id.imageViewLogoCoin);
            textViewName=itemView.findViewById(R.id.textViewNameCoin);
            textViewSymbol=itemView.findViewById(R.id.textViewSymbolCoin);
            textViewPrice=itemView.findViewById(R.id.textViewPriceCoin);
            textViewChange=itemView.findViewById(R.id.textViewChangeCoin);
            imageChange=itemView.findViewById(R.id.imageViewUpAndDownCoin);


        }
        public void bind(ModelDatabase dataItem){

            SetColorText(dataItem);
            textViewName.setText(dataItem.getName());
            textViewSymbol.setText(dataItem.getSymbol());
            SetDecimalsForPrice(dataItem);
            //set + or - before precent change
                imageChange.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                textViewChange.setText(dataItem.getChange24());


            Glide.with(context)
                    .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" +dataItem.getIdImage()+ ".png")
                    .thumbnail(Glide.with(context).load(R.drawable.spinner))
                    .into(imageLogo);


        }


        private void SetColorText(ModelDatabase dataItem){
            int greenColor = Color.parseColor("#FF00FF40");
            int redColor = Color.parseColor("#FFFF0000");
            int whiteColor = Color.parseColor("#FFFFFF");
                //imageChange.setColorFilter(redColor);
                textViewChange.setTextColor(Color.RED);

        }

        private void SetDecimalsForPrice(ModelDatabase dataItem) {
                textViewPrice.setText(dataItem.getPrice());

        }









    }

}
