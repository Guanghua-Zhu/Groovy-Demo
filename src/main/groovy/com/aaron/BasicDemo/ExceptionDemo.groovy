package com.aaron.BasicDemo

/**
 * Groovy 异常 示例
 */
// Aaron: todo: output 2 blog
class ExceptionDemo {
    static void main(String[] args) {
        def result1 = test1("Java 8 In Action")
        assert result1 =="JAVA 8 IN ACTION"

        /*********************** 支持传统的try/catch/finally语句 ***********************/
        try {
            test1("Groovy In Action")
        } catch (e) {   // 如果期望捕获所有类型的异常, 异常类型可省略
            assert e instanceof FileNotFoundException
            assert e.getMessage() == "文件不存在异常"
        }

        def result2 = -1
        try{
            test2(null)
        } catch (NullPointerException | ArithmeticException e) {
            // 支持同时捕获多种类型异常
            assert e.getMessage() == "num不能为null" || "num不能为负数"
        } finally {
            result2 = 996
        }
        assert result2 == 996

        /*********************** 支持资源自动管理ARM机制, 即TWR语法 ***********************/
        try ( FileReader file = new FileReader("src/main/resources/ReadMe.txt") ) {
            file.each {line -> println line}
        }catch(e) {
            println ("Happen Exception: ${e.getMessage()}")
        }
    }

    // Groovy直接抛出CheckedException受查异常, 而在方法签名处的抛出异常声明则是可选的
    // 故推荐在方法签名处显式添加声明: static def test1 (String fileName) throws FileNotFoundException
    static def test1 (String fileName) {
        if( fileName == "Groovy In Action" ) {
            throw new FileNotFoundException("文件不存在异常")
        }
        return fileName.toUpperCase()
    }

    static int test2 (def num) {
        if( num == null ) {
            // 类似地, 直接抛出RuntimeException
            throw new NullPointerException("num不能为null")
        } else if( num<0 ) {
            // 类似地, 直接抛出RuntimeException
            throw new ArithmeticException("num不能为负数")
        }
        return num
    }
}
