package com.ssafy.reply.dto;

public class ReplyDto {
	
	private String reply_no;
	private String article_no;
	private String user_id;
	private String content;
	private String register_time;
	
	public String getReply_no() {
		return reply_no;
	}
	public void setReply_no(String reply_no) {
		this.reply_no = reply_no;
	}
	public String getArticle_no() {
		return article_no;
	}
	public void setArticle_no(String article_no) {
		this.article_no = article_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}
	
	@Override
	public String toString() {
		return "ReplyDto [reply_no=" + reply_no + ", article_no=" + article_no + ", user_id=" + user_id + ", content="
				+ content + ", register_time=" + register_time + "]";
	}
	
}
