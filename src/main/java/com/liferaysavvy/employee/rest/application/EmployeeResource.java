package com.liferaysavvy.employee.rest.application;

import com.liferaysavvy.employee.rest.application.pojo.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("/employee")

public class EmployeeResource {
	
	
	// Return all available list of employees
	
	@GET
	@Produces("application/json")
	public List<Employee> getAllPeople() {
		return employeeList;
	}

	// Return a single Employee based on Id
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getEmployee(@PathParam("id") String id) {
		ResponseBuilder builder;
		try {
		  Employee employee = findById(new Long(id));
		  if (employee != null) {
			  builder = Response.ok(employee);
		  } else {
			  builder = Response.status(Response.Status.NOT_FOUND).entity("Employee not found for ID: " + id);
		  }
		} catch (Exception e) {
			builder = Response.status(Response.Status.NOT_FOUND).entity("Exception occurred for ID: " + id);
			e.printStackTrace();
		}
		return builder.build();
	}
		
    // Adding new employee
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Employee addEmployee(Employee newEmployee) {
		// Generate Random Id for new employee
		newEmployee.id = new Date().getTime();
		employeeList.add(newEmployee);
		return newEmployee;
	}
	
	// Update existing Employee
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateEmployee(Employee newEmployee, @PathParam("id") String id) {
		  ResponseBuilder builder = null;
		  Employee oldEmployee = findById(new Long(id));
		  if (oldEmployee != null) {
			  oldEmployee.firstName = newEmployee.firstName;
			  oldEmployee.lastName = newEmployee.lastName;
			  oldEmployee.country = newEmployee.country;
			  builder = Response.ok(oldEmployee);
		  } else {
			  builder = Response.status(Response.Status.NOT_FOUND).entity("Employee not found for ID: " + id);
		  }
		
		return builder.build();
	}
	
   // Delete Employee based on Id
	
   @DELETE
   @Path("{id}")
   public Response deleteEmployee(@PathParam("id") String id) {
		ResponseBuilder builder =  Response.status(Response.Status.NOT_FOUND).entity("Employee not found for ID: " + id);
		long employeeId = new Long(id);
		int index = 0;
		for (Employee employee : employeeList) {
			if (employee.id == employeeId) {
				employeeList.remove(index);
				builder = Response.ok();
				break;
			} else {
				index++;
			}
		}
		return builder.build();
	}
	
	// Method to find mataching employee based on provided Id
   
	private Employee findById(long id) {
		for (Employee employee : employeeList) {
			if (employee.id == id) {
				return employee;
			}
		}
		return null;
	}
	
	// Sample data for employees
	
    private static List<Employee> employeeList = new ArrayList<Employee> ();
    static {
    	employeeList.add(new Employee(1, "Meera","Prince","NY"));
    	employeeList.add(new Employee(2, "Liferay","Savvy","NJ"));
    	employeeList.add(new Employee(3, "Liferay","DXP","MI"));
    }
}
