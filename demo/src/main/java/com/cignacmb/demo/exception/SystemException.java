package com.cignacmb.demo.exception;


/**
 * 
 * @类名： SystemException.java 
 * @描述：系统异常类，需要抛系统异常（与业务无关的异常）的地方用此类的构造方法封装数据后，直接抛出
 * @作者： mxyanx
 * @修改日期： 2014年6月24日
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 6262831660365806229L;

	private int errorCode;
	
	private Object errorMsg;
	
	public SystemException(int errorCode,Object errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public SystemException(int errorCode){
		this.errorCode = errorCode;
	}
	
	public SystemException(Object errorMsg){
		this.errorMsg = errorMsg;
	}
	
	public SystemException(){
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Object getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Object errorMsg) {
		this.errorMsg = errorMsg;
	}
}
