package com.example.CRUDApplication.controller;

import com.example.CRUDApplication.model.Book;
import com.example.CRUDApplication.model.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/getAllBooks")
    public <List> HttpEntity<ArrayList<Object>> getAllBooks() {
        try {
            ArrayList<Object> bookList = new ArrayList<>();

            bookRepo.findAll().forEach(bookList::add);

            if (bookList.isEmpty()){
                return new ResponseEntity<>(bookList, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBookById/{Id}")
    public HttpEntity<Book> getBookById(@PathVariable long id) {
       Optional<Book> bookData = bookRepo.findById(id);

        if(bookData.isPresent()){
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
       Book bookObj = bookRepo.save(book);

       return new ResponseEntity<>(bookObj, HttpStatus.OK);
    }
@PostMapping("/UpdateBookById/{id}")
    public void updateBookById(@PathVariable long id) {
       Optional<Book> bookData = bookRepo.findById(id);
       if(bookData.isPresent()){
           Book bookObj = bookData.get();
           Object updateBookData = new Object();
           JInternalFrame newBookData = new JInternalFrame();
           updateBookData.equals(newBookData.getTitle());
       }
    }

@DeleteMapping
    public void deleteBookById() {
    }
}



