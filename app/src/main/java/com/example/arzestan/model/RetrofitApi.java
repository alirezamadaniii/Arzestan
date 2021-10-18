package com.example.arzestan.model;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface RetrofitApi {
    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=100&sortBy=market_cap&sortType=desc&convert=USD&cryptoType=all&tagType=all&audited=false&aux=ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,max_supply,circulating_supply,total_supply,volume_7d,volume_30d")
   Observable<AllMarketModel> list();
}
