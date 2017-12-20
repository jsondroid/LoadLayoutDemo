package skinsenor.jcgf.com.greendaodemo.wy.dbdao;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.lang.ref.WeakReference;

import greendao.DaoSession;
import greendao.PagerDBModelDao;
import skinsenor.jcgf.com.greendaodemo.BaseApplication;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by wenbaohe on 2017/12/13.
 * <p>
 * 数据库管理类
 */

public class WYDataBaseManager {

    private static WYDataBaseManager instance = null;
    private WeakReference<Context> contextWeakReference;

    private WYDataBaseManager(Context context) {
        contextWeakReference = new WeakReference<Context>(context);
        daoSession = BaseApplication.getDaoSession();
    }

    public static WYDataBaseManager getInstance(Context context) {
        if (instance == null) {
            synchronized (WYDataBaseManager.class) {
                if (instance == null) {
                    instance = new WYDataBaseManager(context);
                }
            }
        }
        return instance;
    }

    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }


    /**
     * 插入数据
     */
    public void insertData(AbstractDao abstractDao, Object... objects) {
        abstractDao.insertInTx(objects);
    }

    /**
     * 更新数据
     */
    public void upData(AbstractDao abstractDao, Object... objects) {
        abstractDao.updateInTx(objects);
    }

    /**
     * 删除数据
     */
    public void deleteData(AbstractDao abstractDao, Object... objects) {
        abstractDao.deleteInTx(objects);
    }
    public void deleteDataAll(AbstractDao abstractDao) {
        abstractDao.deleteAll();
    }
}
