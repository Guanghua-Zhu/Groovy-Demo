package com.aaron.UnitTest

import com.aaron.HelloWorld
import groovy.test.GroovyTestCase
import junit.framework.TestCase
import org.junit.Test

/**
 * Groovy 单元测试的基本语法示例
 * @author Aaron Zhu
 * @date 2022-02-09
 */
// Aaron: todo: output 2 blog

class HelloWorldTest1 extends GroovyTestCase{
    //直接继承GroovyTestCase, 且方法名需要以test开头
    void testHello() {
        HelloWorld helloWorld = new HelloWorld("Aaron")
        String result = helloWorld.hello("Tony")
        assert result == "I'm Aaron, Hi Tony~"
    }
}

class HelloWorldTest2 extends TestCase {
    //直接继承TestCase, 且方法名需要以test开头
    void testHello() {
        HelloWorld helloWorld = new HelloWorld("Aaron")
        String result = helloWorld.hello("Tony")
        assert result == "I'm Aaron, Hi Tony~"
    }
}

class HelloWorldTest3 {
    //直接使用@Test注解, 方法名可随意
    @Test
    void helloTest() {
        HelloWorld helloWorld = new HelloWorld("Aaron")
        String result = helloWorld.hello("Tony")
        assert result == "I'm Aaron, Hi Tony~"
    }
}