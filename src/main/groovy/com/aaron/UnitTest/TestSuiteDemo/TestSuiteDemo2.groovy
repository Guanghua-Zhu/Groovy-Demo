package com.aaron.UnitTest.TestSuiteDemo

import groovy.test.AllTestSuite
import junit.textui.TestRunner

// Groovy 测试套件示例2

// 单元测试类的路径匹配表达式
String basedir = "."
// 单元测试类的文件名匹配表达式
String pattern = "*Test.groovy"
// 构建测试套件
def testSuite = AllTestSuite.suite(basedir, pattern)
// 执行测试套件
TestRunner.run( testSuite )
