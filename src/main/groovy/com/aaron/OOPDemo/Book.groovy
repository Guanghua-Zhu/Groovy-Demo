package com.aaron.OOPDemo


class BookDemo{
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
        def book5 = new Book("price": 55, "count": 2)
        def book6 = new Book("price": 66, "bookName": "Kafka In Action")


        assert book1.bookName == "C Primer Plus"


        println "gg"
    }
}

class Book{
    String bookName

    double price

    def count

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

    def totalMoney() {
        return price * count
    }
}
