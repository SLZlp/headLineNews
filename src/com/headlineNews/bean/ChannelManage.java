package com.headlineNews.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.database.SQLException;
import android.util.Log;

import com.headlineNews.dao.ChannelDao;
import com.headlineNews.db.SQLHelper;

public class ChannelManage {
	
	//把Channel 写成静态的  我们就不用在new了
	public static ChannelManage channelManage;
	/**
	 * 默认的用户选择频道列表
	 * */
	public static List<Channel> defaultUserChannels;
	/**
	 * 默认的其他频道列表
	 * */
	public static List<Channel> defaultOtherChannels;
	private ChannelDao channelDao;
	/** 判断数据库中是否存在用户数据 */
	private boolean userExist = false;
	
	static {
		defaultUserChannels = new ArrayList<Channel>();
		defaultOtherChannels = new ArrayList<Channel>();
		defaultUserChannels.add(new Channel(1, "推荐", 1, 1));
		defaultUserChannels.add(new Channel(2, "热点", 2, 1));
		defaultUserChannels.add(new Channel(3, "杭州", 3, 1));
		defaultUserChannels.add(new Channel(4, "时尚", 4, 1));
		defaultUserChannels.add(new Channel(5, "科技", 5, 1));
		defaultUserChannels.add(new Channel(6, "体育", 6, 1));
		defaultUserChannels.add(new Channel(7, "军事", 7, 1));
		defaultOtherChannels.add(new Channel(8, "财经", 1, 0));
		defaultOtherChannels.add(new Channel(9, "汽车", 2, 0));
		defaultOtherChannels.add(new Channel(10, "房产", 3, 0));
		defaultOtherChannels.add(new Channel(11, "社会", 4, 0));
		defaultOtherChannels.add(new Channel(12, "情感", 5, 0));
		defaultOtherChannels.add(new Channel(13, "女人", 6, 0));
		defaultOtherChannels.add(new Channel(14, "旅游", 7, 0));
		defaultOtherChannels.add(new Channel(15, "健康", 8, 0));
		defaultOtherChannels.add(new Channel(16, "美女", 9, 0));
		defaultOtherChannels.add(new Channel(17, "游戏", 10, 0));
		defaultOtherChannels.add(new Channel(18, "数码", 11, 0));
		defaultUserChannels.add(new Channel(19, "娱乐", 12, 0));
	}

	/**
	 * 有参构造 是个无返回值的
	 */
	private ChannelManage(SQLHelper paramDBHelper) throws SQLException {
		//当channelDao 为空时 我就 让它new出来
		if (channelDao == null)
			channelDao = new ChannelDao(paramDBHelper.getContext());
		// NavigateItemDao(paramDBHelper.getDao(NavigateItem.class))
		
		return;
	}

	/**
	 * 初始化频道管理类
	 * @param paramDBHelper
	 * @throws SQLException
	 */
	public static ChannelManage getManage(SQLHelper dbHelper)throws SQLException {
		if (channelManage == null)
			channelManage = new ChannelManage(dbHelper);
		return channelManage;
	}

	/**
	 * 清除所有的频道
	 */
	public void deleteAllChannel() {
		channelDao.clearFeedTable();
	}
	/**
	 * 获取默认的频道
	 * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
	 */
	public List<Channel> getUserChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?",new String[] { "1" });
		if (cacheList != null && !((List) cacheList).isEmpty()) {
			userExist = true;
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			List<Channel> list = new ArrayList<Channel>();
			for (int i = 0; i < count; i++) {
				Channel navigate = new Channel(); //navigate 导航
				navigate.CId = Integer.valueOf(maplist.get(i).get(SQLHelper.ID));
				navigate.CName = maplist.get(i).get(SQLHelper.NAME);
				navigate.COrderId = Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID));
				navigate.CSelected = Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED));
				list.add(navigate);
			}
			return list;
		}
		initDefaultChannel();
		return defaultUserChannels;
	}
	
	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
	 */
	public List<Channel> getOtherChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?" ,new String[] { "0" });
		List<Channel> list = new ArrayList<Channel>();
		if (cacheList != null && !((List) cacheList).isEmpty()){
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			for (int i = 0; i < count; i++) {
				Channel navigate= new Channel();
				navigate.CId = Integer.parseInt(maplist.get(i).get(SQLHelper.ID));
				navigate.CName = maplist.get(i).get(SQLHelper.NAME);
				navigate.COrderId = Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID));
				navigate.CSelected = Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED));
				list.add(navigate);
			}
			return list;
		}
		if(userExist){
			return list;
		}
		cacheList = defaultOtherChannels;
		return (List<Channel>) cacheList;
	}
	
	/**
	 * 保存用户频道到数据库
	 * @param userList
	 */
	public void saveUserChannel(List<Channel> userList) {
		for (int i = 0; i < userList.size(); i++) {
			Channel Channel = (Channel) userList.get(i);
			Channel.COrderId = i;
			Channel.CSelected = Integer.valueOf(1);
			channelDao.addCache(Channel);
		}
	}
	
	/**
	 * 保存其他频道到数据库
	 * @param otherList
	 */
	public void saveOtherChannel(List<Channel> otherList) {
		for (int i = 0; i < otherList.size(); i++) {
			Channel Channel = (Channel) otherList.get(i);
			Channel.CId = i;
			Channel.CSelected = 0;
			channelDao.addCache(Channel);
		}
	}
	
	/**
	 * 初始化数据库内的频道数据
	 */
	private void initDefaultChannel(){
//		Log.d("deleteAll", "deleteAll");
		deleteAllChannel();
		saveUserChannel(defaultUserChannels);
		saveOtherChannel(defaultOtherChannels);
	}
}
