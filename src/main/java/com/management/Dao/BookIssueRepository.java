package com.management.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.management.entities.Book;
import com.management.entities.BookIssue;

public interface BookIssueRepository extends CrudRepository<BookIssue, Integer> {

	//BookIssue getuniquebookassignnames(String rollnumberid);

	
	
	@Query("select e FROM BookIssue e WHERE e.accessionNumber = :accessionNumber and e.status = :status and e.rollnumberid =:rollnumberid and e.selectbook =:selectbook")
	public BookIssue getuniquebookassignnames(@Param("accessionNumber") String accessionNumber , @Param("status") String status ,@Param("rollnumberid") String rollnumberid , 
			@Param("selectbook") String selectbook);

	@Query("select e FROM BookIssue e WHERE e.selectbook =:selectbook and e.status = :status and e.rollnumberid =:rollnumberid")
	public BookIssue getAssignbookname(@Param("selectbook") String selectbook,@Param("status") String status , @Param("rollnumberid") String rollnumberid );
	
	
	@Query("select e FROM BookIssue e WHERE e.rollnumberid =:rollnumberid and e.status = :status and e.selectStudent = :selectStudent")
	public List<BookIssue> findrollnumberandBookIssuestudentname(@Param("rollnumberid") String rollnumberid,@Param("status") String status, @Param("selectStudent") String selectStudent);
	
	
	@Query("select e FROM BookIssue e WHERE e.status = :status and e.accessionNumber =:accessionNumber")
	public BookIssue getSameBookAsscession(@Param("status") String status, @Param("accessionNumber") String accessionNumber);
	
	@Query("select e FROM BookIssue e WHERE e.status = :status and e.accessionNumber =:accessionNumber")
	public BookIssue withoutAccessionNumberFind(@Param("status") String status, @Param("accessionNumber") String accessionNumber);
	
	@Query("SELECT e FROM BookIssue e WHERE e.duebook = :duebook")
    List<BookIssue> getnodueebook(@Param("duebook") String duebook);
	
	@Query("select e FROM BookIssue e WHERE e.status = :status")
	List<BookIssue> findallbookissue(@Param("status") String status);
	
	
	@Query("select e FROM BookIssue e WHERE e.status = :status and e.duebook = :duebook")
	List<BookIssue> findallbookissuewithDue(@Param("status") String status , @Param("duebook") String duebook);
}
