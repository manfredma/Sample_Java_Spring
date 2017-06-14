package hibernate.type;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

public class Java8ZoneDateTimeContributor implements TypeContributor {
    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        // register the Java type descriptors
//        JavaTypeDescriptorRegistry.INSTANCE.addDescriptor(InstantJavaDescriptor.INSTANCE);

        // register the Hibernate type mappings
        typeContributions.contributeType(InstantType.INSTANCE);
    }
}