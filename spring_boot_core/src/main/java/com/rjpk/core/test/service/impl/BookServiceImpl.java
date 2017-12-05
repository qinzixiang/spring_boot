package com.rjpk.core.test.service.impl;

import com.rjpk.core.test.dao.generated.BookMapper;
import com.rjpk.core.test.model.generated.Book;
import com.rjpk.core.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BookServiceImpl
 * @Description
 * @Author xiangnan.xu
 * @DATE 2017/12/5 11:27
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookMapper bookMapper;
    public Book getBook() {
        Book book = bookMapper.selectByPrimaryKey(1);
        return book;
    }
}
