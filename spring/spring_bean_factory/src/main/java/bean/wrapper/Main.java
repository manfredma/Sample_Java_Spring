package bean.wrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

public class Main {
    public static void main(String[] args) {
        BeanWrapper company = new BeanWrapperImpl(new Company());
        // setting the company name..
        company.setPropertyValue("name", "Some Company Inc.");
        System.out.println(company.getWrappedInstance());
        // ... can also be done like this:
        PropertyValue value = new PropertyValue("name", "Other Company Inc.");
        company.setPropertyValue(value);
        System.out.println(company.getWrappedInstance());

        // ok, let's create the director and tie it to the company:
        BeanWrapper jim = new BeanWrapperImpl(new Employee());
//        jim.setPropertyValue("name", "Jim Stravinsky");
//        jim.setPropertyValue("salary", "20.0");
        jim.setPropertyValue("age", "20.0");
        company.setPropertyValue("managingDirector", jim.getWrappedInstance());
        System.out.println(company.getWrappedInstance());

        // retrieving the salary of the managingDirector through the company
//        Float salary = (Float) company.getPropertyValue("managingDirector.salary");
        System.out.println(jim.getWrappedInstance());
//        System.out.println(salary);
    }
}
