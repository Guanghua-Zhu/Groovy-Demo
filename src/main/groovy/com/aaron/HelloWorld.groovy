package com.aaron

class HelloWorld {
    static void main(args) {

        def me = 'Aaron'
        def you = 'Tony $me'

        def he = me << ", i"
        def he2 = he.toString()



        def line = "me $me - you $you"
        assert  line == 'me Aaron - you Tony'


        int a=1
        int b=2
        def c = a.plus(b)
        println("c $c")

        def num1 = 2.2
        def num2 = 2.2f
        def num3 = 2.2d


        println("Hello World")

        def book = new Book()
        book.setTitle("d")
        assert book.getTitle() == "d"
        assert book.title == "d"

        // GString
        def firstName = "Aaron"
        def lastName = "Zhu"
        assert "$firstName and $lastName" == "Aaron and Zhu"

        // Number
        def x = 1
        def y = 2
        assert x+y == 3
        assert x.plus(y) == 3
        assert x instanceof Integer

        1.upto(3, num -> println("num: $num") )

        // List
        def numbers = ['', '1', "2", "3"]
        assert numbers[2] == '2'
        numbers[5] = '5'
        assert numbers.size() == 6

        // Map
        def httpCode = [
            100 : "CONTINUE",
            200 : "OK",
            400 : "BAD REQUEST"
        ]

        assert httpCode[200] == "OK"
        httpCode[404] = "Not Found"
        assert httpCode.size() ==4

        // Range
        def num = 2..9
        assert num.contains(4)
        assert num.contains(11) == false
        assert num.size() == 8
        assert num.from == 2
        assert num.to == 9
        assert num.reverse() == 9..2

        // Closure
        [1,3,2].each { e -> println("e: $e") }

    }


}
