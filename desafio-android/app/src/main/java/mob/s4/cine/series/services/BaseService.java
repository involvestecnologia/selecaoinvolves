package mob.s4.cine.series.services;

import okhttp3.OkHttpClient;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public abstract class BaseService {

    public static String BASE_URL = "https://api.themoviedb.org/3/";
    public static String API_KEY = "71e643f4466eb4e93765694f617e6aea";
    public static String LANGUAGE = "pt-BR";
    public static String REGION = "US";

    public static OkHttpClient client = new OkHttpClient();

    private static String query() {
        return "?api_key=".concat(API_KEY).concat("&language=").concat(LANGUAGE);
    }

    public static String getUrl(String endPoint) {
        return BASE_URL.concat(endPoint).concat(query());
    }

}
