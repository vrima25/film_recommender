package com.enigma.film_recommender.repository;

import com.enigma.film_recommender.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("userRepo")
public class UserRepo implements IRepo<User>{
    private JdbcTemplate jdbcTemplate;

    public UserRepo(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_INSERT_USER = "INSERT INTO users(name, email) values(?,?)";
    private final String SQL_GET_ALL = "SELECT * FROM users";
    private final String SQL_FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String SQL_UPDATE_USER = "UPDATE users SET name=? email=?";
    private final String SQL_DELETE_USER = "DELETE FROM users where id=?";

    @Override
    public User create(User user) throws Exception {
        int result = jdbcTemplate.update(SQL_INSERT_USER, user.getName(), user.getEmail());
        if(result <= 0){
            throw new Exception("INSERT DATA FAILED");
        }
        return user;
    }

    @Override
    public List<User> getAll() throws Exception {
        return jdbcTemplate.query(SQL_GET_ALL,
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User addUser = new User();
                        addUser.setId(rs.getInt("id"));
                        addUser.setName(rs.getString("name"));
                        addUser.setEmail(rs.getString("email"));
                        return addUser;
                    }
                });
//        return null;
    }

    @Override
    public Optional<User> getById(Integer id) throws Exception {
        User user = jdbcTemplate.queryForObject(SQL_FIND_BY_ID,
                (rs, rowNum) -> {
                    User addUser = new User();
                    addUser.setId(rs.getInt("id"));
                    addUser.setName(rs.getString("name"));
                    addUser.setEmail(rs.getString("email"));
                    return addUser;
                }, id);
        return Optional.ofNullable(user);
    };

    @Override
    public void update(User user, Integer id) throws Exception {
        jdbcTemplate.update(SQL_UPDATE_USER, user.getName(), user.getEmail());
    }

    @Override
    public void delete(Integer id) throws Exception {

    }
}