package com.liferaysavvy.employee.rest.application.pojo;

public class Employee {

		public long   id = -1;
		public String lastName;
		public String firstName;
		public String country;
		
		public Employee() {}
		
		public Employee(long id, String first, String last, String country) {
			this.id = id;
			this.firstName = first;
			this.lastName = last;
			this.country = country;
		}	
}
