package skinsenor.jcgf.com.greendaodemo.wy.dbdao;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.converter.PropertyConverter;

import skinsenor.jcgf.com.greendaodemo.wy.dbdao.PagerBModel;

/**
 * Created by wenbaohe on 2017/12/13.
 */

@Entity()
public class PagerDBModel {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "PERSITION")
    private int position;

    @Convert(converter = PagerBModelConverter.class, columnType = String.class)//将对象转为json存入数据库中
    private PagerBModel pagerBModel;



    @Generated(hash = 1833120257)
    public PagerDBModel(Long id, int position, PagerBModel pagerBModel) {
        this.id = id;
        this.position = position;
        this.pagerBModel = pagerBModel;
    }



    @Generated(hash = 833187944)
    public PagerDBModel() {
    }



    public Long getId() {
        return this.id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public PagerBModel getPagerBModel() {
        return this.pagerBModel;
    }



    public void setPagerBModel(PagerBModel pagerBModel) {
        this.pagerBModel = pagerBModel;
    }



    public int getPosition() {
        return this.position;
    }



    public void setPosition(int position) {
        this.position = position;
    }



    public static class PagerBModelConverter implements PropertyConverter<PagerBModel, String> {


        @Override
        public PagerBModel convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, PagerBModel.class);
        }

        @Override
        public String convertToDatabaseValue(PagerBModel entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}
