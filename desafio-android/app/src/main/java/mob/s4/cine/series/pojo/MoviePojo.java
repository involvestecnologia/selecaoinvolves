package mob.s4.cine.series.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class MoviePojo implements Serializable {

    private Integer mId;
    private String mTitle;
    private Double mAverage;
    private String mSummary;
    private String mRelease;
    private String mImagePath;
    private List<Integer> mGenres;

    public MoviePojo(Integer id, String title, Double average, String summary, String release, String imagePath, List<Integer> genres) {
        mId = id;
        mTitle = title;
        mAverage = average;
        mSummary = summary;
        mRelease = release;
        mImagePath = imagePath;
        mGenres = genres;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Double getAverage() {
        return mAverage;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getRelease() {
        return mRelease;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public List<Integer> getGenres() {
        return mGenres;
    }

}
