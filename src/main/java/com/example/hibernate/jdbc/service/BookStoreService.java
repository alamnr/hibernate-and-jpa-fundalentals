package com.example.hibernate.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.hibernate.jdbc.entity.Book;
import com.example.hibernate.jdbc.entity.Chapter;
import com.example.hibernate.jdbc.entity.Publisher;
import com.mysql.cj.protocol.Resultset;

public class BookStoreService {

    private Connection connection = null;

    public void persistObjectGraph(Book book){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "kuddus", "p@ssw0rd");

            PreparedStatement statement = connection.prepareStatement("insert into publisher (code,publisher_name) values(?,?)");
            statement.setString(1, book.getPublisher().getCode());
            statement.setString(2, book.getPublisher().getName());
            statement.executeUpdate();
            statement.close();

            statement = connection.prepareStatement("insert into book (isbn,book_name,publisher_code) values (?,?,?)");
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getName());
            statement.setString(3, book.getPublisher().getCode());
            statement.executeUpdate();

            statement.close();

            statement = connection.prepareStatement("insert into chapter (book_isbn,chapter_num,title) values (?,?,?)");
            
            for (Chapter chapter : book.getChapters()) {
                statement.setString(1, book.getIsbn());
                statement.setInt(2, chapter.getChapterNumber());
                statement.setString(3, chapter.getTitle());
                statement.executeUpdate();
            }

            statement.close();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try {
                connection.close();    
            } catch (SQLException e) {
                e.printStackTrace();
            }            
        }
    }
    
    public Book retrieveObjectGraph(String isbn){
        Book book = null;
       
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "kuddus", "p@ssw0rd");

                PreparedStatement statement = connection.prepareStatement("select * from book , publisher where book.publisher_code = publisher_code and book.isbn = ?");
                statement.setString(1, isbn);
                ResultSet rs = statement.executeQuery();

                book = new Book();
                if(rs.next()){
                    book.setIsbn(rs.getString("isbn"));
                    book.setName("book_name");
                    Publisher publisher = new Publisher();
                    publisher.setCode(rs.getString("code"));
                    publisher.setName(rs.getString("publisher_name"));
                    book.setPublisher(publisher);
                }

                rs.close();
                statement.close();

                List<Chapter> chapters = new ArrayList<Chapter>();
                statement = connection.prepareStatement("select * from chapter where book_isbn = ?");
                statement.setString(1, isbn);
                rs = statement.executeQuery();

                while(rs.next()){
                    Chapter chapter = new Chapter();
                    chapter.setTitle(rs.getString("title"));
                    chapter.setChapterNumber(rs.getInt("chapter_num"));
                    chapters.add(chapter);
                }
                book.setChapters(chapters);
                rs.close();
                statement.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }  finally {
            try {
                connection.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
        return book;
        
    }
}
