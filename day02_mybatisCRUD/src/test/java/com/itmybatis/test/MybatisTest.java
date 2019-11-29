package com.itmybatis.test;

import com.itmybatis.dao.IUserDao;
import com.itmybatis.domain.QueryVo;
import com.itmybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试
 */

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private  IUserDao userDao;

    @Before//用于测试之前执行
    public void init() throws IOException{
        //   1、 读取配置文件
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
//   2、创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
//   3、使用工厂生产SqlSession对象
         sqlSession = factory.openSession();
//   4、使用SqlSession创建Dao接口的代理对象
         userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于测试之后执行
    public void destroy() throws Exception{
        //提交事务
        sqlSession.commit();
        //   6、释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user:
             users) {
            System.out.println(user);

        }
    }
    /**
     * 测试保存
      */
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("modify User mybatis");
        user.setUserAddress("四川");
        user.setUserBirthday(new Date());
        user.setUserSex("男");

//   5、使用代理对象执行保存方法

    userDao.saveUser(user);
    }

    /**
     * 测试更新用户
     */
    @Test
    public void testupdate(){
        User user = new User();
        user.setUserId(51);
        user.setUserName("like");
        user.setUserAddress("重庆");
        user.setUserBirthday(new Date());
        user.setUserSex("男");

//   5、使用代理对象执行保存方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testdelete(){
//   5、使用代理对象执行保存方法
        userDao.deleteUser(50);
    }

    /**
     * 测试查询一个
     */
    @Test
    public void testfindOne(){
        User user = userDao.findById(42);
        System.out.println(user);
    }

    /**
     * 根据姓名模糊查询
     */
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        for (User user:
             users) {
            System.out.println(user);
        }
    }

    /**
     * 查找用户总记录数
     */
    @Test
    public void testFindTotal(){
       int count = userDao.findTotal();
        System.out.println(count);
        }

    /**
     * 测试QueryVo作为查询条件
     */
    @Test
    public void testFindBy(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u:
                users) {
            System.out.println(u);
        }
    }
}
