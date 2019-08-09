/**
 * 
 */
package com.michael.springboot2.rest.swagger.documentation.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.michael.springboot2.rest.swagger.documentation.model.Student;
/**
 * @author Michael Philomin Raj 
 *
 */
@RestController
@Api(value="REST APIs related to Student Entity") // This will not be used after 1.5.x
public class Swagger2DemoRestController {

	List<Student> students = new ArrayList<Student>();
	{
		students.add(new Student("Sajal", "IV", "India"));
		students.add(new Student("Lokesh", "V", "India"));
		students.add(new Student("Kajal", "III", "USA"));
		students.add(new Student("Sukesh", "VI", "USA"));
	}

	@ApiOperation(value = "Get list of Students in the System ", response = Iterable.class)
	//@ApiOperation(value = "Get list of Students in the System ", response = Iterable.class, tags = "getStudents")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved students list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the students"),
			@ApiResponse(code = 403, message = "You are forbidden to access the students list"),
			@ApiResponse(code = 404, message = "The student(s) you were trying to reach is not found") })
	@GetMapping(value = "/getStudents" , produces={"application/json","application/xml","application/text"})
	public List<Student> getStudents() {
		return students;
	}

	@ApiOperation(value = "Get specific Student in the System ", response = Student.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!!!"),
			@ApiResponse(code = 404, message = "Not Found!!!") })
	@GetMapping(value = "/getStudent/{name}")
	public Student getStudent(@PathVariable(value = "name") String name) {
		return students.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
	}

	@ApiOperation(value = "Get specific Student By Country in the System ", response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!!!"),
			@ApiResponse(code = 404, message = "Not Found!!!") })
	@GetMapping(value = "/getStudentByCountry/{country}")
	public List<Student> getStudentByCountry(
			@ApiParam(name = "country", value = "Pass the country to pick the student details", required = true)
	@PathVariable(value = "country") String country) {
		System.out.println("Searching Student in country : " + country);
		List<Student> studentsByCountry = students.stream().filter(x -> x.getCountry().equalsIgnoreCase(country))
				.collect(Collectors.toList());
		System.out.println(studentsByCountry);
		return studentsByCountry;
	}

	@ApiOperation(value = "Get specific Student By Class in the System ",response = Student.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "Not Authorized!"),
			@ApiResponse(code = 403, message = "Forbidden!!!"),
			@ApiResponse(code = 404, message = "Not Found!!!") })
	@GetMapping(value = "/getStudentByClass/{cls}")
	public List<Student> getStudentByClass(
			@ApiParam(name = "class", value = "Pass the class to pick the student details", required = true)
			@PathVariable(value = "cls") String cls) {
		return students.stream().filter(x -> x.getCls().equalsIgnoreCase(cls)).collect(Collectors.toList());
	}
}
