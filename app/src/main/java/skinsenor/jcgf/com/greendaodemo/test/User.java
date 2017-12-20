package skinsenor.jcgf.com.greendaodemo.test;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by JCGF-OY on 2017/11/24.
 *  @Id：对于数据库来说，在数据表中作为主键，类型默认为long型，autoincrement =true使得id自增。

 @Property ：将java class中的字段名映射为Property 提供的名字，在上述代码中就是将name映射为USERNAME，默认情况下，如果字段是驼峰命名转为下划线命名，如customName 转换为                                CUSTOM_NAME。

 @NotNull： 标记基本类型为非空。

 @Transient  ：表示java class中的该字段不会存储在数据库中，是一个缓存值。
 */

@Entity()
public class User {


    @Id(autoincrement = true)
    private Long id;
    @Convert(converter = PersConverter.class, columnType = String.class)//将对象转为json存入数据库中
    private Pers pers;
    @Property(nameInDb = "USERNAMN") //修改列的字段名(注：若修改USERNAME字段，将会丢失该字段下的数据，但修改numberb则不会丢失)
    private String numberb;






    @Generated(hash = 1546264326)
    public User(Long id, Pers pers, String numberb) {
        this.id = id;
        this.pers = pers;
        this.numberb = numberb;
    }



    @Generated(hash = 586692638)
    public User() {
    }






    public Long getId() {
        return this.id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Pers getPers() {
        return this.pers;
    }



    public void setPers(Pers pers) {
        this.pers = pers;
    }



    public String getNumberb() {
        return this.numberb;
    }



    public void setNumberb(String numberb) {
        this.numberb = numberb;
    }

 



    public static class PersConverter implements PropertyConverter<Pers, String> {


        @Override
        public Pers convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Pers.class);
        }

        @Override
        public String convertToDatabaseValue(Pers entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}
