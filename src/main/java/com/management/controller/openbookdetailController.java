package com.management.controller;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.Dao.BookDetailRepository;
import com.management.Dao.RegisterRepository;
import com.management.entities.bookDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Controller
public class openbookdetailController {
	
	
	@Autowired
	BookDetailRepository bookdetailRepository;
	
	@Autowired
	RegisterRepository registerRepository;

	@PostMapping("/fetchbookAccessiondetails")
    @ResponseBody
    public List findByBookPrimaryKey(@RequestBody String primaryKey) {
    	List bookDetails = this.bookdetailRepository.getopenbookdetail(primaryKey);
		return bookDetails;
    }
}
