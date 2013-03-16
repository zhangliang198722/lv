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
@Table(name="tbl_breed")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Breed extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable = false)
	private int id;//主键
	
	@Column(name="heatCheckId",length=40)
	private String heatCheckId;//情检id
	
	@Column(name="maleDonkeyId",length=40)
	private String maleDonkeyId;//公驴id
	
	@Column(name="femaleDonkeyId",length=40)
	private String femaleDonkeyId;//母驴id
	
	@Column(name="maleDonkeyCoat",length=40)
	private String maleDonkeyCoat;//公驴毛色
	
	@Column(name="femaleDonkeyCoat",length=40)
	private String femaleDonkeyCoat;//母驴毛色
	
	@Column(name="breedDate",length=40)
	private String breedDate;//配种日期
	
	@Column(name="weather",length=40)
	private String weather;//天气
	
	@Column(name="semenQuality",length=40)
	private String semenQuality;//精液质量
	
	@Column(name="isOvulate",length=40)
	private String isOvulate;//是否排卵
	
	@Column(name="operator",length=40)
	private String operator;//输精员
	
	
	@Column(name="remark",length=200)
	private String remark;//备注


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getHeatCheckId() {
		return heatCheckId;
	}


	public void setHeatCheckId(String heatCheckId) {
		this.heatCheckId = heatCheckId;
	}


	public String getMaleDonkeyId() {
		return maleDonkeyId;
	}


	public void setMaleDonkeyId(String maleDonkeyId) {
		this.maleDonkeyId = maleDonkeyId;
	}


	public String getFemaleDonkeyId() {
		return femaleDonkeyId;
	}


	public void setFemaleDonkeyId(String femaleDonkeyId) {
		this.femaleDonkeyId = femaleDonkeyId;
	}


	public String getMaleDonkeyCoat() {
		return maleDonkeyCoat;
	}


	public void setMaleDonkeyCoat(String maleDonkeyCoat) {
		this.maleDonkeyCoat = maleDonkeyCoat;
	}


	public String getFemaleDonkeyCoat() {
		return femaleDonkeyCoat;
	}


	public void setFemaleDonkeyCoat(String femaleDonkeyCoat) {
		this.femaleDonkeyCoat = femaleDonkeyCoat;
	}


	public String getBreedDate() {
		return breedDate;
	}


	public void setBreedDate(String breedDate) {
		this.breedDate = breedDate;
	}


	public String getWeather() {
		return weather;
	}


	public void setWeather(String weather) {
		this.weather = weather;
	}


	public String getSemenQuality() {
		return semenQuality;
	}


	public void setSemenQuality(String semenQuality) {
		this.semenQuality = semenQuality;
	}


	public String getIsOvulate() {
		return isOvulate;
	}


	public void setIsOvulate(String isOvulate) {
		this.isOvulate = isOvulate;
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
