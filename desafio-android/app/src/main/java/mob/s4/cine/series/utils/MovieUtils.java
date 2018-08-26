package mob.s4.cine.series.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mob.s4.cine.series.dao.GenreDao;
import mob.s4.cine.series.dao.MovieDao;
import mob.s4.cine.series.dao.entities.GenreEntity;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public final class MovieUtils {

    private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");
    private static final String PATTERN_DATE_BR = "dd/MM/yyyy";
    private static final String PATTERN_DATE_US = "yyyy-MM-dd";

    public static String getCategory(List<Integer> genresId) {
        String categoryString = "";
        List<String> categoryList = MovieUtils.getCategoryList(genresId);
        if (categoryList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < categoryList.size() - 1; i++) {
                if (!categoryList.get(i).matches(" *")) {
                    sb.append(categoryList.get(i));
                    sb.append(", ");
                }
            }
            sb.append(categoryList.get(categoryList.size() - 1).trim());
            categoryString = sb.toString();
        }
        return categoryString;
    }

    public static List<Integer> getGenres(String category) {
        List<Integer> genres = new ArrayList<>();
        String[] arrayCategory = category.split(", ");
        for (String anArrayCategory : arrayCategory) {
            GenreEntity genre = MovieUtils.getGenre(anArrayCategory);
            if (genre != null)
                genres.add(genre.getId());
        }
        return genres;
    }

    private static List<String> getCategoryList(List<Integer> genresId) {
        List<String> categoryList = new ArrayList<>();
        if (genresId != null && !genresId.isEmpty()) {
            for (Integer id : genresId) {
                GenreEntity genre = GenreDao.getGenreById(id);
                if (genre != null)
                    categoryList.add(genre.getName());
            }
        }
        return categoryList;
    }

    private static GenreEntity getGenre(String genreName) {
        List<GenreEntity> genreList = GenreDao.getGenres();
        if (genreList != null) {
            for (GenreEntity entity : genreList) {
                if (genreName.equals(entity.getName()))
                    return entity;
            }
        }
        return null;
    }

    public static String formatDate(Date date) {
        final DateFormat dateTimeFormat = new SimpleDateFormat(PATTERN_DATE_BR, LOCALE_PT_BR);
        return dateTimeFormat.format(date);
    }

    public static String formatStringDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat(PATTERN_DATE_US);
        try {
            Date date = new Date(format.parse(dateString).getTime());
            return formatDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean movieIsSaved(Integer id) {
        return MovieDao.getMovieById(id) != null;
    }

}
