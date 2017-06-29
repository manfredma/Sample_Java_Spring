package bean.wrapper;

import org.springframework.beans.propertyeditors.CustomNumberEditor;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.SimpleBeanInfo;
import java.math.BigDecimal;

public class EmployeeBeanInfo extends SimpleBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            final PropertyEditor numberPE = new CustomNumberEditor(BigDecimal.class, false);
            PropertyDescriptor ageDescriptor = new PropertyDescriptor("age", Employee.class) {
                public PropertyEditor createPropertyEditor(Object bean) {
                    return numberPE;
                }
            };
            return new PropertyDescriptor[]{ageDescriptor};
        } catch (IntrospectionException ex) {
            throw new Error(ex.toString());
        }
    }
}