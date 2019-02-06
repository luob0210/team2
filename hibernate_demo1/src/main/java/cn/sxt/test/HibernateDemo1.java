package cn.sxt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.sxt.vo.Custmer;

public class HibernateDemo1 {
    /**
     *步骤分析：
     *  1、解析主配置文件
     *  2、创建sessionFactory  --线程安全  一个应用只有一个
     *  3、创建session   --线程不安全，一个线程对应一个session
     *  4、开启事物
     *  5、执行操作
     *  6、提交事务
     *  7、释放资源
     */
    @Test
    public void test1() {
      Custmer c = new Custmer();
      c.setCustName("黑马6");
      Configuration cfg = new Configuration();
      cfg.configure();
      
      SessionFactory sf =  cfg.buildSessionFactory();
      
      Session session = sf.openSession();
      //System.out.println(session.toString());
      Transaction tx = session.beginTransaction();
      
      
      session.save(c);
      
      tx.commit();
      
      session.close();
      sf.close();

    }
}
