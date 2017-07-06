package com.amarpreet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollocationModel {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String supportingPropI;
	private String componentI;
	private String posI;
	private String componentII;
	private String posII;
	private String supportingPropII;
	
	
	
	public CollocationModel() {
		super();
	}



	public CollocationModel(String supportingPropI, String componentI, String posI, String componentII, String posII,
			String supportingPropII) {
		super();
		this.supportingPropI = supportingPropI;
		this.componentI = componentI;
		this.posI = posI;
		this.componentII = componentII;
		this.posII = posII;
		this.supportingPropII = supportingPropII;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getSupportingPropI() {
		return supportingPropI;
	}



	public void setSupportingPropI(String supportingPropI) {
		this.supportingPropI = supportingPropI;
	}



	public String getComponentI() {
		return componentI;
	}



	public void setComponentI(String componentI) {
		this.componentI = componentI;
	}



	public String getPosI() {
		return posI;
	}



	public void setPosI(String posI) {
		this.posI = posI;
	}



	public String getComponentII() {
		return componentII;
	}



	public void setComponentII(String componentII) {
		this.componentII = componentII;
	}



	public String getPosII() {
		return posII;
	}



	public void setPosII(String posII) {
		this.posII = posII;
	}



	public String getSupportingPropII() {
		return supportingPropII;
	}



	public void setSupportingPropII(String supportingPropII) {
		this.supportingPropII = supportingPropII;
	}
	
	
	
	
	
	
}
