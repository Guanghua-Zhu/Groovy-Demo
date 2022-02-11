package com.aaron.UnitTest.TestSuiteDemo

import groovy.test.GroovyTestCase

/**
 * Groovy 单元测试类
 * @author Aaron Zhu
 * @date 2022-02-10
 */
class IntegerTest extends GroovyTestCase {
    void test1() {
        assertSame(1,1)
    }

    void test2() {
        assertTrue( 200>1 )
    }

    void test3() {
        assertNotNull( 996 )
    }
}
