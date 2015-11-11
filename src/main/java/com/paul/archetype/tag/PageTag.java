package com.paul.archetype.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @包名   com.paul.archetype.tag
 * @文件名 PageTag.java
 * @作者   paul
 * @创建日期 2014-12-11
 * @版本 V 1.0
 */
public class PageTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String url; //链接地址
    private int curpage = 1;// 当前页
    private int pagesize = 10; // 页大小
    private int totalItems; // 总记录条数
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public int doStartTag() throws JspException {
		int pageNum = (totalItems+pagesize)/pagesize;
		StringBuffer buffer=new StringBuffer();
		buffer.append("<div><nav style='background-color: #eff3f8;text-align: center;'><ul class='pagination' style='margin: 5px'>");
		if(curpage==1){
			buffer.append("<li class='disabled'><a href='javascript:;'>«</a>");
		}else{
			buffer.append("<li><a href='javascript:;' class='ajaxDef' ajax-url='"+url+"?pageIndex="+(curpage-1)+"'>«</a>");
		}
		int startIndex=1;
		int endIndex = pageNum;
		if(pageNum>5){
			if(pageNum-curpage>=2&&curpage>3){
				startIndex=curpage-2;
				endIndex = curpage+2;
			}
			if(pageNum-curpage<2){
				startIndex=2*curpage-2-pageNum;
				endIndex = pageNum;
			}
		}
		for(int i=startIndex;i<=endIndex;i++){
			if(curpage==i){
				buffer.append("<li class='active disabled'><a href='javascript:;' class='ajaxDef' ajax-url='"+url+"?pageIndex="+i+"'>"+i+"</a></li>");
			}else{
				buffer.append("<li><a href='javascript:;' class='ajaxDef' ajax-url='"+url+"?pageIndex="+i+"'>"+i+"</a></li>");
			}
		}
		if(curpage==pageNum){
			buffer.append("<li class='disabled'><a href='javascript:;'>»</a>");
		}else{
			buffer.append("<li><a href='javascript:;'  class='ajaxDef' ajax-url='"+url+"?pageIndex="+(curpage+1)+"'>»</a>");
		}
		buffer.append("</ul></nav></div>");
		Writer writer = pageContext.getOut();
		try {
			writer.write(buffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
	}

}
