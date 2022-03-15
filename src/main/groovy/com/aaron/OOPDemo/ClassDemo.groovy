package com.aaron.OOPDemo

/**
 * Groovy 类的示例: 字段/属性的访问/修改, 对象的实例化
 */
class ClassDemo {
    static void main(String[] args) {
        /*************** 基于Positional Parameters位置参数 ***************/
        // 经典的Java方式调用构造函数1
        def book1 = new Book("C Primer Plus", 11)
        // 通过as运算符的方式调用构造函数1
        def book2 = ["C++ Primer Plus", 2.2] as Book
        // 通过指定变量类型的方式调用构造函数1
        Book book3 =  ["ES In Action", 0.33]

        /*************** 基于Named Parameters命名参数 ***************/
        // 使用命名参数时, 需要通过无参构造器进行实现。即这里的构造函数2
        def book4 = new Book("bookName": "Redis In Action")
        def book5 = new Book("price": 55, "count": 2, "remark": "特价")

        // 支持通过.运算符访问、修改属性/字段的内容
        book5.bookName = "治国理政"
        assert book5.bookName == "治国理政"
        assert book5.price == 55
        assert book5.remark == "特价"

        // 支持通过下标的方式访问、修改属性/字段的内容
        book5["count"] = 24
        assert book5["count"] == 24
        book5["remark"] = "促销价"
        assert book5["remark"] == "促销价"
    }
}

class Book{
    /************* 属性 *************/
    // 对于属性, Groovy会自动生产相应的getter/setter方法
    String bookName

    double price

    def count

    /************* 字段 *************/
    private String remark

    String getRemark() {
        return remark
    }

    void setRemark(String remark) {
        this.remark = remark
    }

    /**
     * 构造函数1
     * @param bookName
     * @param price
     */
    Book(String bookName, double price) {
        this.bookName = bookName
        this.price = price
    }

    /**
     * 构造函数2, 无参构造器
     */
    Book() {
    }
}
