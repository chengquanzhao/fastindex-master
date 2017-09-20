package com.davidzhao.fastindexing;

public class DataBean implements Comparable<DataBean> {
	private String name;
	private String pinyin;

	public DataBean(String name) {
		super();
		this.name = name;
		
		//一开始就转化好拼音
		setPinyin(PinYinUtil.getPinyin(name));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(DataBean another) {
		return getPinyin().compareTo(another.getPinyin());
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	
}
