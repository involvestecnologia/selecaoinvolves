package mob.s4.cine.series.dao;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import mob.s4.cine.series.dao.entities.MovieEntity;
import mob.s4.cine.series.dao.utils.RealmInt;
import mob.s4.cine.series.pojo.MoviePojo;

/**
 * Created by robsonmoreira on 26/07/18.
 */

public final class MovieDao {

    public static void saveMovie(MoviePojo pojo) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(pojo.getId());
        movieEntity.setTitle(pojo.getTitle());
        movieEntity.setAverage(pojo.getAverage());
        movieEntity.setSummary(pojo.getSummary());
        movieEntity.setRelease(pojo.getRelease());
        movieEntity.setImagePath(pojo.getImagePath());

        RealmList<RealmInt> realmInts = new RealmList<>();
        for (Integer integer : pojo.getGenres()) {
            RealmInt realmInt = new RealmInt();
            realmInt.setInteger(integer);
            realmInts.add(realmInt);
        }
        movieEntity.setGenres(realmInts);

        realm.insertOrUpdate(movieEntity);

        realm.commitTransaction();
        realm.close();
    }

    public static void removeMovie(MoviePojo pojo) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmObject realmObject = realm.where(MovieEntity.class).equalTo("mId", pojo.getId()).findFirst();
        if (realmObject != null)
            realmObject.deleteFromRealm();

        realm.commitTransaction();
        realm.close();
    }

    public static MovieEntity getMovieById(Integer id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(MovieEntity.class).equalTo("mId", id).findFirst();
    }

    public static void saveMovieList(List<MoviePojo> pojoList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        for (MoviePojo pojo : pojoList) {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setId(pojo.getId());
            movieEntity.setTitle(pojo.getTitle());
            movieEntity.setAverage(pojo.getAverage());
            movieEntity.setSummary(pojo.getSummary());
            movieEntity.setRelease(pojo.getRelease());
            movieEntity.setImagePath(pojo.getImagePath());

            RealmList<RealmInt> realmInts = new RealmList<>();
            for (Integer integer : pojo.getGenres()) {
                RealmInt realmInt = new RealmInt();
                realmInt.setInteger(integer);
                realmInts.add(realmInt);
            }
            movieEntity.setGenres(realmInts);

            realm.insertOrUpdate(movieEntity);
        }

        realm.commitTransaction();
        realm.close();
    }

    public static List<MovieEntity> getMovies() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(MovieEntity.class).findAll();
    }

    public static List<MovieEntity> searchMovies(String text) {
        Realm realm = Realm.getDefaultInstance();
        List<MovieEntity> temp = realm.where(MovieEntity.class).contains("mTitle", text, Case.INSENSITIVE).findAll();
        return temp;
    }

}
