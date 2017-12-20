package skinsenor.jcgf.com.greendaodemo.wy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import greendao.DaoMaster;
import greendao.DaoSession;
import greendao.PagerDBModelDao;
import skinsenor.jcgf.com.greendaodemo.BaseApplication;
import skinsenor.jcgf.com.greendaodemo.R;
import skinsenor.jcgf.com.greendaodemo.wy.dbdao.PagerBModel;
import skinsenor.jcgf.com.greendaodemo.wy.dbdao.PagerDBModel;
import skinsenor.jcgf.com.greendaodemo.wy.dbdao.WYDataBaseManager;

public class Main2Activity extends AppCompatActivity {

    DaoSession daoSession;
    PagerDBModelDao pagerDBModelDao;
    QueryBuilder<PagerDBModel> queryBuilder;


    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        img= (ImageView) findViewById(R.id.img);
        daoSession = BaseApplication.getDaoSession();
        pagerDBModelDao = daoSession.getPagerDBModelDao();
        queryBuilder = pagerDBModelDao.queryBuilder();

        init();
    }

    private void init() {
        Database database=pagerDBModelDao.getDatabase();

//        new Helper(this).onUpgrade(database,1,2);

        pagerDBModelDao.deleteAll();

    }


    public void clickBtn(View view) {
        seach();
    }

    public void clickup(View view) {
        updata();
    }

    public void clickinsert(View view) {
        insert();
    }

    public void onclickdelet(View view) {
//        PagerDBModel pagerDBModel1 = pagerDBModelDao.queryBuilder().where(PagerDBModelDao.Properties.Position.eq(0)).unique();
        WYDataBaseManager.getInstance(this).deleteDataAll(pagerDBModelDao);
    }


    private void updata() {
//        PagerDBModel pagerDBModel1 = pagerDBModelDao.queryBuilder().where(PagerDBModelDao.Properties.Position.eq(0)).unique();
        PagerDBModel pagerDBModel1 = new PagerDBModel();
        PagerBModel pagerBModel_0 = new PagerBModel();
        pagerDBModel1.setPagerBModel(pagerBModel_0);

        PagerDBModel pagerDBModel2 = pagerDBModelDao.queryBuilder().where(PagerDBModelDao.Properties.Position.eq(1)).unique();
        PagerDBModel pagerDBModel3 = pagerDBModelDao.queryBuilder().where(PagerDBModelDao.Properties.Position.eq(2)).unique();
        if (pagerDBModel1 != null) {
            pagerDBModel1.getPagerBModel().setHeadTextdes("新的数据1");
            pagerDBModel2.getPagerBModel().setHeadTextdes("新的数据2");
            pagerDBModel3.getPagerBModel().setHeadTextdes("新的数据3");

            WYDataBaseManager.getInstance(this).upData(pagerDBModelDao,pagerDBModel1, pagerDBModel2, pagerDBModel3);
//            pagerDBModelDao.updateInTx(pagerDBModel1, pagerDBModel2, pagerDBModel3);

        }

    }

    int n=0;
    private void seach() {
        List<PagerDBModel> pagerDBModels = pagerDBModelDao.queryBuilder().list();
        for (PagerDBModel dbm : pagerDBModels) {
            Log.e("clickBtn2--->", dbm.getId()+"--"+dbm.getPosition() + "-" + dbm.getPagerBModel().toString());
            Log.e("clickBtn3--->", dbm.getPagerBModel().getWiteTextdes());
            if(n==0){
                byte[]bimap=dbm.getPagerBModel().getImgbyte();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bimap, 0, bimap.length);
                img.setImageBitmap(bitmap);
            }
        }
    }


    int i = 0;

    private void insert() {
        PagerDBModelDao.createTable(pagerDBModelDao.getDatabase(),true);
        /**插入数据*/
        PagerBModel pagerBModel_0 = new PagerBModel();
        pagerBModel_0.setType(i);
        pagerBModel_0.setFraction("58");
        pagerBModel_0.setGridNumber(30);
        pagerBModel_0.setGridTipText("肤色偏黄");
        pagerBModel_0.setHeadTextdes("您的脸颊肤色偏黄");
        pagerBModel_0.setProgessThis(48);
        pagerBModel_0.setProgessLast(39);
        pagerBModel_0.setProgessSame(61);
        pagerBModel_0.setTime("1513065076");
        pagerBModel_0.setWiteText("偏黄");
        pagerBModel_0.setWiteTextdes("您的脸颊肤色偏黄，分布不均匀，需注意补水，多吃蔬菜");

        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] datas = baos.toByteArray();
        pagerBModel_0.setImgbyte(datas);


        PagerDBModel pagerDBModel = new PagerDBModel();
        pagerDBModel.setPosition(pagerBModel_0.getType());
        pagerDBModel.setPagerBModel(pagerBModel_0);

        PagerDBModel pagerDBMode2 = new PagerDBModel();
        pagerDBMode2.setPosition(pagerBModel_0.getType());
        pagerDBMode2.setPagerBModel(pagerBModel_0);

        PagerDBModel pagerDBMode3 = new PagerDBModel();
        pagerDBMode3.setPosition(pagerBModel_0.getType());
        pagerDBMode3.setPagerBModel(pagerBModel_0);

//        pagerDBModelDao.insertInTx(pagerDBModel);
        PagerDBModel[]pagerDBModels=new PagerDBModel[3];
        pagerDBModels[0]=pagerDBModel;
        pagerDBModels[1]=pagerDBMode2;
        pagerDBModels[2]=pagerDBMode3;

        WYDataBaseManager.getInstance(this).insertData(pagerDBModelDao,pagerDBModels[0],pagerDBModels[1],pagerDBModels[2]);
        i++;
    }
}
