package com.aaron.UnitTest.TestSuiteDemo

import groovy.test.GroovyTestCase

/**
 * Groovy 单元测试类
 * @author Aaron Zhu
 * @date 2022-02-10
 */
class StringTest extends GroovyTestCase {
    void test1() {
        def name = "Aaron"
        assertSame( name, name )
    }

    void test2() {
        assertTrue( "Aaron".length()>1 )
    }

    void test3() {
        assertNotNull( "Aaron" )
    }
}
