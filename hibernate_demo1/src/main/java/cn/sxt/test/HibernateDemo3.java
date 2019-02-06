package cn.sxt.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import cn.sxt.util.HibernateUtil;

public class HibernateDemo3 {
    @Test
    public void test1() {
        Session s = HibernateUtil.openSession();
        System.out.println(s);
        s.doWork(new Work() {
            
            @Override
            public void execute(Connection conn) throws SQLException {
                // TODO Auto-generated method stub
                System.out.println(conn.getClass().getName());
            }
        });
    }
    
    
    /**
     * 对象的三种状态
     *  1、 瞬时   无OID，和session没关系   delete  close
     *  2、持久    有OID，和session有关系   save  update  saveOrUpdate
     *  3、脱管    有OID ，和session没关系  update  
     * 
     */
}
