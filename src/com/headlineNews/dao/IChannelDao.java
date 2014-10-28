package com.headlineNews.dao;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;

import com.headlineNews.bean.Channel;

/**
 * 对频道进行操作的接口
 * @author susan
 *
 */
public interface IChannelDao {
	/**添加把频道添加到数据库*/
	public boolean addCache(Channel item);

	/**删除数据库*/
	public boolean deleteCache(String whereClause, String[] whereArgs);
	/**更新数据库*/
	public boolean updateCache(ContentValues values, String whereClause,
			String[] whereArgs);
	/**返回数据库内容*/
	public Map<String, String> viewCache(String selection,
			String[] selectionArgs);
	/**返回数据库内容集合*/
	public List<Map<String, String>> listCache(String selection,
			String[] selectionArgs);
	
	/**删除表**/
	public void clearFeedTable();
}
