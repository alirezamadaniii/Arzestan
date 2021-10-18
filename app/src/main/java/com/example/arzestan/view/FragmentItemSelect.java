package com.example.arzestan.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arzestan.R;
import com.example.arzestan.RoomDao;
import com.example.arzestan.model.AppDatabaseSave;
import com.example.arzestan.model.ModelDatabase;
import com.example.arzestan.model.RoomDaoSave;
import com.klim.tcharts.TChart;
import com.klim.tcharts.entities.ChartData;
import com.klim.tcharts.entities.ChartItem;

import java.util.ArrayList;
import java.util.Random;


public class FragmentItemSelect extends Fragment {

    boolean StarState=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_item_select, container, false);
        TextView textViewName=view.findViewById(R.id.textViewNamePageItemSelect);
        TextView textViewSymbol=view.findViewById(R.id.textViewSymbolPageItemSelect);
        TextView textViewChange24=view.findViewById(R.id.textViewChange24);
        TextView textViewMarketCap=view.findViewById(R.id.textViewMarketCap);
        TextView textViewHigh24=view.findViewById(R.id.textViewHigh24);
        TextView textViewLow24=view.findViewById(R.id.textViewlow24);
        ImageView imageViewLogo=view.findViewById(R.id.imageViewLogoPageItemSelect);
        ImageView imageViewStar=view.findViewById(R.id.imageViewstar);



        //Get Data From Adapter
        Bundle bundle=getArguments();
        textViewName.setText(bundle.getString("name"));
        textViewSymbol.setText(bundle.getString("symbol"));
        int id=bundle.getInt("id");
        textViewChange24.setText(bundle.getString("change24"));
        textViewMarketCap.setText(bundle.getString("marketCap"));
        textViewHigh24.setText(bundle.getString("high24"));
        textViewLow24.setText(bundle.getString("low24"));



        Glide.with(getContext())
                .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" +id+ ".png")
                .thumbnail(Glide.with(getContext()).load(R.drawable.spinner))
                .into(imageViewLogo);




        WebView webView=view.findViewById(R.id.tchart);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.loadUrl("https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=%20"+ bundle.getString("symbol") +"USD&interval=D&hidesidetoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT");
String image="https://s2.coinmarketcap.com/static/img/coins/64x64/" +id+ ".png";


        imageViewStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (StarState==false){
                    imageViewStar.setImageResource(R.drawable.ic_baseline_star_24);
                    StarState=true;

                }else if (StarState==true){
                    imageViewStar.setImageResource(R.drawable.ic_baseline_star_border_24);
                    StarState=false;
                }

                RoomDaoSave dao=AppDatabaseSave.getAppDatabase(getContext()).dao();
                ModelDatabase modelDatabase=new ModelDatabase();
                modelDatabase.setName(bundle.getString("name"));
                modelDatabase.setSymbol(bundle.getString("symbol"));
                modelDatabase.setChange24(bundle.getString("change24"));
                modelDatabase.setMarketCap(bundle.getString("marketCap"));
                modelDatabase.setHigh24(bundle.getString("high24"));
                modelDatabase.setLow24(bundle.getString("low24"));
                modelDatabase.setChange("change");
                modelDatabase.setLogo(image);
                modelDatabase.setIdImage(id);
                modelDatabase.setPrice(bundle.getString("price"));
                dao.addDatabase(modelDatabase);

            }
        });

        return view;
    }
}