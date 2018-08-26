package mob.s4.cine.series.dao;

import java.util.List;

import io.realm.Realm;
import mob.s4.cine.series.pojo.GenrePojo;
import mob.s4.cine.series.dao.entities.GenreEntity;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public final class GenreDao {

    public static void saveGenreList(List<GenrePojo> pojoList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        for (GenrePojo pojo : pojoList) {
            GenreEntity genreEntity = new GenreEntity();
            genreEntity.setId(pojo.getId());
            genreEntity.setName(pojo.getName());

            realm.insertOrUpdate(genreEntity);
        }

        realm.commitTransaction();
        realm.close();
    }

    public static GenreEntity getGenreById(Integer id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(GenreEntity.class).equalTo("mId", id).findFirst();
    }

    public static List<GenreEntity> getGenres() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(GenreEntity.class).findAll();
    }

}
