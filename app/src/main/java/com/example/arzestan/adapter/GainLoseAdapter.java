package com.example.arzestan.adapter;

import android.content.Context;
import android.graphics.Color;
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

import java.util.ArrayList;

public class GainLoseAdapter extends RecyclerView.Adapter<GainLoseAdapter.Holder>{

    ArrayList<DataItem> dataItems;
    private Context context;

    public GainLoseAdapter(ArrayList<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.bind(dataItems.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("name",dataItems.get(position).getName());
                bundle.putString("symbol",dataItems.get(position).getSymbol());
                bundle.putString("price",holder.textViewPrice.getText().toString());
                bundle.putInt("id",dataItems.get(position).getId());
                bundle.putString("high24",dataItems.get(position).getHigh24h()+"");
                bundle.putString("low24",dataItems.get(position).getLow24h()+"");
                bundle.putString("marketCap",dataItems.get(position).getListQuote().get(0).getMarketCap()+"");
                bundle.putString("change24",dataItems.get(position).getListQuote().get(0).getPercentChange24h()+"");
                bundle.putString("change",holder.textViewChange.getText().toString());

                Navigation.findNavController(v).navigate(R.id.action_fragmentMarket_to_fragmentItemSelect,bundle);
            }
        });

    }




    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public void updateData(ArrayList<DataItem> newdata) {
        dataItems = newdata;
        notifyDataSetChanged();

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
       public void bind(DataItem dataItem){

           loadCoinlogo(dataItem);
           SetColorText(dataItem);
           textViewName.setText(dataItem.getName());
           textViewSymbol.setText(dataItem.getSymbol());
           SetDecimalsForPrice(dataItem);
           //set + or - before precent change
           if (dataItem.getListQuote().get(0).getPercentChange24h() > 0){
               imageChange.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
               textViewChange.setText(String.format("%.2f",dataItem.getListQuote().get(0).getPercentChange24h()) + "%");
           }else if (dataItem.getListQuote().get(0).getPercentChange24h() < 0){
               imageChange.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
               textViewChange.setText(String.format("%.2f",dataItem.getListQuote().get(0).getPercentChange24h()) + "%");
           }else {
               textViewChange.setText(String.format("%.2f",dataItem.getListQuote().get(0).getPercentChange24h()) + "%");
           }

       }

       private void loadCoinlogo(DataItem dataItem) {
           Glide.with(context)
                   .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" +dataItem.getId()+ ".png")
                   .thumbnail(Glide.with(context).load(R.drawable.spinner))
                   .into(imageLogo);
       }

       private void SetColorText(DataItem dataItem){
           int greenColor = Color.parseColor("#FF00FF40");
           int redColor = Color.parseColor("#FFFF0000");
           int whiteColor = Color.parseColor("#FFFFFF");
           if (dataItem.getListQuote().get(0).getPercentChange24h() < 0){
               //imageChange.setColorFilter(redColor);
               textViewChange.setTextColor(Color.RED);
           }else if (dataItem.getListQuote().get(0).getPercentChange24h() > 0){
            //   imageChange.setColorFilter(greenColor);
               textViewChange.setTextColor(Color.GREEN);
           }else {
             //  imageChange.setColorFilter(whiteColor);
               textViewChange.setTextColor(Color.WHITE);
           }
       }

       private void SetDecimalsForPrice(DataItem dataItem) {
           if (dataItem.getListQuote().get(0).getPrice() < 1){
               textViewPrice.setText("$" + String.format("%.6f",dataItem.getListQuote().get(0).getPrice()));
           }else if (dataItem.getListQuote().get(0).getPrice() < 10){
               textViewPrice.setText("$" + String.format("%.4f",dataItem.getListQuote().get(0).getPrice()));
           }else {
               textViewPrice.setText("$" + String.format("%.2f",dataItem.getListQuote().get(0).getPrice()));
           }
       }









   }
}
