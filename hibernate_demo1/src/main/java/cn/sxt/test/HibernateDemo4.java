package cn.sxt.test;

import org.hibernate.Session;
import org.junit.Test;

import cn.sxt.util.HibernateUtil;


/**
 *  session绑定线程：hibernate.current_session_context_class = thread
 *       opensession()每次都是新建一个session对象，需要手动关闭
 *       getCurrentSession() 事物结束，当commit或者 rollback时自动关闭
 * 
 * @author mac
 *@date:2019年1月26日
 */
public class HibernateDemo4 {
    
    @Test
    public void test1() {
        Session s1 = HibernateUtil.getCurrentSession();
        Session s2 = HibernateUtil.getCurrentSession();
        
        System.out.println(s1==s2);
        
    }
}
