package net.martincharlesworth.data.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//This app has been created to prove that Spring transactions are working correctly. The use of Spring JUnit support in tests seems to screw up transaction rollback
public class AspectJTestApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAspectJ.xml");

        RollbackTestBean bean = (RollbackTestBean) context.getBean("rollbackTestBean");
        bean.testRollback();
    }

}
