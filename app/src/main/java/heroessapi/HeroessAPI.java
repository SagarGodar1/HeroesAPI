package heroessapi;

import model.Heroes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HeroessAPI {
    // 1. Using Object
    @POST("heroes")
    Call<Void> addHero (@Body Heroes heroes);
}
