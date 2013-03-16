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
@Table(name="tbl_pregnancy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pregnancy extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable = false)
	private int id;//主键
	
	@Column(name="breedId",length=40)
	private String breedId;//配种id
	
	@Column(name="donkeyNum",length=40)
	private String donkeyNum;//孕检母驴号
	
	@Column(name="pregnancyDate",length=40)
	private String pregnancyDate;//孕检日期
	
	@Column(name="isGestation",length=40)
	private String isGestation;//妊娠与否
	
	@Column(name="reTest",length=40)
	private String reTest;//再次孕检日期
	
	@Column(name="operator",length=40)
	private String operator;//孕检人员
	
	@Column(name="remark",length=200)
	private String remark;//备注

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBreedId() {
		return breedId;
	}

	public void setBreedId(String breedId) {
		this.breedId = breedId;
	}
	
	public String getDonkeyNum() {
		return donkeyNum;
	}
	public void setDonkeyNum(String donkeyNum) {
		this.donkeyNum = donkeyNum;
	}

	public String getPregnancyDate() {
		return pregnancyDate;
	}

	public void setPregnancyDate(String pregnancyDate) {
		this.pregnancyDate = pregnancyDate;
	}

	public String getIsGestation() {
		return isGestation;
	}

	public void setIsGestation(String isGestation) {
		this.isGestation = isGestation;
	}

	public String getReTest() {
		return reTest;
	}

	public void setReTest(String reTest) {
		this.reTest = reTest;
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
