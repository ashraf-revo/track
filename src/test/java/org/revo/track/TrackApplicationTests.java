package org.revo.track;

import org.junit.Test;
import org.revo.track.Domain.Call;
import org.revo.track.Domain.Direction;
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
//            Response<Tracker> trackerResponse = service.save(new Tracker()).execute();
//            Response<Location> locationResponse = service.save(new Location(null, "5c63145cf8b0a3519928c005", new Date(), 29.9661564, 31.2766769)).execute();
            Response<Call> callResponse = service.save(new Call(null, "5c63145cf8b0a3519928c005", new Date(), Direction.in, "01120266849")).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

