package com.cignacmb.demo.exception;

/**
 * 
 * @类名： BusinessException.java 
 * @描述：业务异常封装类，需要抛业务异常的地方用此类的构造方法封装数据后，直接抛出
 * @作者： mxyanx
 * @修改日期： 2014年6月24日
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -1114234843075811490L;

	private int errorCode;
	
	private String errorMsg;
	
	public BusinessException(int errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public BusinessException(int errorCode){
		this.errorCode = errorCode;
	}
	
	public BusinessException(String errorMsg){
		this.errorMsg = errorMsg;
	}
	
	public BusinessException(){
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	

}
