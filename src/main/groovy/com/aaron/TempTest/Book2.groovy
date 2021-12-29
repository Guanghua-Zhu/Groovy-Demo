package com.aaron.TempTest

class Book2 {
    private String title

    Book2(String theTitle) {
        title = theTitle
    }

    String getTitle() {
        return title
    }

    void setTitle(String theTitle) {
        title = theTitle
    }

    Book2 plus(Book2 other) {
        if(other==null) {
            return null
        }

        return new Book2( title +"-"+other.title )
    }

    static void main(args) {
        Book2 book1 = new Book2("C Primer")
        Book2 book4 = new Book2("C Primer")

        assert  book1 == book4

        Book2 book2 = new Book2("Java Core")

        Book2 book3 = book1 + book2
        println(book3.toString())

    }
}
