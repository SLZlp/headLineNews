package com.headlineNews.bean;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable{

	/** 新闻类别 ID */
	private Integer newsCategoryId;
	/** 新闻类型 */
	private String newsCategory;
	/** 标记状态，如推荐之类的 */
	private Integer mark;
	/** 评论数量 */
	private Integer commentNum;
	/** ID */
	private Integer id;
	/** 新闻ID */
	private Integer newsId;
	/** 标题 */
	private String title;
	/** 新闻源 */
	private String source;
	/** 新闻源地址 URL */
	private String source_url;
	/** 发布时间 */
	private Long publishTime;
	/** 总结 */
	private String summary;
	/** 摘要 */
	private String newsAbstract;
	/** 评论 */
	private String comment;
	/** 特殊标签，如广告推广之类的 ，可以为空 */
	private String local;
	/** 图片列表字符串 */
	private String picListString;
	/** 图片1 URL */
	private String picOne;
	/** 图片2 URL */
	private String picTwo;
	/** 图片3 URL */
	private String picThr;
	/** 图片 列表 */
	private List<String> picList;
	/** 图片类型是否为大图 大图：1， 小图：0 */
	private int isLarge;
	/** 阅读状态 ，读过的话显示灰色背景  度过：1， 未读：0 */
	private int readStatus;
	/** 收藏状态  是：1， 否：0*/
	private int collectStatus;
	/** 喜欢 状态   是：1， 否：0*/
	private int likeStatus;
	/** 感兴趣状态   是：1， 否：0*/
	private int interestedStatus;
	
	
	public Integer getNewsCategoryId() {
		return newsCategoryId;
	}
	public void setNewsCategoryId(Integer newsCategoryId) {
		this.newsCategoryId = newsCategoryId;
	}
	public String getNewsCategory() {
		return newsCategory;
	}
	public void setNewsCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSource_url() {
		return source_url;
	}
	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}
	public Long getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getNewsAbstract() {
		return newsAbstract;
	}
	public void setNewsAbstract(String newsAbstract) {
		this.newsAbstract = newsAbstract;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getPicListString() {
		return picListString;
	}
	public void setPicListString(String picListString) {
		this.picListString = picListString;
	}
	public String getPicOne() {
		return picOne;
	}
	public void setPicOne(String picOne) {
		this.picOne = picOne;
	}
	public String getPicTwo() {
		return picTwo;
	}
	public void setPicTwo(String picTwo) {
		this.picTwo = picTwo;
	}
	public String getPicThr() {
		return picThr;
	}
	public void setPicThr(String picThr) {
		this.picThr = picThr;
	}
	public List<String> getPicList() {
		return picList;
	}
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}
	public int getIsLarge() {
		return isLarge;
	}
	public void setIsLarge(int isLarge) {
		this.isLarge = isLarge;
	}
	public int getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}
	public int getCollectStatus() {
		return collectStatus;
	}
	public void setCollectStatus(int collectStatus) {
		this.collectStatus = collectStatus;
	}
	public int getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(int likeStatus) {
		this.likeStatus = likeStatus;
	}
	public int getInterestedStatus() {
		return interestedStatus;
	}
	public void setInterestedStatus(int interestedStatus) {
		this.interestedStatus = interestedStatus;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(newsCategoryId);
		parcel.writeString(newsCategory);
		parcel.writeInt(mark);
		parcel.writeInt(commentNum);
		parcel.writeInt(id);
		parcel.writeInt(newsId);
		parcel.writeString(title);
		parcel.writeString(source);
		parcel.writeString(source_url);
		parcel.writeLong(publishTime);
		parcel.writeString(summary);
		parcel.writeString(newsAbstract);
		parcel.writeString(comment);
		parcel.writeString(local);
		parcel.writeString(picListString);
		parcel.writeString(picOne);
		parcel.writeString(picTwo);
		parcel.writeString(picThr);
		parcel.writeList(picList);
		parcel.writeInt(isLarge);
		parcel.writeInt(readStatus);
		parcel.writeInt(collectStatus);
		parcel.writeInt(likeStatus);
		parcel.writeInt(interestedStatus);
	}
	
	public static final Parcelable.Creator<News> CREATOR = new Creator<News>() {
		
		@Override
		public News[] newArray(int size) {
			return new News[size];
		}
		
		@Override
		public News createFromParcel(Parcel source) {
			News news = new News();
			news.newsCategoryId = source.readInt();
			news.newsCategory = source.readString();
			news.mark = source.readInt();
			news.commentNum = source.readInt();
			news.id = source.readInt();
			news.newsId = source.readInt();
			news.title = source.readString();
			news.source = source.readString();
			news.source_url = source.readString();
			news.publishTime = source.readLong();
			news.summary = source.readString();
			news.newsAbstract = source.readString();
			news.comment = source.readString();
			news.local = source.readString();
			news.picListString = source.readString();
			news.picOne = source.readString();
			news.picTwo = source.readString();
			news.picThr = source.readString();
			ClassLoader loader = News.class.getClassLoader();
			source.readList(news.picList, loader);
			news.isLarge = source.readInt();
			news.readStatus = source.readInt();
			news.collectStatus = source.readInt();
			news.likeStatus = source.readInt();
			news.interestedStatus = source.readInt();
			return news;
		}
	};
	
}
