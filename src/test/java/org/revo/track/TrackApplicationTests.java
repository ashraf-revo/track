package org.revo.track;

import org.junit.Test;
import org.revo.track.Domain.Location;
import org.revo.track.Domain.Tracker;
import org.revo.track.Domain.User;
import retrofit2.Response;

import java.io.IOException;
import java.util.Date;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TrackApplicationTests {

    private final TrackerApiService service = TrackerServiceGenerator.createService(TrackerApiService.class);

    @Test
    public void contextLoads() {
        try {
            Response<User> userResponse = service.getCurrentUser().execute();
            Response<Tracker> trackerResponse = service.save(new Tracker()).execute();
            service.save(new Location(null, trackerResponse.body().getId(), new Date(), 29.9799115,31.2874916)).execute();
            service.save(new Location(null, trackerResponse.body().getId(), new Date(), 29.984930, 31.283372)).execute();
            service.save(new Location(null, trackerResponse.body().getId(), new Date(), 29.9901726,31.2698489)).execute();
            service.save(new Location(null, trackerResponse.body().getId(), new Date(), 29.9954061,31.2518983)).execute();


//            Response<Call> callResponse = service.save(new Call(null, "5c63145cf8b0a3519928c005", new Date(), Direction.in, "01120266849")).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

