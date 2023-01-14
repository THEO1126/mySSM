package cn.edu.guet.controller;

import cn.edu.guet.bean.Book;
import cn.edu.guet.bean.Result;
import cn.edu.guet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author liwei
 * @Date 2023/1/11 11:44
 * @Version 1.0
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/saveBook.html")
    @ResponseBody
    //public Result saveBook(@RequestBody Book book){//PostMan传JSON格式
    public Result saveBook(Book book){//表单传参
        System.out.println("后台接收的book对象："+book);
        bookService.saveBook(book);
        return new Result(1000,"图书保存成功",null);
    }
}
