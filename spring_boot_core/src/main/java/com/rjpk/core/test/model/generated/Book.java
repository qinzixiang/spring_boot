package com.rjpk.core.test.model.generated;

import java.util.Date;

public class Book {
    /**
     *ID
     */
    private Integer id;

    /**
     *书编号
     */
    private String bookCode;

    /**
     *书名
     */
    private String bookName;

    /**
     *出版社
     */
    private String publish;

    /**
     *出版日期
     */
    private Date createTime;

    /**
     *ID
     */
    public Integer getId() {
        return id;
    }

    /**
     *ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *书编号
     */
    public String getBookCode() {
        return bookCode;
    }

    /**
     *书编号
     */
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode == null ? null : bookCode.trim();
    }

    /**
     *书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     *书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    /**
     *出版社
     */
    public String getPublish() {
        return publish;
    }

    /**
     *出版社
     */
    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    /**
     *出版日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *出版日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}