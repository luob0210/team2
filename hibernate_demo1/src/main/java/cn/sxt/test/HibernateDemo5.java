package cn.sxt.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.sxt.util.HibernateUtil;

/**
 * hql : hibernate query language
 *      规则：将数据库表名替换成Java实体对象名（类名），将数据库字段名替换成Java属性名
 *      hql:            select  custId  from  custmer
 *      sql:            select   cust_id from cust_cust_custmer
 */
public class HibernateDemo5 {
    
    //基本查询
    @Test
    public void hqlQuery() {
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        //1、获取query对象
        Query query = s.createQuery("from Custmer");
        List list = query.list();
        for (Object o : list) {
            System.out.println(o);
        }
        
        tx.commit();
        
    }
    
    
  //条件查询
    @Test
    public void hqlQueryById() {
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        //1、获取query对象
        Query query = s.createQuery("from Custmer where custName = :custName and custLevel like :custLevel");
//        query.setParameter("custName", "黑马1");
//        query.setParameter("custLevel", "%改%");
        
        List list = query.list();
        for (Object o : list) {
            System.out.println(o);
        }
        
        tx.commit();
    }
    
    
  //排序查询
    @Test
    public void hqlQueryOrderBy() {
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        //1、获取query对象  asc升序，desc降序排序  order by 字段名  desc
        Query query = s.createQuery("from Custmer order by custId desc");

        
        List list = query.list();
        for (Object o : list) {
            System.out.println(o);
        }
        
        tx.commit();
    }
    
    
    
  //分页查询
    @Test
    public void hqlQueryListPage() {
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        //1、获取query对象  asc升序，desc降序排序  order by 字段名  desc
        Query query = s.createQuery("from Custmer order by custId desc");
        //页码 （当前页-1 ）* 每页条数  =first     
        query.setFirstResult(0);
        query.setMaxResults(2);
        List list = query.list();
        for (Object o : list) {
            System.out.println(o);
        }
        
        tx.commit();
    }
    
    
  //统计查询
    /**
     * 数据库中的聚合函数
     *          count   avg   max   min   sum
     * 
    *@description:
    *@parameter:
    *@return:void
     */
    @Test
    public void hqlQueryCount() {
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        //1、获取query对象  asc升序，desc降序排序  order by 字段名  desc
        Query query = s.createQuery("select count(*) from Custmer");
        
        
//        List list = query.list();
//        for (Object o : list) {
//            System.out.println(o);
//        }
        Long count = (Long) query.uniqueResult();//返回唯一结果
        System.out.println(count);
        tx.commit();
    }
    
    
  //投影查询
    /**
     * 解析：可以看出第二种解决方案返回的对象（被泛型限定的User），这种方式解决了传递Object[]方式，只传递对象了，且也实现了，只查询部分属性，需要注意的是User 映射对象 中必须

要有对应查询语句的构造参数（还要补上一个无参数构造方法，不然在进行merge,saveOrUpdate等方法操作时会报错(复制副本的时候没有无参构造函数)）

public User(String name,int sex){

........

}不然会报org.hibernate.hql.ast.QuerySyntaxException异常，通过以上给出的解决方案，已经能解决查询部分属性，提高了查询速度。
    * 
    *@description:
    *@parameter:
    *@return:void
     */
    @Test
    public void hqlQuerycC() {
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        //1、获取query对象
        Query query = s.createQuery("select new cn.sxt.vo.Custmer(custName,custAddress) from Custmer");
        List list = query.list();
        for (Object o : list) {
            System.out.println(o);
        }
        
        tx.commit();
    }
   
}
