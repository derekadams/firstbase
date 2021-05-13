package com.firstbase.codetest.model.dto;

import javax.validation.constraints.NotNull;

import com.firstbase.codetest.model.Employee;

/**
 * Holds data required to update an {@link Employee}. In this case, it's the
 * same as the create request, but this model could limit the fields that may be
 * updated by only listing the updatable fields.
 */
public class EmployeeUpdateRequest {

    /** Employee first name */
    private String firstName;

    /** Employee last name */
    private String lastName;

    /** Employee job title */
    @NotNull
    private String jobTitle;

    /** URL pointing to employee picture */
    private String pictureUrl;

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getJobTitle() {
	return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
    }

    public String getPictureUrl() {
	return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
	this.pictureUrl = pictureUrl;
    }
}
