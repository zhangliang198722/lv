package com.smart.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import net.sf.json.JSONObject;

import com.smart.service.ICommonService;
import com.smart.util.JsonUtil;
import com.smart.util.SpringContextUtil;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @Title: 存储实体类公共的属�?便于子类继承
 * @Description: 实现TODO
 * @author Zhang Xiao Dong
 * @Date: 2012-4-12
 * @version 1.0
 */
public class AbstractModel implements Serializable {
	private static final long serialVersionUID = 2035013017939483936L;
	
	@Column(name = "cdate", nullable = false)
	public Date createTime;
	@Column(name = "mdate", nullable = false)
	public Date modifyTime;
	@Column(name = "cuser", nullable = false)
	public String createUser;
	@Column(name = "muser", nullable = false)
	public String modifyUser;

	public AbstractModel() {
		this.createTime = new Date();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public void save() {
		ICommonService commonService = SpringContextUtil
				.getBean("CommonService");
		commonService.save(this);
	}

	public void delete() {
		ICommonService commonService = SpringContextUtil
				.getBean("CommonService");
		commonService.deleteObject(this);
	}

	public void update() {
		ICommonService commonService = SpringContextUtil
				.getBean("CommonService");
		commonService.update(this);
	}

	public JSONObject toJSON() {
		return JSONObject.fromObject(this);
	}

	public String toJSONString() {
		return JsonUtil.bean2json(this);
	}
}
