package com.xiangtai.biz.domain.query;

import com.xiangtai.biz.domain.SysDictionary;

/**
 * 
 * @author ZhangDe
 * @date 2015年9月26日下午1:31:18
 */
public class SysDictionaryQuery extends SysDictionary {
	private static final long serialVersionUID = 4587505733381942426L;

	/**
	 * @fields dicNameLike 字典名称模糊查询
	 */
	private String dicNameLike;
	/**
	 * @fields dicGroupLike 字段组模糊查询
	 */
	private String dicGroupLike;

	public String getDicGroupLike() {
		return dicGroupLike;
	}

	public void setDicGroupLike(String dicGroupLike) {
		this.dicGroupLike = dicGroupLike;
	}

	public String getDicNameLike() {
		return dicNameLike;
	}

	public void setDicNameLike(String dicNameLike) {
		this.dicNameLike = dicNameLike;
	}

}
