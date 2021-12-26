package com.aaron.BasicDemo

/**
 * Groovy 数字 示例
 */
class NumberDemo {
    static void main(String[] args) {
        testType1()
        testType2()
        testMethod()
    }

    static void testType1() {
        // Groovy 中的数字类型均使用Java中相应包装类型
        byte b1 = 1
        assert b1 instanceof Byte
        Byte b2 = 2
        assert b2 instanceof Byte
        // 通过as运算符强制类型
        def b3 = 3 as byte
        assert b3 instanceof Byte
        def b4 = 4 as Byte
        assert b3 instanceof Byte

        short s1 = 1
        assert s1 instanceof Short
        Short s2 = 2
        assert s2 instanceof Short

        int i1 = 1
        assert i1 instanceof Integer
        Integer i2 = 2
        assert i2 instanceof Integer

        long l1 = 1
        assert l1 instanceof Long
        Long l2 = 2
        assert l2 instanceof Long

        float f1 = 1
        assert f1 instanceof Float
        Float f2 = 2
        assert f2 instanceof Float

        double d1 = 1
        assert d1 instanceof Double
        Double d2 = 2
        assert d2 instanceof Double

        // 类似地对于布尔类型, Groovy boolean 同样使用Java Boolean进行包装
        boolean b6 = true
        assert b6 instanceof Boolean
        Boolean b7 = false
        assert b7 instanceof Boolean
    }

    static void testType2() {
        // 整型默认类型
        def num1 = 15
        assert num1 instanceof Integer

        def num2 = 15l
        def num3 = 15L
        assert num2 instanceof Long
        assert num3 instanceof Long

        // Integer.MAX_VALUE + 1
        def num4 = 2147483648
        //  Groovy会自动选择合适的整数类型
        assert num4 instanceof Long

        // 整数添加g/G后缀，使用Java BigInteger类型
        def num5 = 15g
        def num6 = 15G
        assert num5 instanceof BigInteger
        assert num6 instanceof BigInteger

        // 对于浮点数, Groovy默认使用BigDecimal类型
        def num7 = 7.7
        def num8 = 8.8g
        def num9 = 9.9G
        assert num7 instanceof BigDecimal
        assert num8 instanceof BigDecimal
        assert num9 instanceof BigDecimal

        def num10 = 5.55f
        def num11 = 6.66F
        assert num10 instanceof Float
        assert num11 instanceof Float

        def num12 = 7.77d
        def num13 = 8.88D
        assert num12 instanceof Double
        assert num13 instanceof Double
    }

    static void testMethod() {
        def msg1 = ""
        def num1 = 4
        // 对闭包执行指定次数
        num1.times { msg1 += "A" }
        assert msg1 == "AAAA"
        def msg2 = ""
        num1.times { e -> msg2 += "B$e" }
        assert msg2 == "B0B1B2B3"

        def msg3 = ""
        // 从3到5依次执行闭包
        3.upto(5) { num -> msg3 += num }
        assert msg3 == "345"

        def msg4 = ""
        // 从5到-2依次执行闭包
        5.downto(-2) { num -> msg4 += num }
        assert msg4 == "543210-1-2"

        def msg5 = ""
        def num2 = 0.1f
        // 从0.1按0.2的步长增长到1.1, 但不包括1.1
        0.1.step(1.1, 0.2) { num -> msg5 += "H$num, " }
        assert msg5 == "H0.1, H0.3, H0.5, H0.7, H0.9, "
    }
}