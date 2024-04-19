package co.istad.ite2mbanking.feature.user;

import co.istad.ite2mbanking.domain.User;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByNationalCardId(String nationalCardId);
    boolean existsByStudentIdCard(String studentId);

    @Query(value ="")
    Optional<User> findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    void deleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE User as u SET u.isBlocked = TRUE WHERE u.uuid = ?1")
    void blockByUuid(String uuid);

    @Modifying
    @Query("UPDATE User as u SET u.isDeleted = TRUE WHERE u.uuid = ?1")
    void disableByUuid(String uuid);

    @Modifying
    @Query("UPDATE User as u SET u.isDeleted = FALSE WHERE u.uuid = ?1")
    void enableByUuid(String uuid);


    Optional<User> findByPhoneNumber(String username);
}


