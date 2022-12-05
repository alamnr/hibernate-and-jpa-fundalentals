package com.example.hibernate.jdbc.client;

import java.util.Arrays;
import java.util.List;

import com.example.hibernate.jdbc.entity.Book;
import com.example.hibernate.jdbc.entity.Chapter;
import com.example.hibernate.jdbc.entity.Publisher;
import com.example.hibernate.jdbc.service.BookStoreService;

public class BookStoreClient {
    public static void main(String[] args) {
        Chapter chapter_1 = new Chapter(1, "How to doggy ");
        Chapter chapter_2 = new Chapter(2, "How to missionary ");
        Chapter chapter_3 = new Chapter(3, "How to top up ");
        List<Chapter> chapters = Arrays.asList(chapter_1, chapter_2, chapter_3);
        Publisher publisher = new Publisher("1234","Kama Publisher");

        Book book = new Book("isbn-1", "Kamasutra", publisher);

        book.setChapters(chapters);

        BookStoreService bookStoreService = new BookStoreService();
        bookStoreService.persistObjectGraph(book);

        book = bookStoreService.retrieveObjectGraph("isbn-1");
        System.out.println("Book detail: " + book);
    }
}
