package cn.sxt.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sf;
    
    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sf = cfg.buildSessionFactory();
        } catch (ExceptionInInitializerError er) {
            // TODO Auto-generated catch block
            throw new ExceptionInInitializerError("初始化sessionFactory失败，请检查配置文件");
        }
    }
    
    public static Session openSession() {
        return sf.openSession();
//        return sf.getCurrentSession();
    }
    
    //此方法必须在配置文件中绑定线程，否则返回值为null
    public static Session getCurrentSession() {
        return sf.getCurrentSession();
    }
}
