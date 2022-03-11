package team.concurrency.mealproject.repository.auth;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.concurrency.mealproject.entity.User;
import team.concurrency.mealproject.repository.base.AbstractRepository;

import javax.transaction.Transactional;


@Repository
public interface AuthUserRepository extends AbstractRepository<User, String> {


    @Transactional
    @Modifying
    @Query(value = "update main.users set deleted = true where id like :id", nativeQuery = true)
    void softDeleteById(@Param(value = "id") String id);

    @Transactional
    @Modifying
    @Query(value = "update main.users set password = ?2 where id = ?1", nativeQuery = true)
    void resetPassword(String id, String newPassword);

    @Query(value = "select * from main.users where username like :username and not deleted", nativeQuery = true)
    User findByUsername(@Param("username") String username);

    @Query(value = "select * from main.users where chat_id =:chatId ", nativeQuery = true)
    User getByChatId(@Param("chatId") Long chatId);

    @Transactional
    @Modifying
    @Query(value = "update main.users  set status =0 where id =:id1", nativeQuery = true)
    void confirmUser(@Param("id1") String id1);



    @Query(value = "select * from main.users where role =:role", nativeQuery = true)
    User getByRole(@Param("role") String role);

    @Query(value = "select * from main.users where department =:department and position = 'administrator' ", nativeQuery = true)
    User getAdministratorByDepartment(String department);

    @Transactional
    @Modifying
    @Query(value = "update main.users set status = 0 where chat_id = :chatId", nativeQuery = true)
    void acceptUser(@Param("chatId") Long chatId);

}
