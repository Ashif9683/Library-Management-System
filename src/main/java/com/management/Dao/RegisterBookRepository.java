package com.management.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.management.entities.Book;
import com.management.entities.bookDetail;

public interface RegisterBookRepository extends CrudRepository<Book, Integer> {
	
	
	@Query("SELECT MAX(e.serailNo) FROM Book e")
	Integer findMaxSerailNumber();

	
}
;



