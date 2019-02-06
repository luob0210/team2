package cn.sxt.test;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.sxt.util.HibernateUtil;
import cn.sxt.vo.Custmer;

public class hibernateDemo2 {
    
    @Test
    public void test1() {
        Custmer c = new Custmer();
        c.setCustAddress("湖南长沙");
        Session session =  HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(c);
        
        tx.commit();
        session.close();
    }
    
    @Test
    public void update() {
        
        Session s =  HibernateUtil.openSession();
        Transaction tx = s.beginTransaction();
        //按ID查询信息,如果类型为Integer，即为基本数据类型2
        //                   如果类型为Long，即为2L
        Custmer cust = (Custmer) s.get(Custmer.class, 2);
        System.out.println(cust);
        //修改信息
        cust.setCustLevel("测试修改xxx");
        s.update(cust);
        
        tx.commit();
        s.close();
    }
    
    @Test
    public void delete() {
        Session s =  HibernateUtil.openSession();
        Transaction tx = s.beginTransaction();
        
        //删除，不能按ID删除，不能传参数，只能传对象
        //先得到要删除的对象
        Custmer cust = (Custmer) s.get(Custmer.class, 3);
        //传对象
        s.delete(cust);
        tx.commit();
        s.close();
        
    }
    
    /**
     * 查询所有
    *@description:
    *@parameter:
    *@return:void
     */
    @Test
    public void findAll() {
        Session s =  HibernateUtil.openSession();
        Transaction tx = s.beginTransaction();
       
        SQLQuery sqlq = s.createSQLQuery("select * from cust_custmer");
        List<Object[]> list = sqlq.list();
        
        for (Object[] os : list) {
            System.out.println(os);
            for (Object v : os) {
                System.out.println(v);
            }
        }
        
        tx.commit();
        s.close();
        
    }
}
