package programmatic.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
        for (Object bean : beansToAutowireInClass) {
            if (bean == null) {
                applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
                return;
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static CrudRepository getCrudRepositoryByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        Map<String, CrudRepository> map = applicationContext.getBeansOfType(CrudRepository.class);
        if (null != map.get(name)) {
            return map.get(name);
        }

        if (null != map.get(StringUtils.uncapitalize(name))) {
            return map.get(StringUtils.uncapitalize(name));
        }

        return map.get(StringUtils.capitalize(name));
    }

    public static <T> T getBeansByTypeAndName(Class<T> clazz, String name) {
        return applicationContext.getBeansOfType(clazz).get(name);
    }

    public static <T> Map<String, T> getBeansByType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    public static void applyBeanPropertyValues(Object object, String beanName) {
        applicationContext.getAutowireCapableBeanFactory().applyBeanPropertyValues(object, beanName);
    }

    public static void autowiredBeanPropertyValues(Object object) {
        applicationContext.getAutowireCapableBeanFactory().autowireBeanProperties(object, AutowireCapableBeanFactory
                .AUTOWIRE_BY_TYPE, false);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

}