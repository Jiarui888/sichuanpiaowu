package cn.scpw.pojo;

import java.util.Map;

public interface UserMapper {
    //通过用户名和密码来查询
    User selectByUsernameAndPassword(User user);
    //用户修改密码
    void updateByUserPassword(String password, String userId);

    //根据注册时传入的User中的username，查询数据库是否已经存在该用户名
    User selectByUsername(String username);

    //注册----根据User向数据库插入数据
    void insertByUser(User user);

    //通过id显示用户信息
    User userInfoSelectByUserId(User user);

}