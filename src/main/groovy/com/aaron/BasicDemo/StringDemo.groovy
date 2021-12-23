package com.aaron.BasicDemo

/**
 * Groovy 字符串 示例
 */
class StringDemo {
    static void main(args) {
        def x = 1
        /*********************** 单引号 ***********************/
        def str1 = 'str 1: $x'
        // 单引号字符串 是Java String的实例
        assert str1 instanceof String
        println("-------------------")
        // 由于不支持插值, 故会输出 str 1: $x
        println(str1)

        /*********************** 双引号 ***********************/
        def str2a = "str 2a: Hello"
        // 双引号字符串 中如果不含占位符, 则其是Java String的实例
        assert str2a instanceof String
        assert str2a == 'str 2a: Hello'

        def str2b = "str 2b: $x"
        // 双引号字符串 中如果含占位符, 则其是Groovy GString的实例
        assert str2b instanceof GString
        assert str2b == 'str 2b: 1'

        /********************** 三重单引号 *********************/
        def str3a = '''str3a
line 1
$x
line 2
line 3'''
        // 三重单引号字符串 是Java String的实例, 不支持插值
        assert str3a instanceof String
        println("-------------------")
        println(str3a)

        // 使用续航符避免第一行出现换行符
        def str3b = '''\
            str3b
            hello'''
        println("-------------------")
        // 通过stripIndent方法移除指定数量的缩进
        println( str3b.stripIndent(12) )

        /********************** 三重双引号 *********************/
        def str4a = """str4a
line 1
line 2
line 3"""
        // 如果不含占位符, 则其是Java String的实例
        assert str3a instanceof String
        println("-------------------")
        println(str4a)

        def str4b = """str4b
line 1
$x
line 3"""
        // 如果含占位符, 则其是Groovy GString的实例
        assert str4b instanceof GString
        println("-------------------")
        println(str4b)

        // 斜杠表示字符串 Hello"12\World ,无需通过反斜杠进行转义
        def str5a = /Hello"12\World/
        // 如果不含占位符, 则其是Java String的实例
        assert str5a instanceof String
        // 可以看到如果使用双引号表示字符串时, 需要使用大量的反斜杠进行转义
        assert str5a == "Hello\"12\\World"

        def str5b = /a${x}c/
        // 如果含占位符, 则其是Groovy GString的实例
        assert str5b instanceof GString
        assert str5b == 'a1c'
    }
}

