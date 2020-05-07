package com.wxwteam.bleapp.bean;

import java.io.Serializable;

public class BaseParams implements Serializable {
  protected String commond;
  
  protected String first;
  
  protected String length;
  
  protected String msgCount;
  
  public String getCommond() {
    return this.commond;
  }
  
  public String getFirst() {
    return this.first;
  }
  
  public String getLength() {
    return this.length;
  }
  
  public String getMsgCount() {
    return this.msgCount;
  }
  
  public void setCommond(String paramString) {
    this.commond = paramString;
  }
  
  public void setFirst(String paramString) {
    this.first = paramString;
  }
  
  public void setLength(String paramString) {
    this.length = paramString;
  }
  
  public void setMsgCount(String paramString) {
    this.msgCount = paramString;
  }
}
