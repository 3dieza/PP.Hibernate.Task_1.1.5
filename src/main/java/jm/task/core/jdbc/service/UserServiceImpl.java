package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl repository;

    public UserServiceImpl() {
    }

    public void createUsersTable() {


    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
repository.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
repository.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    public void cleanUsersTable() {

    }
}
