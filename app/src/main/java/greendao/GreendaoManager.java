package greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import skinsenor.jcgf.com.greendaodemo.test.Helper;

/**
 * Created by wenbaohe on 2017/11/24.
 *
 * greendao数据库管理类
 */

public class GreendaoManager {
    private static GreendaoManager instence;

    public static GreendaoManager getInstence(){
        if(instence==null){
            synchronized (GreendaoManager.class){
                if(instence==null) {
                    instence = new GreendaoManager();
                }
            }
        }
        return  instence;
    }


    private String db_name="";

    private SQLiteDatabase db;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoSession daoSession;
    private DaoMaster daoMaster;

    /**创建数据库*/
    public void oncreatDB(Context context){
        devOpenHelper= new DaoMaster.DevOpenHelper(context,db_name);
        db= devOpenHelper.getWritableDatabase();
        daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();
    }

    public void updateDB(Helper helper, Database db, int oldVersion, int newVersion){
        helper.onUpgrade(db,oldVersion,newVersion);
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public DaoMaster.DevOpenHelper getDevOpenHelper() {
        return devOpenHelper;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}