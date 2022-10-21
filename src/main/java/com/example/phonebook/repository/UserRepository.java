package com.example.phonebook.repository;

import com.example.phonebook.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u.id, u.first_name,u.last_name, u.created_date, p.number_phone \n"
            + "from users u \n"
            + "left join users_phones up on up.user_id = u.id \n"
            + "left join phones p on p.id = up.phones_id \n"
            + "where p.number_phone like %?1%", nativeQuery = true)
    List<User> findUsersByPhone(String phoneNumber);
}
