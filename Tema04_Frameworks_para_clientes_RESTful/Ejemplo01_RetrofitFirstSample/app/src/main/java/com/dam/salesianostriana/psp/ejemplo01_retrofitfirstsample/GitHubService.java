package com.dam.salesianostriana.psp.ejemplo01_retrofitfirstsample;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Jesús Pallares on 09/12/2015.
 */
public interface GitHubService {

    @GET("/users/{user}/repos")
    Call<List<GitHub>> listRepos(@Path("user") String user);
}
