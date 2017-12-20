package skinsenor.jcgf.com.greendaodemo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.Query;

import java.util.List;

import greendao.DaoSession;
import greendao.FontsUtils;
import greendao.PersonDao;
import greendao.UserDao;
import skinsenor.jcgf.com.greendaodemo.BaseApplication;
import skinsenor.jcgf.com.greendaodemo.R;

public class MainActivity extends AppCompatActivity {

    DaoSession daoSession;
    UserDao userDao;
    PersonDao personDao;
    Query<User> queryBuilder;
    Query<Person> personQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoSession= BaseApplication.getDaoSession();
        userDao=daoSession.getUserDao();
        personDao=daoSession.getPersonDao();

        new Helper(this).onUpgrade(userDao.getDatabase(),1,2);
//        DaoMaster.dropAllTables(userDao.getDatabase(), true);
//        DaoMaster.createAllTables(userDao.getDatabase(), true);//创建新的表
//        for(int i=0;i<10;i++){
////            User user=new User();
////            user.setHeadimg("");
////            user.setNamee("name"+i);
////            user.setNumber("12345678901");
////            userDao.insert(user);
//
//            Person person=new Person();
//            person.setNamen("naamr"+i);
//            personDao.insert(person);
//        }

//        User user=new User();
//        user.setNumber("1234455");
//        Pers pers=new Pers();
//        pers.setName("牛逼");
//        user.setPers(pers);
//        userDao.insertOrReplace(user);


        queryBuilder=userDao.queryBuilder().build();
        personQuery=personDao.queryBuilder().build();
    }
    boolean b=false;
    public void onClickbtn(View view){
        FontsUtils.setAppTypeface(this);

        List<User>users=userDao.queryBuilder().where(UserDao.Properties.Numberb.eq("1234455")).limit(1).list();

        Property propertys[]=userDao.getProperties();
        for (Property property:propertys){
            Log.e("property-->",property.columnName);
        }

//        List<User>users=queryBuilder.list();
        for (User user:users){
            Log.e("信息-->",user.getId()+"-"+user.getNumberb()+"-"+user.getPers().getName());
        }

    }
}
