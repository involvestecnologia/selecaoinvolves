package mob.s4.cine.series;

import android.app.Application;
import android.content.Context;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mob.s4.cine.series.helpers.InternetHelper;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class CineSeriesApplication extends Application {

    private static CineSeriesApplication mInstance;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mContext = getApplicationContext();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("realm-students.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static boolean isNetworkAvailable() {
        return InternetHelper.isNetworkAvailable(mInstance.getApplicationContext());
    }

    public static File getFileCacheDir() {
        return mInstance.getCacheDir();
    }

    public static Context getContext() {
        return mContext;
    }

}
