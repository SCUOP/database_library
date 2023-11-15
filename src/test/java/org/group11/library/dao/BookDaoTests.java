package org.group11.library.dao;

import java.util.List;

import org.group11.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTests {
    @Autowired
    private BookDao bookDao;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Book> bookList = bookDao.selectList(null);
        bookList.forEach(System.out::println);
    }
}
