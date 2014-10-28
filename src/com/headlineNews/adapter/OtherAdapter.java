package com.headlineNews.adapter;

import java.util.List;

import com.headlineNews.R;
import com.headlineNews.bean.Channel;

import android.R.bool;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OtherAdapter extends BaseAdapter {
	private Context context;
	public List<Channel> channelList;
	private TextView item_text;
	/** 是否可见 */
	boolean isVisible = true;
	/** 要删除的position */
	public int remove_position = -1;

	public OtherAdapter(Context context, List<Channel> channelList) {
		super();
		this.context = context;
		this.channelList = channelList;
	}

	@Override
	public int getCount() {
		return channelList == null ? 0 : channelList.size();
	}

	@Override
	public Channel getItem(int position) {
		if (channelList == null || channelList.size() == 0)
			return null;
		return channelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.channel_item, null);
		item_text = (TextView) view.findViewById(R.id.text_item);
		Channel channel = channelList.get(position);
		item_text.setText(channel.CName);
		if (!isVisible || remove_position == position) {
			item_text.setText("");;
		}
		return view;
	}

	/** 获取频道列表 */
	public List<Channel> getChannelList(){
		return channelList;
	}
	/** 添加频道 */
	public void addChannel(Channel channel){
		channelList.add(channel);
		notifyDataSetChanged();
	}
	/** 设置移除频道 */
	public void setRemoveChannel(int position){
		remove_position = position;
		notifyDataSetChanged();
	}
	/** 移除频道 */
	public void removeChannel(){
		channelList.remove(remove_position);
		remove_position = -1;
		notifyDataSetChanged();
	}
	/** 设置频道 */
	public void setChannelList(List<Channel> channelList){
		this.channelList = channelList;
	}
	/** 是否可见 */
	public boolean isVisible(){
		return isVisible;
	}
	/** 设置是否可见 */
	public void setVisible(boolean flag){
		this.isVisible = flag;
	}
}
