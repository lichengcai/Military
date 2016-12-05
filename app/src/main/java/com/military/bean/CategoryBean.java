package com.military.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chengcai on 2016/11/29.
 */

public class CategoryBean {
    private String name;
    private String linkUrl;
    private String imgUrl;

    public CategoryBean(String name, String linkUrl) {
        this.name = name;
        this.linkUrl = linkUrl;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "name='" + name + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public static Map<String,ArrayList<CategoryBean>> getCategoryData() {
        Map<String, ArrayList<CategoryBean>> map = new LinkedHashMap<>();

        ArrayList<CategoryBean> array_airCraft = new ArrayList<>();
        array_airCraft.add(new CategoryBean("战斗机","http://weapon.huanqiu.com/weaponlist/aircraft/list_1_0"));
        array_airCraft.add(new CategoryBean("攻击机","http://weapon.huanqiu.com/weaponlist/aircraft/list_2_0"));
        array_airCraft.add(new CategoryBean("轰炸机","http://weapon.huanqiu.com/weaponlist/aircraft/list_3_0"));
        array_airCraft.add(new CategoryBean("教练机","http://weapon.huanqiu.com/weaponlist/aircraft/list_4_0"));
        array_airCraft.add(new CategoryBean("预警机","http://weapon.huanqiu.com/weaponlist/aircraft/list_5_0"));
        array_airCraft.add(new CategoryBean("侦察机","http://weapon.huanqiu.com/weaponlist/aircraft/list_6_0"));
        array_airCraft.add(new CategoryBean("反潜机","http://weapon.huanqiu.com/weaponlist/aircraft/list_7_0"));
        array_airCraft.add(new CategoryBean("电子战机","http://weapon.huanqiu.com/weaponlist/aircraft/list_8_0"));
        array_airCraft.add(new CategoryBean("无人机","http://weapon.huanqiu.com/weaponlist/aircraft/list_9_0"));
        array_airCraft.add(new CategoryBean("运输机","http://weapon.huanqiu.com/weaponlist/aircraft/list_10_0"));
        array_airCraft.add(new CategoryBean("飞艇","http://weapon.huanqiu.com/weaponlist/aircraft/list_11_0"));
        array_airCraft.add(new CategoryBean("试验机","http://weapon.huanqiu.com/weaponlist/aircraft/list_12_0"));
        array_airCraft.add(new CategoryBean("加油机","http://weapon.huanqiu.com/weaponlist/aircraft/list_13_0"));
        array_airCraft.add(new CategoryBean("通用飞机","http://weapon.huanqiu.com/weaponlist/aircraft/list_14_0"));
        array_airCraft.add(new CategoryBean("干线","http://weapon.huanqiu.com/weaponlist/aircraft/list_15_0"));
        array_airCraft.add(new CategoryBean("支线","http://weapon.huanqiu.com/weaponlist/aircraft/list_16_0"));
        array_airCraft.add(new CategoryBean("运输直升机","http://weapon.huanqiu.com/weaponlist/aircraft/list_17_0"));
        array_airCraft.add(new CategoryBean("武装直升机","http://weapon.huanqiu.com/weaponlist/aircraft/list_18_0"));
        array_airCraft.add(new CategoryBean("多用途直升机","http://weapon.huanqiu.com/weaponlist/aircraft/list_19_0"));

        ArrayList<CategoryBean> array_warship = new ArrayList<>();
        array_warship.add(new CategoryBean("航空母舰","http://weapon.huanqiu.com/weaponlist/warship/list_1_0"));
        array_warship.add(new CategoryBean("战列舰","http://weapon.huanqiu.com/weaponlist/warship/list_2_0"));
        array_warship.add(new CategoryBean("巡洋舰","http://weapon.huanqiu.com/weaponlist/warship/list_3_0"));
        array_warship.add(new CategoryBean("驱逐舰","http://weapon.huanqiu.com/weaponlist/warship/list_4_0"));
        array_warship.add(new CategoryBean("护卫舰","http://weapon.huanqiu.com/weaponlist/warship/list_5_0"));
        array_warship.add(new CategoryBean("两栖作战舰艇","http://weapon.huanqiu.com/weaponlist/warship/list_6_0"));
        array_warship.add(new CategoryBean("核潜艇","http://weapon.huanqiu.com/weaponlist/warship/list_7_0"));
        array_warship.add(new CategoryBean("常规潜艇","http://weapon.huanqiu.com/weaponlist/warship/list_8_0"));
        array_warship.add(new CategoryBean("水雷战舰艇","http://weapon.huanqiu.com/weaponlist/warship/list_9_0"));
        array_warship.add(new CategoryBean("导弹艇","http://weapon.huanqiu.com/weaponlist/warship/list_10_0"));
        array_warship.add(new CategoryBean("巡逻舰艇","http://weapon.huanqiu.com/weaponlist/warship/list_11_0"));
        array_warship.add(new CategoryBean("保障辅助舰艇","http://weapon.huanqiu.com/weaponlist/warship/list_12_0"));
        array_warship.add(new CategoryBean("气垫艇","http://weapon.huanqiu.com/weaponlist/warship/list_13_0"));
        array_warship.add(new CategoryBean("其他","http://weapon.huanqiu.com/weaponlist/warship/list_14_0"));

        ArrayList<CategoryBean> array_guns = new ArrayList<>();
        array_guns.add(new CategoryBean("非自动步枪","http://weapon.huanqiu.com/weaponlist/guns/list_1_0"));
        array_guns.add(new CategoryBean("自动步枪","http://weapon.huanqiu.com/weaponlist/guns/list_2_0"));
        array_guns.add(new CategoryBean("冲锋枪","http://weapon.huanqiu.com/weaponlist/guns/list_3_0"));
        array_guns.add(new CategoryBean("狙击枪","http://weapon.huanqiu.com/weaponlist/guns/list_4_0"));
        array_guns.add(new CategoryBean("手枪","http://weapon.huanqiu.com/weaponlist/guns/list_5_0"));
        array_guns.add(new CategoryBean("机枪","http://weapon.huanqiu.com/weaponlist/guns/list_6_0"));
        array_guns.add(new CategoryBean("霰弹枪","http://weapon.huanqiu.com/weaponlist/guns/list_7_0"));
        array_guns.add(new CategoryBean("火箭筒","http://weapon.huanqiu.com/weaponlist/guns/list_8_0"));
        array_guns.add(new CategoryBean("榴弹发射器","http://weapon.huanqiu.com/weaponlist/guns/list_9_0"));
        array_guns.add(new CategoryBean("附件","http://weapon.huanqiu.com/weaponlist/guns/list_10_0"));
        array_guns.add(new CategoryBean("刀具","http://weapon.huanqiu.com/weaponlist/guns/list_11_0"));
        array_guns.add(new CategoryBean("迷彩服","http://weapon.huanqiu.com/weaponlist/guns/list_12_0"));

        ArrayList<CategoryBean> array_tank = new ArrayList<>();
        array_tank.add(new CategoryBean("步兵战车","http://weapon.huanqiu.com/weaponlist/tank/list_1_0"));
        array_tank.add(new CategoryBean("主战坦克","http://weapon.huanqiu.com/weaponlist/tank/list_2_0"));
        array_tank.add(new CategoryBean("特种坦克","http://weapon.huanqiu.com/weaponlist/tank/list_3_0"));
        array_tank.add(new CategoryBean("装甲运兵车","http://weapon.huanqiu.com/weaponlist/tank/list_4_0"));
        array_tank.add(new CategoryBean("装甲侦察车","http://weapon.huanqiu.com/weaponlist/tank/list_5_0"));
        array_tank.add(new CategoryBean("装甲指挥车","http://weapon.huanqiu.com/weaponlist/tank/list_6_0"));
        array_tank.add(new CategoryBean("救护车","http://weapon.huanqiu.com/weaponlist/tank/list_7_0"));
        array_tank.add(new CategoryBean("工程抢修车","http://weapon.huanqiu.com/weaponlist/tank/list_8_0"));
        array_tank.add(new CategoryBean("布/扫雷车","http://weapon.huanqiu.com/weaponlist/tank/list_9_0"));
        array_tank.add(new CategoryBean("越野车","http://weapon.huanqiu.com/weaponlist/tank/list_10_0"));
        array_tank.add(new CategoryBean("其他特种装甲车辆","http://weapon.huanqiu.com/weaponlist/tank/list_11_0"));

        ArrayList<CategoryBean> array_artillery = new ArrayList<>();
        array_artillery.add(new CategoryBean("榴弹炮","http://weapon.huanqiu.com/weaponlist/artillery/list_1_0"));
        array_artillery.add(new CategoryBean("加农炮","http://weapon.huanqiu.com/weaponlist/artillery/list_2_0"));
        array_artillery.add(new CategoryBean("加农榴弹炮","http://weapon.huanqiu.com/weaponlist/artillery/list_3_0"));
        array_artillery.add(new CategoryBean("迫击炮","http://weapon.huanqiu.com/weaponlist/artillery/list_4_0"));
        array_artillery.add(new CategoryBean("火箭炮","http://weapon.huanqiu.com/weaponlist/artillery/list_5_0"));
        array_artillery.add(new CategoryBean("高射炮","http://weapon.huanqiu.com/weaponlist/artillery/list_6_0"));
        array_artillery.add(new CategoryBean("坦克炮","http://weapon.huanqiu.com/weaponlist/artillery/list_7_0"));
        array_artillery.add(new CategoryBean("反坦克炮","http://weapon.huanqiu.com/weaponlist/artillery/list_8_0"));
        array_artillery.add(new CategoryBean("无后坐炮","http://weapon.huanqiu.com/weaponlist/artillery/list_9_0"));
        array_artillery.add(new CategoryBean("装甲车载炮","http://weapon.huanqiu.com/weaponlist/artillery/list_10_0"));
        array_artillery.add(new CategoryBean("舰炮","http://weapon.huanqiu.com/weaponlist/artillery/list_11_0"));
        array_artillery.add(new CategoryBean("航空炮","http://weapon.huanqiu.com/weaponlist/artillery/list_12_0"));
        array_artillery.add(new CategoryBean("自行火炮","http://weapon.huanqiu.com/weaponlist/artillery/list_13_0"));
        array_artillery.add(new CategoryBean("弹炮结合系统","http://weapon.huanqiu.com/weaponlist/artillery/list_14_0"));

        ArrayList<CategoryBean> array_missile = new ArrayList<>();
        array_missile.add(new CategoryBean("反弹道导弹","http://weapon.huanqiu.com/weaponlist/missile/list_1_0"));
        array_missile.add(new CategoryBean("地地导弹","http://weapon.huanqiu.com/weaponlist/missile/list_2_0"));
        array_missile.add(new CategoryBean("舰地导弹","http://weapon.huanqiu.com/weaponlist/missile/list_3_0"));
        array_missile.add(new CategoryBean("地空导弹","http://weapon.huanqiu.com/weaponlist/missile/list_4_0"));
        array_missile.add(new CategoryBean("舰空导弹","http://weapon.huanqiu.com/weaponlist/missile/list_5_0"));
        array_missile.add(new CategoryBean("空空导弹","http://weapon.huanqiu.com/weaponlist/missile/list_6_0"));
        array_missile.add(new CategoryBean("空地导弹","http://weapon.huanqiu.com/weaponlist/missile/list_7_0"));
        array_missile.add(new CategoryBean("潜艇导弹","http://weapon.huanqiu.com/weaponlist/missile/list_8_0"));
        array_missile.add(new CategoryBean("空舰导弹","http://weapon.huanqiu.com/weaponlist/missile/list_9_0"));
        array_missile.add(new CategoryBean("岸舰导弹","http://weapon.huanqiu.com/weaponlist/missile/list_10_0"));
        array_missile.add(new CategoryBean("舰舰导弹","http://weapon.huanqiu.com/weaponlist/missile/list_11_0"));

        ArrayList<CategoryBean> array_space = new ArrayList<>();
        array_space.add(new CategoryBean("航天机构","http://weapon.huanqiu.com/weaponlist/spaceship/list_1_0"));
        array_space.add(new CategoryBean("运载火箭","http://weapon.huanqiu.com/weaponlist/spaceship/list_2_0"));
        array_space.add(new CategoryBean("航天基地","http://weapon.huanqiu.com/weaponlist/spaceship/list_3_0"));
        array_space.add(new CategoryBean("技术试验卫星","http://weapon.huanqiu.com/weaponlist/spaceship/list_4_0"));
        array_space.add(new CategoryBean("军事卫星","http://weapon.huanqiu.com/weaponlist/spaceship/list_5_0"));
        array_space.add(new CategoryBean("科学卫星","http://weapon.huanqiu.com/weaponlist/spaceship/list_6_0"));
        array_space.add(new CategoryBean("应用卫星","http://weapon.huanqiu.com/weaponlist/spaceship/list_7_0"));
        array_space.add(new CategoryBean("空间探测器","http://weapon.huanqiu.com/weaponlist/spaceship/list_8_0"));
        array_space.add(new CategoryBean("航天飞机","http://weapon.huanqiu.com/weaponlist/spaceship/list_9_0"));
        array_space.add(new CategoryBean("宇宙飞船","http://weapon.huanqiu.com/weaponlist/spaceship/list_10_0"));


        map.put("舰船舰艇",array_warship);
        map.put("导弹武器",array_missile);
        map.put("坦克装甲车辆",array_tank);
        map.put("枪械与单兵",array_guns);
        map.put("飞行器",array_airCraft);
        map.put("火炮",array_artillery);
        map.put("航空航天",array_space);
        return map;
    }
}

