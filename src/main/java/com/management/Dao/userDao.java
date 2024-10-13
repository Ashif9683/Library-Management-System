package com.management.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.management.entities.Book;
import com.management.entities.user;

import jakarta.transaction.Transactional;

public interface userDao extends CrudRepository<user, Integer>{
	
	
	@Query("select e.username FROM user e WHERE e.username = :username")
	List<String> checkusr(@Param("username") String username);
	
	@Query("select e.email FROM user e WHERE e.email = :email")
	public List<String> checkemail(@Param("email") String email);
	
	@Query("select e FROM user e WHERE e.username = :username AND e.password = :password")
	public user finduserNameAndPassword(@Param("username") String username , @Param("password") String password);
	

	@Query("select e FROM user e WHERE e.username = :username AND e.password = :password")
	public user finduserNameAndPasswordMobile(@Param("username") String username , @Param("password") String password);
	
	@Query("select e FROM user e")
	public List<user> getAlluser();
	
	@Modifying
    @Transactional
    @Query("DELETE FROM user e WHERE e.primarykey = :primarykey")
    public void deleteByUserField(@Param("primarykey") Integer primaryKey);
	
	@Query("select e FROM user e WHERE e.primarykey = :primarykey")
	public user getUserbyID(@Param("primarykey") String primarykey);
	
	@Query("select e FROM user e WHERE e.primarykey = :primarykey")
	public Optional<user> getupdateUserbyID(@Param("primarykey") Integer primaryKey);
}
