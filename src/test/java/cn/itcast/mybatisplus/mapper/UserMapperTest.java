package cn.itcast.mybatisplus.mapper;

import cn.itcast.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * MyBatisPlus测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 基础查询
     */
    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * Like查询
     */
    @Test
    public void testSelectByLike() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(new User());
        //查询名字中包含“o”的用户
        queryWrapper.like("name", "o");
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void testSelectByLe() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(new User());
        //查询年龄小于等于20的用户
        queryWrapper.le("age", 20);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setName("子和");
        user.setAge(24);
        user.setEmail("hezihao@126.com");
        int row = userMapper.insert(user);
        if (row > 0) {
            System.out.println("保存成功");
        } else {
            System.out.println("保存失败");
        }
    }

    /**
     * 测试删除数据
     */
    @Test
    public void testDelete() {
        int row = userMapper.deleteById(6L);
        if (row > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    /**
     * 测试更新数据
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(5L);
        user.setName("罗");
        user.setAge(18);
        user.setEmail("383030391@qq.com");
        int row = userMapper.updateById(user);
        if (row > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    /**
     * 测试分页
     */
    @Test
    public void testSelectPage() {
        IPage<User> page = new Page<>(1, 2);
        IPage<User> userPage = userMapper.selectPage(page, null);
        System.out.println("总条数：---------->" + userPage.getTotal());
        System.out.println("当前页数：---------->" + userPage.getCurrent());
        System.out.println("当前每页显示数量：---------->" + userPage.getSize());
        List<User> users = userPage.getRecords();
        for (User user : users) {
            System.out.println(user);
        }
    }
}