package com.management.Dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.management.entities.Book;
import com.management.entities.BookIssue;
import com.management.entities.Register;
import com.management.entities.Student;
import com.management.entities.bookDetail;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

public interface RegisterRepository extends CrudRepository<Student, Integer> {

	Student save(Student student);
	
	Book save(Book book);
	
	BookIssue save(BookIssue bookissue);
	
	Register save(Register register);
	

	@Query("SELECT MAX(e.serailNo) FROM Student e")
	Integer findMaxSerailNumberStudent();
	
	@Query("select e FROM Student e")
	public List<Student> getAllStudent();
	
	 @Query(value = "SELECT COUNT(*) FROM Student")
	 long countTotalStudent();
	
	@Query("select e FROM Book e")
	public List<Book> getAllBook();
	
	 @Query(value = "SELECT COUNT(*) FROM Book")
	 long countTotalBooks();
	
	@Query("select e FROM BookIssue e")
	public List<BookIssue> getAllBookissue();
	
	 @Query(value = "SELECT COUNT(*) FROM BookIssue")
	 long countTotalBooksIssue();
	
	@Query("SELECT e.booktitle FROM Book e")
	List<String> getBooknames();
	
	@Query("SELECT e.fullName FROM Student e")
	List<String> getStudentnames();
	
	@Query("SELECT e FROM Student e WHERE e.selectSemester = :selectSemester")
	List<Student> getStudentSemesternames(@Param("selectSemester") String selectSemester);
	
	@Query("SELECT e FROM Student e WHERE e.selectBranch = :selectBranch")
	List<Student> getStudentbranchnames(@Param("selectBranch") String selectBranch);
	
	@Query("SELECT e FROM Book e WHERE e.bookCategory = :bookCategory")
	List<Book> getbookCategorynames(@Param("bookCategory") String bookCategory);
	
	@Query("SELECT e.rollnumber FROM Student e")
	List<String> getStudentRollnumber();
	
	@Query("select e.fullName FROM Student e WHERE e.rollnumber = :rollnumber")
	public List<String> getstudentrollnumberwithName(@Param("rollnumber") String rollnumber);
	
	/*
	 * @Query("select e.bookname FROM Book e WHERE e.semesterName = :semesterName")
	 * public List<String> getfetchsemesterbook(@Param("semesterName") String
	 * semester);
	 */
	
	@Query("select e FROM Student e WHERE e.rollnumber = :rollnumber")
	public Student getstudentrollnumberobject(@Param("rollnumber") String rollnumber);
	
	@Query("select e FROM Student e WHERE e.fullName = :fullName AND e.phone =:phone")
	public Student getfullnameandmobile(@Param("fullName") String fullName, @Param("phone") String phone);
	
	@Query("select e FROM Student e WHERE e.rollnumber = :rollnumber and e.fullName = :fullName")
	public Student getstudentrollnumberandNameobject(@Param("rollnumber") String rollnumber , @Param("fullName") String fullName );
	
	@Query("select e FROM Student e WHERE e.primaryKey = :primaryKey")
	public Student getAllStudentBYID(@Param("primaryKey") Long long1);
	
	@Query("select e FROM Student e WHERE e.primaryKey = :primaryKey")
	public Student getstudentrecord(@Param("primaryKey") int primarykey);
	
	@Query("select e FROM Student e WHERE e.primaryKey = :primaryKey")
	public Student getAllStudentBYID1(@Param("primaryKey") String primaryKey);
	
	@Query("select e FROM BookIssue e WHERE e.primaryKey = :primaryKey")
	public BookIssue getAllfetchbookisseBYID(@Param("primaryKey") String primaryKey);
	
	@Query("select e FROM BookIssue e WHERE e.rollnumberid = :rollnumberid and e.status =:status")
	public List<BookIssue> getAllbookissuetablerecord(@Param("rollnumberid") String rollnumberid , @Param("status") String status );
	
	@Modifying
	@Transactional
	@Query("UPDATE BookIssue b SET b.fine = :fine , b.duebook = :duebook WHERE b.primaryKey = :primaryKey")
	public void updatecharges(@Param("fine") String fine,@Param("duebook") String duebook, @Param("primaryKey") int primaryKey  );
	
	
	@Modifying
	@Transactional
	@Query("UPDATE BookIssue b SET b.status = :status , b.studentRetrunBookDate = :studentRetrunBookDate , b.duebook = :duebook WHERE b.primaryKey = :primaryKey")
	public void updatestatusbook(@Param("status") String status, @Param("studentRetrunBookDate") Date studentRetrunBookDate,@Param("duebook") String duebook, @Param("primaryKey") int primaryKey);

