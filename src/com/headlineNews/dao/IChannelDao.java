package com.headlineNews.dao;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;

import com.headlineNews.bean.Channel;

public interface IChannelDao {
	/**添加把频道添加到缓存*/
	public boolean addCache(Channel item);

	/**删除缓存*/
	public boolean deleteCache(String whereClause, String[] whereArgs);
	/**更新缓存*/
	public boolean updateCache(ContentValues values, String whereClause,
			String[] whereArgs);
	/**返回缓存内容*/
	public Map<String, String> viewCache(String selection,
			String[] selectionArgs);
	/**返回缓存内容集合*/
	public List<Map<String, String>> listCache(String selection,
			String[] selectionArgs);
	/***/
	public void clearFeedTable();
}
