package com.ssafy.attraction.dto;

public class GugunDto {
	
	private String gugun_code;
	private String gugun_name;
	private String sido_code;
	
	public String getGugun_code() {
		return gugun_code;
	}
	public void setGugun_code(String gugun_code) {
		this.gugun_code = gugun_code;
	}
	public String getGugun_name() {
		return gugun_name;
	}
	public void setGugun_name(String gugun_name) {
		this.gugun_name = gugun_name;
	}
	public String getSido_code() {
		return sido_code;
	}
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	
	@Override
	public String toString() {
		return "GugunDto [gugun_code=" + gugun_code + ", gugun_name=" + gugun_name + ", sido_code=" + sido_code + "]";
	}

}
