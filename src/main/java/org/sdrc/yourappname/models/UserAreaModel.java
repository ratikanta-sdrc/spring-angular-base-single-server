package org.sdrc.yourappname.models;


import java.util.List;

import org.sdrc.utils.ValueObject;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public class UserAreaModel {

	private Integer userAreaMappingId;
	private ValueObject areaModel;
	
	private List<UserRoleFeaturePermissionMappingModel> userRoleFeaturePermissionMappings;
	
	public Integer getUserAreaMappingId() {
		return userAreaMappingId;
	}
	public ValueObject getAreaModel() {
		return areaModel;
	}
	public void setUserAreaMappingId(Integer userAreaMappingId) {
		this.userAreaMappingId = userAreaMappingId;
	}

	public void setAreaModel(ValueObject areaModel) {
		this.areaModel = areaModel;
	}
	public List<UserRoleFeaturePermissionMappingModel> getUserRoleFeaturePermissionMappings() {
		return userRoleFeaturePermissionMappings;
	}
	public void setUserRoleFeaturePermissionMappings(
			List<UserRoleFeaturePermissionMappingModel> userRoleFeaturePermissionMappings) {
		this.userRoleFeaturePermissionMappings = userRoleFeaturePermissionMappings;
	}
	
}