	@Modifying
	@Transactional
	@Query("UPDATE Book b SET b.bookquantity = :bookquantity WHERE b.primaryKey = :primaryKey")
	public void updateaddbookQuantity(@Param("bookquantity") Integer bookquantity, @Param("primaryKey") int primaryKey);

	
	@Query("select e FROM Book e WHERE e.primaryKey = :primaryKey")  
	public Book getBookBYID1(@Param("primaryKey") String primaryKey);
	
	@Query("select e FROM Book e WHERE e.primaryKey = :primaryKey")  
	public Book getobjectBookBYID1(@Param("primaryKey") int primaryKey);
	
	@Query("select e FROM Book e WHERE e.primaryKey = :primaryKey")  
	Book gebookDetailsByPk(@Param("primaryKey") int primaryKey);
	
	@Query("select e FROM Book e WHERE e.primaryKey = :primaryKey")
	public List getbokname(@Param("primaryKey") String primaryKey);
	
	@Query("select e FROM Book e WHERE e.booktitle = :booktitle")
	public Book getselectBookName(@Param("booktitle") String booktitle);
	
	@Query("select e FROM Book e WHERE e.primaryKey = :primaryKey")
	public Book getselectBookNamePK(@Param("primaryKey") int primaryKey);
	
	@Query("select e.booktitle FROM Book e WHERE e.booktitle = :booktitle")
	public List<String> getselectBookNamexits(@Param("booktitle") String booktitle);
	
	@Query("select e.rollnumber FROM Student e WHERE e.rollnumber = :rollnumber")
	public List<String> getselectRollNumberexits(@Param("rollnumber") String rollnumber);
	
	@Query("select e.email FROM Student e WHERE e.email = :email")
	public List<String> getselectemailexits(@Param("email") String email);
	
	@Query("select e FROM Book e WHERE e.primaryKey = :primaryKey")
	public Optional<Book> getBookBYIDint(@Param("primaryKey") Integer primaryKey);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Student e WHERE e.primaryKey = :primaryKey")
    public void deleteBySomeField(@Param("primaryKey") Integer primaryKey);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM BookIssue e WHERE e.primaryKey = :primaryKey")
    public void deleteBySomeFieldbookissue(@Param("primaryKey") Integer primaryKey);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Book e WHERE e.primaryKey = :primaryKey")
    public void deleteByBookField(@Param("primaryKey") Integer primaryKey);
	
	@Modifying
	@Transactional
	@Query("UPDATE Book b SET b.bookquantity = :bookquantity WHERE b.primaryKey = :primaryKey")
	public void updateleftbook(@Param("bookquantity") Integer bookquantity, @Param("primaryKey") int primaryKey);
	
	@Modifying
	@Transactional
	@Query("UPDATE Book b SET b.bookquantity = :bookquantity WHERE b.primaryKey = :primaryKey")
	public void updateleftbookPK(@Param("bookquantity") Integer bookquantity, @Param("primaryKey") int primaryKey);

	

//	@Query("select e FROM Register e WHERE e.username = :username")
	//public Register finduserName(@Param("username") String username );
	
	@Query("select e.username FROM Register e WHERE e.username = :username")
	public List<String> finduserName(@Param("username") String username);
	//Register findUserNameAndPassword(String username, String password);
	
	@Query("select e.email FROM Register e WHERE e.email = :email")
	public List<String> findemailName(@Param("email") String email);
	
	
	@Query("select e FROM Student e  WHERE e.createdAt <=:sixMonthsAgoTimestamp ")
	List<Student> allgetstudentsixmonth(@Param("sixMonthsAgoTimestamp") Timestamp sixMonthsAgoTimestamp);
	
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("UPDATE Student b SET b.select_semester = :selectSemester , b.created_at =: createdAt WHERE b.primary_key = :primaryKey"
	 * ) public Optional<Student> updateSemester(@Param("selectSemester") String
	 * selectSemester, @Param("createdAt") Date createdAt, @Param("primaryKey") int
	 * primaryKey);
	 */
	
	@Modifying
	@Transactional
	@Query("UPDATE Student b SET b.bookBank = :bookBank , b.selectSemester = :selectSemester , b.modifiedOn = :modifiedOn , b.selectYear = :selectYear WHERE b.primaryKey = :primaryKey")
	Optional updateSemester(@Param("bookBank") String bookBank,@Param("selectSemester") String selectSemester, @Param("modifiedOn") Date modifiedOn, @Param("selectYear") String selectYear , @Param("primaryKey") int primaryKey);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE Student b SET  b.noDuess = :noDuess WHERE b.primaryKey = :primaryKey")
	void updateAddRemark(@Param("noDuess") String noDuess, @Param("primaryKey") String primaryKey);
	
	
	
	/*
	 * @Query("select e FROM Book e WHERE e.bookCategory = :bookCategory or e.bookname = :bookname"
	 * ) public List<Book> getfilterbyname(@Param("bookCategory") String
	 * bookCategory, @Param("bookname") String bookname);
	 */


//	Student getStudentById(Long long1);
}
