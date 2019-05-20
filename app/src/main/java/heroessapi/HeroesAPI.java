package heroessapi;

import java.util.Map;

import model.Heroes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HeroesAPI {

    // 1. Using Object
    @POST("heroes")
    Call<Void> addHero (@Body Heroes heroes);


    //2.Using @Field
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHero(@Field("name") String name, @Field("desc") String desc);


    //3. Using @FieldMap
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addhero (@FieldMap Map<String,String> map);

}
