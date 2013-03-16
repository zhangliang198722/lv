package cn.lv.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javassist.SerialVersionUID;

import com.smart.bean.AbstractModel;
@Entity
@Table(name="tbl_donkey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Donkey extends AbstractModel {
	
	
	private static final long serialVersionUID = 5954061636440879238L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable = false)
	private int id;//主键
	
	@Column(name="donkeyNum")
	private String donkeyNum;//驴驹号
	
	@Column(name="sex")
	private String sex;//性别
	
	@Column(name="birth")
	private String birth;//出生日期
	
	@Column(name="house")
	private String house;//驴圈
	
	@Column(name="monterId")
	private String monterId;//母亲驴id
	
	@Column(name="fatherId")
	private String fatherId;//父亲驴id
	
	@Column(name="coat")
	private String coat;//毛色
	
	@Column(name="deliverId")
	private String deliverId;//接产人id
	
	@Column(name="death")
	private String death;//死亡日期
	
	@Column(name="deathCause")
	private String deathCause;//死亡原因
	
	@Column(name="remark")
	private String remark;//备注

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDonkeyNum() {
		return donkeyNum;
	}

	public void setDonkeyNum(String donkeyNum) {
		this.donkeyNum = donkeyNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getMonterId() {
		return monterId;
	}

	public void setMonterId(String monterId) {
		this.monterId = monterId;
	}

	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public String getCoat() {
		return coat;
	}

	public void setCoat(String coat) {
		this.coat = coat;
	}

	public String getDeliverId() {
		return deliverId;
	}

	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}

	public String getDeath() {
		return death;
	}

	public void setDeath(String death) {
		this.death = death;
	}

	public String getDeathCause() {
		return deathCause;
	}

	public void setDeathCause(String deathCause) {
		this.deathCause = deathCause;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
