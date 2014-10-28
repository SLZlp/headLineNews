package com.headlineNews.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Channel implements Parcelable{

	/** 
	 * 频道对应ID
	 *  */
	public Integer CId;
	/** 
	 * 频道对应NAME
	 *  */
	public String CName;
	/** 
	 * 频道在整体中的排序顺序  rank
	 *  */
	public Integer COrderId;
	/** 
	 * 频道是否选中
	 *  */
	public Integer CSelected;
	
	public static final Parcelable.Creator<Channel> CREATOR = new Creator<Channel>() {
		
		@Override
		public Channel[] newArray(int size) {
			return new Channel[size];
		}
		
		@Override
		public Channel createFromParcel(Parcel source) {
			Channel channel = new Channel();
			channel.CId = source.readInt();
			channel.CName = source.readString();
			channel.COrderId = source.readInt();
			channel.CSelected = source.readInt();
			return channel;
		}
	};
	
	/**
	 * 无参构造
	 */
	public Channel() {
		
	}
	/**
	 * 有参构造
	 * @param id 频道对应ID
	 * @param name 频道对应NAME
	 * @param orderId 频道在整体中的排序顺序
	 * @param selected 频道是否选中
	 */
	public Channel(Integer CId, String CName, Integer COrderId, Integer CSelected) {
		super();
		this.CId = CId;
		this.CName = CName;
		this.COrderId = COrderId;
		this.CSelected = CSelected;
	}
	
	@Override
	public String toString() {
		return "Channel [CId=" + CId + ", CName=" + CName + ", COrderId="
				+ COrderId + ", CSelected=" + CSelected + "]";
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(CId);
		parcel.writeString(CName);
		parcel.writeInt(COrderId);
		parcel.writeInt(CSelected);
	}
	
	
	
}
