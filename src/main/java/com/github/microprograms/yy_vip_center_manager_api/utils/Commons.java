package com.github.microprograms.yy_vip_center_manager_api.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.github.microprograms.ignite_utils.IgniteUtils;
import com.github.microprograms.ignite_utils.sql.dml.FieldToUpdate;
import com.github.microprograms.ignite_utils.sql.dml.InsertSql;
import com.github.microprograms.ignite_utils.sql.dml.PagerRequest;
import com.github.microprograms.ignite_utils.sql.dml.SelectCountSql;
import com.github.microprograms.ignite_utils.sql.dml.SelectSql;
import com.github.microprograms.ignite_utils.sql.dml.Sort;
import com.github.microprograms.ignite_utils.sql.dml.UpdateSql;

public class Commons {

    public static <T> T queryObject(Class<T> clz, Object where) throws SQLException {
        try (Connection conn = IgniteUtils.getConnection(Consts.jdbc_url)) {
            ResultSet selectRs = conn.createStatement().executeQuery(new SelectSql(clz).where(where).build());
            return IgniteUtils.getJavaObject(selectRs, clz);
        }
    }

    public static <T> int queryCount(Class<T> clz, Object where) throws SQLException {
        try (Connection conn = IgniteUtils.getConnection(Consts.jdbc_url)) {
            ResultSet selectRs = conn.createStatement().executeQuery(new SelectCountSql(clz).where(where).build());
            return IgniteUtils.getCount(selectRs);
        }
    }

    public static <T> List<T> queryAllObject(Class<T> clz, Object where) throws SQLException {
        try (Connection conn = IgniteUtils.getConnection(Consts.jdbc_url)) {
            ResultSet selectRs = conn.createStatement().executeQuery(new SelectSql(clz).where(where).build());
            return IgniteUtils.getJavaObjectList(selectRs, clz);
        }
    }

    public static <T> List<T> queryAllObject(Class<T> clz, Object where, List<Sort> sorts) throws SQLException {
        try (Connection conn = IgniteUtils.getConnection(Consts.jdbc_url)) {
            ResultSet selectRs = conn.createStatement().executeQuery(new SelectSql(clz).where(where).sorts(sorts).build());
            return IgniteUtils.getJavaObjectList(selectRs, clz);
        }
    }

    public static <T> List<T> queryAllObject(Class<T> clz, Object where, List<Sort> sorts, PagerRequest pager) throws SQLException {
        try (Connection conn = IgniteUtils.getConnection(Consts.jdbc_url)) {
            ResultSet selectRs = conn.createStatement().executeQuery(new SelectSql(clz).where(where).sorts(sorts).pager(pager).build());
            return IgniteUtils.getJavaObjectList(selectRs, clz);
        }
    }

    public static <T> int updateFieldsForObject(Class<T> clz, List<FieldToUpdate> fields, Object where) throws SQLException {
        try (Connection conn = IgniteUtils.getConnection(Consts.jdbc_url)) {
            return conn.createStatement().executeUpdate(new UpdateSql(clz).fields(fields).where(where).build());
        }
    }

    public static int insertObject(Object object) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try (Connection conn = IgniteUtils.getConnection(Consts.jdbc_url)) {
            return conn.createStatement().executeUpdate(InsertSql.build(object));
        }
    }
}
