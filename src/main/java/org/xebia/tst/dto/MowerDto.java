package org.xebia.tst.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.xebia.tst.bo.Orientation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MowerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  int x;
	
	private  int y;
	
	private Orientation orientation;
	

	@NotNull
	@Pattern(regexp = "(G|D|A)*")
	private String orders;

	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

}
