package skinsenor.jcgf.com.greendaodemo.wy.dbdao;

import android.util.Base64;

import java.io.Serializable;
import java.util.Arrays;


/**
 * Created by wenbaohe on 2017/12/12.
 * 供肤色页面显示的数据模型
 */

public class PagerBModel extends BasePagerModel implements Serializable{

    private int type;//部位id

    private String fraction;//分数
    private String time;//日期
    private String ggg;//日期


    private String witeText;//白色方框提示信息
    private String witeTextdes;

    private String headTextdes;//颈部的文字描述

    private int progessThis;//进度条值
    private int progessLast;
    private int progessSame;

    private byte[] imgbyte;

    public byte[] getImgbyte() {
        return  Base64.decode(imgbyte,Base64.DEFAULT);
    }

    public void setImgbyte(byte[] imgbyte) {
        this.imgbyte =  Base64.encode(imgbyte,Base64.DEFAULT);
    }

    private int gridNumber;//方格位置
    private String gridTipText;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWiteText() {
        return witeText;
    }

    public void setWiteText(String witeText) {
        this.witeText = witeText;
    }

    public String getWiteTextdes() {
        return new String(Base64.decode(witeTextdes.getBytes(),Base64.DEFAULT));
    }

    public void setWiteTextdes(String witeTextdes) {
        this.witeTextdes = new String(Base64.encode(witeTextdes.getBytes(),Base64.DEFAULT));
    }

    public String getHeadTextdes() {
        return headTextdes;
    }

    public void setHeadTextdes(String headTextdes) {
        this.headTextdes = headTextdes;
    }

    public int getProgessThis() {
        return progessThis;
    }

    public void setProgessThis(int progessThis) {
        this.progessThis = progessThis;
    }

    public int getProgessLast() {
        return progessLast;
    }

    public void setProgessLast(int progessLast) {
        this.progessLast = progessLast;
    }

    public int getProgessSame() {
        return progessSame;
    }

    public void setProgessSame(int progessSame) {
        this.progessSame = progessSame;
    }

    public int getGridNumber() {
        return gridNumber;
    }

    public void setGridNumber(int gridNumber) {
        this.gridNumber = gridNumber;
    }

    public String getGridTipText() {
        return gridTipText;
    }

    public void setGridTipText(String gridTipText) {
        this.gridTipText = gridTipText;
    }

    @Override
    public String toString() {
        return "PagerBModel{" +
                "type=" + type +
                ", fraction='" + fraction + '\'' +
                ", time='" + time + '\'' +
                ", ggg='" + ggg + '\'' +
                ", witeText='" + witeText + '\'' +
                ", witeTextdes='" + witeTextdes + '\'' +
                ", headTextdes='" + headTextdes + '\'' +
                ", progessThis=" + progessThis +
                ", progessLast=" + progessLast +
                ", progessSame=" + progessSame +
                ", imgbyte=" + Arrays.toString(imgbyte) +
                ", gridNumber=" + gridNumber +
                ", gridTipText='" + gridTipText + '\'' +
                '}';
    }

    public String getGgg() {
        return ggg;
    }

    public void setGgg(String ggg) {
        this.ggg = ggg;
    }
}
