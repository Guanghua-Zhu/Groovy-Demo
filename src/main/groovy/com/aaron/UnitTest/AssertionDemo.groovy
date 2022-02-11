package com.aaron.UnitTest

import com.aaron.Flower
import groovy.test.GroovyTestCase

/**
 * Groovy 单元测试的断言Assertion示例
 * @author Aaron Zhu
 * @date 2022-02-09
 */
class AssertionDemo extends GroovyTestCase{

    /**
     * assertLength: 断言数组长度
     */
    void test1() {
        int[] nums = [1,3,5,7]
        assertLength(4, nums)

        char[] alphabet = ["T", "C", "Z"]
        assertLength(3, alphabet)

        def array = [996, "Aaron"] as Object[]
        assertLength(2, array)
    }

    /**
     * assertArrayEquals: 断言数组长度、内容完全一致
     */
    void test2() {
        Object[] nums1 = [1,3]
        Object[] nums2 = [1,3]
        assertArrayEquals(nums1 ,nums2)

        Flower[] flowers1 = [ new Flower("牡丹"), new Flower("茉莉") ]
        Flower[] flowers2 = [ new Flower("牡丹"), new Flower("茉莉") ]
        assertArrayEquals( flowers1, flowers2 )
    }

    /**
     * assertEquals: 断言内容是否相等
     * assertNotSame: 断言两个对象的地址是否不同
     * assertSame: 断言两个对象的地址是否相同
     */
    void test3() {
        // 校验result变量是否为期待值
        String result = "Aaron" + "." + "Zhu"
        String expected = "Aaron.Zhu"
        assertEquals(expected, result)

        // 校验浮点数num1、num2、num3是否为期待值
        double num1 = 100.01
        double num2 = 100.1
        double num3 = 99.9
        double expectedNum = 100
        // 浮点数比较需要设置阈值
        double delta = 0.1
        assertEquals(expectedNum, num1, delta)
        assertEquals(expectedNum, num2, delta)
        assertEquals(expectedNum, num3, delta)

        String errorMsg = "浮点数不相等"
        // 支持设置断言失败的信息
        assertEquals(errorMsg, expectedNum, num3, delta)

        Flower flower = new Flower("牵牛花")
        Flower expectedObj = new Flower("牵牛花")
        // 断言两个对象的内容是否相等
        assertEquals( expectedObj, flower )
        // 断言两个对象的地址是否不同
        assertNotSame( expectedObj, flower )
        // 断言两个对象的地址是否相同
        assertSame( flower, flower )
    }

    /**
     * assertToString: 断言对象调用toString方法的结果
     * assertInspect: 断言对象调用inspect方法的结果
     */
    void test4() {
        Flower flower = new Flower(type: "向阳花")

        String expected = "This is a 向阳花 Flower"
        assertToString(flower, expected)

        expected = "[Info]: 向阳花"
        assertInspect(flower, expected)
    }

    /**
     * shouldFail: 断言闭包执行失败, 并抛出指定或任意类型的异常
     * shouldFailWithCause: 断言闭包执行失败, 且内部嵌套异常为指定类型异常
     */
    void test5() {
        // 断言闭包执行失败, 并抛出指定类型的异常
        def msg1 = shouldFail(NullPointerException) {
            new HashMap(null)
        }
        def msg2 = shouldFail(IllegalArgumentException) {
            new HashMap(-1)
        }

        // 断言闭包执行失败, 并抛出任意类型的异常
        def msg3 = shouldFail {
            new HashSet(-1)
        }

        // 断言闭包执行失败, 且getCause方法获取的内部嵌套的异常为指定类型异常
        Flower flower = new Flower()
        def msg4 = shouldFailWithCause(FileNotFoundException) {
            flower.calcException(1)
        }
        def msg5 = shouldFailWithCause(NullPointerException) {
            flower.calcException(2)
        }
    }

    /**
     * assertTrue: 断言表达式为真
     * assertFalse: 断言表达式为假
     * assertNull: 断言对象为null
     * assertNotNull: 断言对象不为null
     */
    void test6() {
        assertTrue( 200>1 )
        assertFalse( 1>200 )

        Flower flower = null
        assertNull( flower )
        flower = new Flower()
        assertNotNull( flower )
    }

}


