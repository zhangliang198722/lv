package cn.lv.bean;


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
@Table(name="tbl_sire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sire extends AbstractModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable = false)
	private int id;//主键
	
	@Column(name="breedId")
	private String breedId;//配种id
	
	@Column(name="produceDate")
	private String produceDate;//产驴日期
	
	@Column(name="sex")
	private String sex;//性别
	
	@Column(name="miscarry")
	private String miscarry;//流产日期
	
	@Column(name="deathDate")
	private String deathDate;//死亡日期
	
	@Column(name="deliverId")
	private String deliverId;//接产人id
	
	@Column(name="remark")
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

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMiscarry() {
		return miscarry;
	}

	public void setMiscarry(String miscarry) {
		this.miscarry = miscarry;
	}

	public String getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public String getDeliverId() {
		return deliverId;
	}

	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
