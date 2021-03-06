package greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import skinsenor.jcgf.com.greendaodemo.test.Person;
import skinsenor.jcgf.com.greendaodemo.test.User;
import skinsenor.jcgf.com.greendaodemo.wy.dbdao.PagerDBModel;

import greendao.PersonDao;
import greendao.UserDao;
import greendao.PagerDBModelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig personDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig pagerDBModelDaoConfig;

    private final PersonDao personDao;
    private final UserDao userDao;
    private final PagerDBModelDao pagerDBModelDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        personDaoConfig = daoConfigMap.get(PersonDao.class).clone();
        personDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        pagerDBModelDaoConfig = daoConfigMap.get(PagerDBModelDao.class).clone();
        pagerDBModelDaoConfig.initIdentityScope(type);

        personDao = new PersonDao(personDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        pagerDBModelDao = new PagerDBModelDao(pagerDBModelDaoConfig, this);

        registerDao(Person.class, personDao);
        registerDao(User.class, userDao);
        registerDao(PagerDBModel.class, pagerDBModelDao);
    }
    
    public void clear() {
        personDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        pagerDBModelDaoConfig.clearIdentityScope();
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public PagerDBModelDao getPagerDBModelDao() {
        return pagerDBModelDao;
    }

}
