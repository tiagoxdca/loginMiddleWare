package dao;

import domain.User;
import org.junit.Assert;
import org.junit.Test;
import util.DB;

import java.sql.SQLException;

public class UserDaoTest {

    @Test
    public void testConnection() throws SQLException {
        boolean user_table = UserDao.createUSER_TABLE();
        Assert.assertTrue(!user_table);
    }

    @Test
    public void insertUserInDataBase(){
        User tiago = User.builder().name("xavier").email("xavier@hotmail.com").passaword("123456").build();

        int success = UserDao.save_user(tiago);
        Assert.assertTrue(success == 1);
    }

    @Test
    public void getUserByEmail(){
        String email = "soares343vio@hotmail.com";
        User user = UserDao.getUserByEmail(email);
        Assert.assertEquals("Tiago", user.getName());
    }

    @Test
    public void getUsertByPassword(){
        String password = "135792468txdca";
        User user = UserDao.getUserByPassword(password);
        Assert.assertEquals("soares343vio@hotmail.com", user.getEmail());
    }

}
