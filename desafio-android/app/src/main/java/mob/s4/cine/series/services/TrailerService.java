package mob.s4.cine.series.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.services.objects.ParseObject;
import mob.s4.cine.series.services.objects.TrailerObject;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class TrailerService extends BaseService {

    public static List<TrailerObject> getMovieTrailers(Integer id) throws IOException {
        String endPoint = "movie/".concat(id.toString()).concat("/videos");
        Request request = new Request.Builder()
                .url(getUrl(endPoint))
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();

        ParseObject<TrailerObject> traileList = null;
        if (response.isSuccessful()) {
            Type listType = new TypeToken<ParseObject<TrailerObject>>(){}.getType();
            traileList = new Gson().fromJson(string, listType);
        }

        return traileList == null ? new ArrayList<TrailerObject>() : traileList.getResults();
    }

}
