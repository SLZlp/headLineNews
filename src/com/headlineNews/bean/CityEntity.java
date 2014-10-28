package com.headlineNews.bean;

import java.io.Serializable;

/**
 *  城市实体
 * @author susan
 *
 */
public class CityEntity implements Serializable{
	
	//序列化的  一个常量
	private static final long serialVersionUID = 2005295701925847160L;
	/* 城市的ID */
	public Integer id;
	/* 城市的 name*/
	public String name;
	/* 城市的拼音字母 */
	public char pinyin;

	public CityEntity(Integer id, String name, char pinyin) {
		this.id = id;
		this.name = name;
		this.pinyin = pinyin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getPinyin() {
		return pinyin;
	}

	public void setPinyin(char pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public String toString() {
		return "CityEntity [id=" + id + ", name=" + name + ", pinyin=" + pinyin
				+ "]";
	}

	
	
}
