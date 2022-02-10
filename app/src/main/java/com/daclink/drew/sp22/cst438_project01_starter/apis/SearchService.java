package com.daclink.drew.sp22.cst438_project01_starter.apis;

import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("?type=movie")
    Call<APIValues> searchValues(
            @Query("title") String title
    );
}
