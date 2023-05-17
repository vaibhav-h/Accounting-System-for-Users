package com.accounting.system.repository;

import com.accounting.system.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value  = "select * from transactions  where txn_type = ?1", countQuery = "select count(*) from transactions  where txn_type = ?1", nativeQuery = true)
    public Page<Transaction> findAllTransactionsWithPagination(String str, Pageable pageable);

    @Query(value  = "select distinct(user_name) from transactions  where user_id = ?1", nativeQuery = true)
    public String findUserNameById(Long id);

    @Query(value  = "select distinct(ip) from transactions  where user_id = ?1", nativeQuery = true)
    public String findIpById(Long id);

    @Query(value  = "select ip from transactions", nativeQuery = true)
    public List<String> findAllIds();

    @Query(value  = "select user_id from transactions where ip = ?1", nativeQuery = true)
    public Long findUserByIp(String ip);

    @Query(value  = "select * from transactions t where t.location_id in " +
            "(select id from location where location_id = ?1) and t.txn_type = ?2 order by t.user_id",
            nativeQuery = true)
    public List<Transaction> findAllTransactionsBTxnTypeAndLocation(Long locationId, String txnTpe);

}
