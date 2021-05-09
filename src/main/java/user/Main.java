package user;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args){
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        RandomUsers randomusers = new RandomUsers(new Locale("hu"));
        List<User> users = jdbi.withExtension(UserDao.class, dao->{
            dao.createTable();
            System.out.println(dao.insertUser(randomusers.generate()));
            System.out.println(dao.insertUser(randomusers.generate()));
            System.out.println(dao.insertUser(randomusers.generate()));
            System.out.println(dao.insertUser(randomusers.generate()));
            System.out.println(dao.insertUser(randomusers.generate()));

            System.out.println(dao.getUserById((long)2));
            System.out.println(dao.getUserByName(dao.getUserById((long)2).get().getUsername()));
            dao.deleteUser(dao.getUserById((long)2).get());
            return dao.listUsers();
        });
    }
}
