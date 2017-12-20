package skinsenor.jcgf.com.greendaodemo;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import greendao.DaoMaster;
import greendao.DaoSession;
import skinsenor.jcgf.com.greendaodemo.test.Helper;

/**
 * Created by JCGF-OY on 2017/11/23.
 */

public class BaseApplication extends Application {

    String DB_Name="user_db";
    private static DaoSession daoSession;
    static Helper helper;
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper= new DaoMaster.DevOpenHelper(this,DB_Name);
        SQLiteDatabase db= devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();
        helper=new Helper(this);

    }

    public static Helper getHelper() {
        return helper;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
