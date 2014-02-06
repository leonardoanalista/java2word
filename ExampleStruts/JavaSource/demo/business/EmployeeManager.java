package demo.business;

import java.util.ArrayList;
import java.util.List;

import demo.domain.*;

public class EmployeeManager {

	public static List<Employee> getResultList(){
		List<Employee> lst = new ArrayList<Employee>();
		
		lst.add(new Employee("Leonardo Correa", "40,000.00"));
		lst.add(new Employee("Romario da Silva", "500,000.00"));
		lst.add(new Employee("Ronaldinho", "850,000.00"));
		lst.add(new Employee("Kaka Ricardo de Oliveira", "1.240,000.00"));
		
		return lst;
	}
	
}
