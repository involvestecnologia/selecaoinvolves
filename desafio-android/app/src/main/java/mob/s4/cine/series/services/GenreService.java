package mob.s4.cine.series.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.services.objects.GenreObject;
import mob.s4.cine.series.services.objects.ParseObject;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class GenreService extends BaseService {

    public static List<GenreObject> getGenreList() throws IOException {
        Request request = new Request.Builder()
                .url(getUrl("genre/movie/list"))
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();

        ParseObject<GenreObject> genreList = null;
        if (response.isSuccessful()) {
            Type listType = new TypeToken<ParseObject<GenreObject>>(){}.getType();
            genreList = new Gson().fromJson(string, listType);
        }

        return genreList == null ? new ArrayList<GenreObject>() : genreList.getGenres();
    }

}
