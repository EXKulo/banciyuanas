package com.exkulo.banciyuan.ui.data.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.exkulo.banciyuan.ui.model.PostDetail;
import com.exkulo.banciyuan.ui.utils.ContextAccessor;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 * Created by exkulo on 10/4/2015.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String TB_NAME = "default_db";

    private static final Context mContext = ContextAccessor.getContextAccessor().getContext();

    private Dao<PostDetail, String> mPostDao;

    private DataBaseHelper() {
        super(mContext, TB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, PostDetail.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
        int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, PostDetail.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DataBaseHelper instance;

    /**
     * 单例获取该Helper
     */
    public static synchronized DataBaseHelper getHelper() {
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null) instance = new DataBaseHelper();
            }
        }
        return instance;
    }

    /**
     * 获得userDao
     *
     * @throws SQLException
     */
    public Dao<PostDetail, String> getUserDao() throws SQLException {
        if (mPostDao == null) {
            mPostDao = getDao(PostDetail.class);
        }
        return mPostDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        mPostDao = null;
    }
}
