package site.penghao.simple_ioc.ioc;

/**
 * @author hope
 * @date 2023/3/28 - 20:11
 */
public interface ApplicationContext {
    /**
     * Get bean object created by this IoC.
     * @param klass type of bean
     * @return bean
     */
    Object getBean(Class<?> klass);
}
