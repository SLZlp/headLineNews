package com.headlineNews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 要往手机自带的 数据库里面  写数据 （存放频道）通过 SQLHelper extends SQLiteOpenHelper 创建一张名为"channel"的数据库表 
 * @author susan
 */
public class SQLHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "news.db";// 数据库名称
	public static final int VERSION = 1;//版本号
	
	public static final String TABLE_CHANNEL = "channel";//数据库表

	public static final String ID = "CId";//
	public static final String NAME = "CName";
	public static final String ORDERID = "COrderId";
	public static final String SELECTED = "CSelected";
	private Context context;
	
	public SQLHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		this.context = context;
	}

	public Context getContext(){
		return context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 创建数据库后，对数据库的操作
		String sql = "create table if not exists "+TABLE_CHANNEL +
				"(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				ID + " INTEGER , " +
				NAME + " TEXT , " +
				ORDERID + " INTEGER , " +
				SELECTED + " SELECTED)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 更改数据库版本
		onCreate(db);
	}

}
