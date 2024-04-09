package co.istad.ite2mbanking.feature.account;

import co.istad.ite2mbanking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository <Account,Long>{


   Optional <Account> findByActNo(String actNo);

   boolean existsByActNo(String actNo);
   @Modifying
   @Query("""
              UPDATE Account AS a
              SET a.isHidden = TRUE
              WHERE a.actNo = ?1
   """)
   void hideAccountByActNo(String actNo);



}
