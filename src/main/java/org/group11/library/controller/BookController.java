package org.group11.library.controller;

import java.io.IOException;

import org.group11.library.entity.Book;
import org.group11.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.dev33.satoken.util.SaResult;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public SaResult getAll() {
        return new SaResult(SaResult.CODE_SUCCESS, "getAll", bookService.list());
    }

    @PostMapping
    public SaResult save(@RequestBody Book book) throws IOException {
        boolean flag = bookService.save(book);
        return new SaResult(SaResult.CODE_SUCCESS, Boolean.toString(flag), flag ? "添加成功" : "添加失败");
    }

    @PutMapping
    public SaResult update(@RequestBody Book book) throws IOException {
        if (book.getName().equals("123"))
            throw new IOException();
        boolean flag = bookService.modify(book);
        return new SaResult(SaResult.CODE_SUCCESS, Boolean.toString(flag), flag ? "修改成功" : "修改失败");
    }

    @DeleteMapping("{id}")
    public SaResult delete(@PathVariable Integer id) {
        return new SaResult(SaResult.CODE_SUCCESS, "delete", bookService.delete(id));
    }

    @GetMapping("{id}")
    public SaResult getById(@PathVariable Integer id) {
        return new SaResult(SaResult.CODE_SUCCESS, "getById", bookService.getById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public SaResult getPage(@PathVariable int currentPage, @PathVariable int pageSize, Book book) {

        IPage<Book> page = bookService.getPage(currentPage, pageSize, book);
        // 如果当前页码值大于总页码值，重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = bookService.getPage((int) page.getPages(), pageSize, book);
        }
        return new SaResult(SaResult.CODE_SUCCESS, "getPages", page);
    }

}
