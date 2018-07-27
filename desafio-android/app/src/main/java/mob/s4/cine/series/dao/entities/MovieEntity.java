package mob.s4.cine.series.dao.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import mob.s4.cine.series.dao.utils.RealmInt;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class MovieEntity extends RealmObject {

    @PrimaryKey
    private Integer mId;
    private String mTitle;
    private Double mAverage;
    private String mSummary;
    private String mRelease;
    private String mImagePath;
    private RealmList<RealmInt> mGenres;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Double getAverage() {
        return mAverage;
    }

    public void setAverage(Double average) {
        mAverage = average;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getRelease() {
        return mRelease;
    }

    public void setRelease(String release) {
        mRelease = release;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public RealmList<RealmInt> getGenres() {
        return mGenres;
    }

    public void setGenres(RealmList<RealmInt> genres) {
        mGenres = genres;
    }

}
