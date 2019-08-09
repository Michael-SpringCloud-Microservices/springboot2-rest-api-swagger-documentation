/**
 * 
 */
package com.michael.springboot2.rest.swagger.documentation.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael Philomin Raj 
 *
 */
@ApiModel(value="Student DTO" ,description="POJO class to contain student details")
public class Student {
	@ApiModelProperty(notes = "Name of the Student" , required=true)
	private String name;

	@ApiModelProperty(notes = "Class of the Student" , required=true)
	private String cls;

	@ApiModelProperty(notes = "Country of the Student", required=true, value="test country") // value attributes overrides notes
	private String country;

	public Student(String name, String cls, String country) {
		super();
		this.name = name;
		this.cls = cls;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public String getCls() {
		return cls;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", cls=" + cls + ", country=" + country + "]";
	}
}
