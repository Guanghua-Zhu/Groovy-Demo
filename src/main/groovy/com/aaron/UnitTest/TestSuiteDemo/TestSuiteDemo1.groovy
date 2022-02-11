package com.aaron.UnitTest.TestSuiteDemo

import groovy.test.GroovyTestSuite
import junit.framework.TestSuite
import junit.textui.TestRunner

// Groovy 测试套件示例1
// Aaron: todo: output 2 blog

def testSuite = new TestSuite()
def groovyTestSuite = new GroovyTestSuite()

// 向测试套件中分别添加各单元测试类
testSuite.addTestSuite( groovyTestSuite.compile("IntegerTest.groovy") )
testSuite.addTestSuite( groovyTestSuite.compile("StringTest.groovy") )

// 执行测试套件
TestRunner.run( testSuite )