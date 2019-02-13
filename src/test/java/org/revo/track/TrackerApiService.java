package org.revo.track;

import org.revo.track.Domain.Call;
import org.revo.track.Domain.Location;
import org.revo.track.Domain.Tracker;
import org.revo.track.Domain.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static org.revo.track.Domain.Role.Paths.USER_PATH;

public interface TrackerApiService {
    @GET("/auth/user")
    retrofit2.Call<User> getCurrentUser();

    @POST(USER_PATH + "/tracker")
    retrofit2.Call<Tracker> save(@Body Tracker tracker);

    @POST(USER_PATH + "/call")
    retrofit2.Call<Call> save(@Body Call call);

    @POST(USER_PATH + "/location")
    retrofit2.Call<Location> save(@Body Location location);
}
