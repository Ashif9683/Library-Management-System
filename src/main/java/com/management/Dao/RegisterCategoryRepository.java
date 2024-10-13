package com.management.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.management.entities.AccessionCode;
import com.management.entities.Branch;
import com.management.entities.Category;

import jakarta.transaction.Transactional;

public interface RegisterCategoryRepository extends CrudRepository<Category, Integer> {
	
	AccessionCode save(AccessionCode accessioncode);
	
	Branch save(Branch branch);
	
	@Query("SELECT e FROM Category e")
	List<Category> getcategorynametable();
	
	@Query("SELECT e FROM AccessionCode e")
	List<AccessionCode> getAccessionCode();
	
	
	@Query("SELECT e.categoryName FROM Category e")
	List<String> getcategoryname();
	
	@Query("SELECT e.accessionCodeCategory FROM AccessionCode e")
	List<String> getallAccessionCode();
	
	@Query("SELECT e.branchName FROM Branch e")
	List<String> getbranchname();


	

	@Query("SELECT e FROM Branch e")
	List<Branch> getbranchnametable();
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Branch e WHERE e.primaryKey = :primaryKey")
    public void deleteByBranchField(@Param("primaryKey") Integer primaryKey);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM AccessionCode e WHERE e.primaryKey = :primaryKey")
    public void deleteByAccession(@Param("primaryKey") Integer primaryKey);


	
}
