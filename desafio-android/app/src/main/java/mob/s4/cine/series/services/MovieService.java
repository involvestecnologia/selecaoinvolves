package mob.s4.cine.series.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.services.objects.MovieObject;
import mob.s4.cine.series.services.objects.ParseObject;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class MovieService extends BaseService {

    public static List<MovieObject> getPopularMovies(int page) throws IOException {
        String url = getUrl("movie/upcoming")
                .concat("&page=")
                .concat(String.valueOf(page)).concat("&region=").concat(REGION);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();

        ParseObject<MovieObject> movieList = null;
        if (response.isSuccessful()) {
            Type listType = new TypeToken<ParseObject<MovieObject>>(){}.getType();
            movieList = new Gson().fromJson(string, listType);
        }

        return movieList == null ? new ArrayList<MovieObject>() : movieList.getResults();
    }

    public static List<MovieObject> searchPopular(String text) throws IOException {
        String url = getUrl("search/movie")
                .concat("&query=").concat(text).concat("&region=").concat(REGION);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();

        ParseObject<MovieObject> movieList = null;
        if (response.isSuccessful()) {
            Type listType = new TypeToken<ParseObject<MovieObject>>(){}.getType();
            movieList = new Gson().fromJson(string, listType);
        }

        return movieList == null ? new ArrayList<MovieObject>() : movieList.getResults();
    }

}
