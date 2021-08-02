package com.mihaelanegoita.recash.data.net;

import com.google.common.util.concurrent.ListenableFuture;
import com.mihaelanegoita.recash.Constants;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Constants.BASE_VERSION)
    ListenableFuture<List<CategoryRemote>> getCategories();
}
