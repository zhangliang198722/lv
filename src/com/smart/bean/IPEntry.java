/**
 * 
 */
package com.smart.bean;


/**
 * @Project: www.ishowchina.com
 * @author Zhang Xiao Dong
 * @Date: 2012-3-23
 * 
 */
public class IPEntry extends  AbstractModel{

	private static final long serialVersionUID = -2845886899294957424L;
	public String	beginIp;
	public String	endIp;
	public String	country;
	public String	area;

	/**
	 * 构造函数
	 */

	public IPEntry() {
		beginIp = endIp = country = area = "";
	}

	@Override
	public String toString() {
		return this.area + "  " + this.country + "IP范围:" + this.beginIp + "-" + this.endIp;
	}
}