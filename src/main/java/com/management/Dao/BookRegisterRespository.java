package com.management.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.management.entities.Book;

public interface BookRegisterRespository extends CrudRepository<Book, Integer>{

	Page<Book> findAll(Pageable pageable);
}
