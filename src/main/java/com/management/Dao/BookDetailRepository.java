package com.management.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.management.entities.Book;
import com.management.entities.bookDetail;

public interface BookDetailRepository extends CrudRepository<bookDetail, Integer>{
		
	@Query("SELECT bd FROM bookDetail bd WHERE bd.book.primaryKey = :primaryKey")
	public List getopenbookdetail(@Param("primaryKey") String primaryKey);

	//public bookDetail findAllBybook(String primaryKey);
	
	//boolean existsByaccessionNumber(String accessionNumber);
	
	@Query("SELECT MAX(e.serailNo) FROM bookDetail e")
	Integer findMaxSerailNumber();
	
	 @Query(value = "SELECT COUNT(*) FROM bookDetail")
	 long countTotalBooksdetails();
	
	
		
	//	  @Query(value =
		//  "SELECT MAX(CAST(SUBSTRING(accession_number, INSTR(accession_number, '-') + 1) AS SIGNED)) AS max_value "
		//  + "FROM libarymanagement.book_detail WHERE accession_number REGEXP '^[A-Za-z]*-[0-9]+$' OR accession_number REGEXP '^[0-9]+$'" , nativeQuery = true) 
		//  String findMaaxAccessionNumber();
		 
    @Query(value= "SELECT MAX(accession_number) FROM libarymanagement.book_detail  WHERE accession_number REGEXP '^[0-9]+$'", nativeQuery = true)
	String findMaxAccessionNumber();
		  
		  
    @Query(value = "SELECT accession_number FROM libarymanagement.book_detail WHERE accession_number LIKE :code ORDER BY CAST(SUBSTRING(accession_number, :startPosition) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findCodeMax(@Param("code") String code ,@Param("startPosition") int startPosition );

	@Query("select e FROM bookDetail e WHERE e.accessionNumber = :accessionNumber")
	public bookDetail fetchAccessionNumber(@Param("accessionNumber") String accessionNumber);
	
	
	@Query(value = "SELECT * FROM libarymanagement.book_detail WHERE accession_number REGEXP '^[0-9]+$'", nativeQuery = true)
    List<bookDetail> findNumericRecords();
	/*
	 * @Query("select e FROM bookDetail e WHERE e.exitAsscessionNumber = :exitAsscessionNumber or e.accessionNumber = :exitAsscessionNumber"
	 * ) public bookDetail
	 * fetchAccessionNumberORexiting(@Param("exitAsscessionNumber") String
	 * exaccessionNumber);
	 */
	@Query("select e.accessionNumber FROM bookDetail e ")
	public List fetchAccession();
	
	@Query("select e FROM bookDetail e ")
	public List<bookDetail> ALLfetchbookdetail();
	
	//@Query("select e.exitAsscessionNumber FROM bookDetail e ")
	//public List fetchExitingAccession();
	
	@Query("select e FROM bookDetail e WHERE e.accessionNumber = :accessionNumber")
	public bookDetail getrecordaccessionNumber(@Param("accessionNumber") String accessionNumber);
	
	@Query("SELECT b FROM bookDetail b WHERE b.accessionNumber LIKE :pattern")
    List<bookDetail> findByAccessionNumberLike(@Param("pattern") String pattern);
	
	@Query(value ="SELECT * FROM libarymanagement.book_detail  WHERE fk_book_id = :fk_book_id", nativeQuery = true)
	public List<bookDetail> getdetailbook(@Param("fk_book_id") int fk_book_id );
	//bookDetail getRecordByAccessionNumber(String accessionNumber);
 
}
