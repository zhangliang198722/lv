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
public class IPLocation extends  AbstractModel{

	private static final long serialVersionUID = 1999861713276336044L;
	public String country;
	public String area;
	public IPLocation() {
	    country = area = "";
	}
	public IPLocation getCopy() {
	    IPLocation ret = new IPLocation();
	    ret.country = country;
	    ret.area = area;
	    return ret;
	}
}
