package cn.lv.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smart.bean.AbstractModel;
@Entity
@Table(name="tbl_heatcheck")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Heatcheck extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable = false)
	private int id;//id
	
	@Column(name="donkeyId",length=40)
	private String donkeyId;//驴驹id
	
	@Column(name="subMother",length=10)
	private String subMother;//是否是子母圈
	
	@Column(name="checkDate",length=40)
	private String checkDate;//情检日期
	
	@Column(name="follicle",length=100)
	private String follicle;//卵泡发育度
	
	@Column(name="canBreed",length=10)
	private String canBreed;//是否可配
	
	@Column(name="drugUse",length=100)
	private String drugUse;//用药情况
	
	@Column(name="reCheckDate",length=40)
	private String reCheckDate;//再检日期
	
	@Column(name="operator",length=40)
	private String operator;//情检人员
	
	@Column(name="remark",length=200)
	private String remark;//备注

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDonkeyId() {
		return donkeyId;
	}

	public void setDonkeyId(String donkeyId) {
		this.donkeyId = donkeyId;
	}

	public String getSubMother() {
		return subMother;
	}

	public void setSubMother(String subMother) {
		this.subMother = subMother;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getFollicle() {
		return follicle;
	}

	public void setFollicle(String follicle) {
		this.follicle = follicle;
	}

	public String getCanBreed() {
		return canBreed;
	}

	public void setCanBreed(String canBreed) {
		this.canBreed = canBreed;
	}

	public String getDrugUse() {
		return drugUse;
	}

	public void setDrugUse(String drugUse) {
		this.drugUse = drugUse;
	}

	public String getReCheckDate() {
		return reCheckDate;
	}

	public void setReCheckDate(String reCheckDate) {
		this.reCheckDate = reCheckDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
