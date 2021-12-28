package com.aaron.TempTest

class Book {
    private String title

    Book(String theTitle) {
        title = theTitle
    }

    String getTitle() {
        return title
    }

    void setTitle(String theTitle) {
        title = theTitle
    }

    Book plus(Book other) {
        if(other==null) {
            return null
        }

        return new Book( title +"-"+other.title )
    }

    static void main(args) {
        Book book1 = new Book("C Primer")
        Book book4 = new Book("C Primer")

        assert  book1 == book4

        Book book2 = new Book("Java Core")

        Book book3 = book1 + book2
        println(book3.toString())

    }
}
