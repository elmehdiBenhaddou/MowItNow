package org.xebia.tst.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MowersBagDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private int borderX;
	 
	 private int borderY;
	 
	 @NotNull
	 @Valid
	 List<MowerDto> mowers;
	 

	public int getBorderX() {
		return borderX;
	}

	public void setBorderX(int borderX) {
		this.borderX = borderX;
	}

	public int getBorderY() {
		return borderY;
	}

	public void setBorderY(int borderY) {
		this.borderY = borderY;
	}

	public List<MowerDto> getMowers() {
		return mowers;
	}

	public void setMowers(List<MowerDto> mowers) {
		this.mowers = mowers;
	}

}
