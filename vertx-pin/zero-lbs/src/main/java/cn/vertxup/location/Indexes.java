/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.location;


import cn.vertxup.location.tables.LCity;
import cn.vertxup.location.tables.LCountry;
import cn.vertxup.location.tables.LFloor;
import cn.vertxup.location.tables.LLocation;
import cn.vertxup.location.tables.LRegion;
import cn.vertxup.location.tables.LState;
import cn.vertxup.location.tables.LTent;
import cn.vertxup.location.tables.LYard;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>DB_ETERNAL</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index L_CITY_CODE = Indexes0.L_CITY_CODE;
    public static final Index L_CITY_PRIMARY = Indexes0.L_CITY_PRIMARY;
    public static final Index L_COUNTRY_CODE = Indexes0.L_COUNTRY_CODE;
    public static final Index L_COUNTRY_PHONE_PREFIX = Indexes0.L_COUNTRY_PHONE_PREFIX;
    public static final Index L_COUNTRY_PRIMARY = Indexes0.L_COUNTRY_PRIMARY;
    public static final Index L_FLOOR_CODE = Indexes0.L_FLOOR_CODE;
    public static final Index L_FLOOR_PRIMARY = Indexes0.L_FLOOR_PRIMARY;
    public static final Index L_LOCATION_CODE = Indexes0.L_LOCATION_CODE;
    public static final Index L_LOCATION_PRIMARY = Indexes0.L_LOCATION_PRIMARY;
    public static final Index L_REGION_CODE = Indexes0.L_REGION_CODE;
    public static final Index L_REGION_PRIMARY = Indexes0.L_REGION_PRIMARY;
    public static final Index L_STATE_CODE = Indexes0.L_STATE_CODE;
    public static final Index L_STATE_PRIMARY = Indexes0.L_STATE_PRIMARY;
    public static final Index L_TENT_CODE = Indexes0.L_TENT_CODE;
    public static final Index L_TENT_PRIMARY = Indexes0.L_TENT_PRIMARY;
    public static final Index L_YARD_CODE = Indexes0.L_YARD_CODE;
    public static final Index L_YARD_PRIMARY = Indexes0.L_YARD_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index L_CITY_CODE = Internal.createIndex("CODE", LCity.L_CITY, new OrderField[] { LCity.L_CITY.CODE, LCity.L_CITY.SIGMA }, true);
        public static Index L_CITY_PRIMARY = Internal.createIndex("PRIMARY", LCity.L_CITY, new OrderField[] { LCity.L_CITY.KEY }, true);
        public static Index L_COUNTRY_CODE = Internal.createIndex("CODE", LCountry.L_COUNTRY, new OrderField[] { LCountry.L_COUNTRY.CODE, LCountry.L_COUNTRY.SIGMA }, true);
        public static Index L_COUNTRY_PHONE_PREFIX = Internal.createIndex("PHONE_PREFIX", LCountry.L_COUNTRY, new OrderField[] { LCountry.L_COUNTRY.PHONE_PREFIX, LCountry.L_COUNTRY.SIGMA }, true);
        public static Index L_COUNTRY_PRIMARY = Internal.createIndex("PRIMARY", LCountry.L_COUNTRY, new OrderField[] { LCountry.L_COUNTRY.KEY }, true);
        public static Index L_FLOOR_CODE = Internal.createIndex("CODE", LFloor.L_FLOOR, new OrderField[] { LFloor.L_FLOOR.CODE, LFloor.L_FLOOR.SIGMA }, true);
        public static Index L_FLOOR_PRIMARY = Internal.createIndex("PRIMARY", LFloor.L_FLOOR, new OrderField[] { LFloor.L_FLOOR.KEY }, true);
        public static Index L_LOCATION_CODE = Internal.createIndex("CODE", LLocation.L_LOCATION, new OrderField[] { LLocation.L_LOCATION.CODE, LLocation.L_LOCATION.SIGMA }, true);
        public static Index L_LOCATION_PRIMARY = Internal.createIndex("PRIMARY", LLocation.L_LOCATION, new OrderField[] { LLocation.L_LOCATION.KEY }, true);
        public static Index L_REGION_CODE = Internal.createIndex("CODE", LRegion.L_REGION, new OrderField[] { LRegion.L_REGION.CODE, LRegion.L_REGION.SIGMA }, true);
        public static Index L_REGION_PRIMARY = Internal.createIndex("PRIMARY", LRegion.L_REGION, new OrderField[] { LRegion.L_REGION.KEY }, true);
        public static Index L_STATE_CODE = Internal.createIndex("CODE", LState.L_STATE, new OrderField[] { LState.L_STATE.CODE, LState.L_STATE.SIGMA }, true);
        public static Index L_STATE_PRIMARY = Internal.createIndex("PRIMARY", LState.L_STATE, new OrderField[] { LState.L_STATE.KEY }, true);
        public static Index L_TENT_CODE = Internal.createIndex("CODE", LTent.L_TENT, new OrderField[] { LTent.L_TENT.CODE, LTent.L_TENT.SIGMA }, true);
        public static Index L_TENT_PRIMARY = Internal.createIndex("PRIMARY", LTent.L_TENT, new OrderField[] { LTent.L_TENT.KEY }, true);
        public static Index L_YARD_CODE = Internal.createIndex("CODE", LYard.L_YARD, new OrderField[] { LYard.L_YARD.CODE, LYard.L_YARD.SIGMA }, true);
        public static Index L_YARD_PRIMARY = Internal.createIndex("PRIMARY", LYard.L_YARD, new OrderField[] { LYard.L_YARD.KEY }, true);
    }
}
