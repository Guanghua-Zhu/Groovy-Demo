package com.aaron.BasicDemo

import org.codehaus.groovy.runtime.MethodClosure
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Collectors

/**
 * Groovy 操作符示例
 */
// Aaron: todo: output 2 blog
class OperatorDemo {

    static void main(String[] args){
        arithmetic()
        relational()
        logical()
        bit()
        conditional()
        object()
        regular()
        other()
    }

    /**
     * 算术操作符
     */
    static void arithmetic() {
        // 加
        assert 3+4 == 7
        // 加并赋值
        def foo1 = 3
        foo1 += 4
        assert foo1 == 7

        // 减
        assert 4-3 == 1
        // 减并赋值
        def foo2 = 4
        foo2 -= 3
        assert foo2 == 1

        // 乘
        assert 4*3 == 12
        // 乘并赋值
        def foo3 = 4
        foo3 *=3
        assert foo3 == 12

        // 除
        assert 12/3 == 4
        // 除并赋值
        def foo4 = 12
        foo4 /= 3
        assert foo4 ==4

        // 求余
        assert 13%3 == 1
        // 求余并赋值
        def foo5 = 13
        foo5 %= 3
        assert foo5 == 1

        // 幂
        assert 2**3 == 8
        // 幂并赋值
        def foo6 = 2
        foo6 **= 3
        assert foo6 == 8

        // 一元运算符 + 表示 正数
        assert +3 == 3
        // 一元运算符 - 表示 负数
        assert -4 == 0-4
        assert -(-11)  == 11

        // 后缀自增
        def a1 = 2
        def b1 = a1++
        assert a1 == 3
        assert b1 == 2

        // 前缀自增
        def a2 = 2
        def b2 = ++a2
        assert a2 == 3
        assert b2 == 3

        // 后缀自减
        def a3 = 2
        def b3 = a3--
        assert a3 == 1
        assert b3 == 2

        // 前缀自减
        def a4 = 2
        def b4 = --a4
        assert a4 == 1
        assert b4 == 1
    }

    /**
     * 关系运算符
     */
    static void relational() {
        // 相等
        assert (3*4) == (10+2)
        // 不相等
        assert 3 != 4
        // 小于
        assert 3 < 4
        // 小于等于
        assert 3<=4
        assert 4<=4
        // 大于
        assert 5 > 4
        // 大于等于
        assert 5>=4
        assert 5>=5
    }

    /**
     * 逻辑运算符
     */
    static void logical() {
        // 逻辑与, 支持短路求值
        assert true && true
        // 逻辑或, 支持短路求值
        assert false || true
        // 逻辑非
        assert !false
    }

    /**
     * 位运算
     */
    static void bit() {
        // 按位与
        int a = 0b1010  // a = 10
        assert a == 10
        int b = 0b0110  // b = 6
        assert b == 6
        int c = 0b0010  // c = 2
        assert c ==2
        assert (a&b) == c

        // 按位或
        int d = 0b1110  // d = 14
        assert d == 14
        assert (a|b) == d

        // 按位异或
        int e = 0b1100
        assert e == 12
        assert (a^b) == e

        // 按位取反
        byte f = 0b00001111
        assert f == 15
        byte h = 0b11110000
        assert h == -16
        assert (~f) == h

        // 左移
        byte i = 0b00000011
        assert i == 3
        byte j = 0b00001100
        assert j == 12
        assert (i<<2) == j

        // 右移
        assert (j>>2) == i
        // 右移: 左边使用原符号位进行填充, 右边超出部分直接丢弃
        byte k = 0b11111011
        assert k == -5
        byte p = 0b11111110
        assert p == -2
        assert (k>>2) == p

        // 无符号右移: 左边使用0进行填充, 右边超出部分直接丢弃
        int q = 0x8022_11ff // 数字支持使用下划线进行划分, 便于人眼查看
        assert q == -2145250817
        int r = 0x0080_2211
        assert r == 8397329
        assert (q>>>8) == r
    }

    /**
     * 条件运算符
     */
    static void conditional() {
        // 三元运算符
        def a1 = (2>1) ? 3 : 4
        assert a1 == 3
        def a2 = (2>3) ? 3 : 4
        assert a2 == 4

        // Elvis 运算符, 如果?:运算符左边的操作数判定为真，则返回左边操作数; 否则返回右边的操作数
        // 可以视为简版的三元运算符
        def b1 = "Hello World"
        //  等同于 b1 = (b1!=null || b1!="") ? b1 : "Hi"
        b1 = b1 ?: "Hi" // 非空字符串视为真
        assert b1 == "Hello World"
        // Elvis 运算符
        def b2 = ""
        b2 = b2 ?: "Hi"
        assert b2 == "Hi"

        // Elvis赋值 运算符, 其是对Elvis运算符的进一步简化, 省去了再次赋值操作
        def c1 = "Hello World"
        c1 ?= "Hi"
        assert c1 == "Hello World"

        def c2 = ""
        c2 ?= "Hi"
        assert c2 == "Hi"
    }

    /**
     * 对象操作符
     */
    static void object() {
        def person1 = new Person("remark": "领军人才")
        // 通过.操作符访问字段
        person1.name = "Aaron"
        assert person1.name == "Aaron"
        // 通过.操作符修改字段实际上是隐式调用setter方法
        person1.age = 18
        assert person1.age == 218
        // 通过.操作符获取字段实际上是隐式调用getter方法
        assert person1.remark == "<REMARK INFO>: 领军人才"

        // 通过.@运算符可以实现直接访问字段, 而不是通过隐式调用getter、setter方法实现
        assert person1.@remark == "领军人才"
        person1.@age = 17
        assert person1.age == 17

        // 安全引用操作符
        Person person2 = null
        // 如果?.安全引用操作符的引用为null, 则不会调用方法, 而是直接返回null以避免NPE
        assert person2?.getAge() == null
        assert person1?.getAge() == 17

        // 方法指针运算符
        MethodClosure fun1 = person1.&getAge
        // 方法指针的类型是闭包
        assert fun1 instanceof Closure
        assert fun1() == 17
        assert fun1.call() == 17

        // 方法指针同样支持多分派
        def fun3 = person1.&test1
        assert fun3.call("Bye") == "Aaron: Bye"
        assert fun3.call(3) == "<17 + 3> -->> 20"

        // 可通过new获取构造器的方法指针
        def fun4 = Person.&new
        Person person3 = fun4.call("name":"Bob")
        assert person3.name == "Bob"

        // 通过类先获取非静态方法的方法指针
        def fun2 = String.&toUpperCase
        // 在执行闭包时, 再传入该类的实例, 以作为方法的调用者
        assert fun2.call("Hello") == "HELLO"
        def fun5 = Person.&test1
        assert fun5.call( person1, "welcome" ) == "Aaron: welcome"

        // 方法指针运算符同样适用于静态方法
        def fun6 = String.&valueOf
        assert fun6.call( false ) == "false"
        assert fun6.call( 996 ) == "996"

        // Groovy对Java 8的::方法引用运算符保持支持兼容
        def list1 = ["71","2","4"].stream()
            .map( Integer::valueOf )
            .collect( Collectors.toList() )
        assert list1 == [71,2,4]

        // list1a, list1b 引用地址相同
        def list1a = [1,2] as LinkedList
        def list1b = list1a
        // 另外一个包含相同元素的列表
        def list2 = [1,2] as LinkedList

        // 判断两个引用的内容是否相同
        // == 运算符所对应的方法是equals
        assert list1a == list2
        assert list1a.equals( list2 )


        // 类似地, !=运算符是对equals方法的结果进行否定
        assert list1a != [985,211]
        assert !( list1a.equals([985,211]) )

        // 判断两个引用的地址是否相同
        // ===运算符对应is方法
        assert list1a === list1a
        assert list1a.is(list1b)
        // 判断两个对象的引用地址是否不同
        // 类似地, !==运算符是对is方法的结果进行否定
        assert list1a !== list2
        assert !( list1a.is(list2) )
    }

    /**
     * 正则操作符
     */
    static void regular() {
        String regex = /\S+\s+\S+/
        // 模式操作符 ~
        def pattern1 = ~regex
        assert pattern1 instanceof Pattern
        def text1 = "One Two Three Four Five"
        def matcher1 = pattern1.matcher(text1)
        assert matcher1 instanceof Matcher
        assert matcher1.size() == 2
        assert matcher1[0] == "One Two"
        assert matcher1[1] == "Three Four"

        // 查找运算符 =~
        // 具体地，其会在文本text1上应用正则表达式regex, 生成matcher
        def matcher2 = (text1 =~ regex)
        assert matcher2 instanceof Matcher
        assert matcher2.size() == 2
        assert matcher2[0] == "One Two"
        assert matcher2[1] == "Three Four"

        // 匹配运算符 ==~, 其是完整的匹配, 而非部分匹配
        boolean b1 = "One Two Three Four" ==~ regex
        boolean b2 = "One Two" ==~ regex
        assert b1 == false
        assert b2 == true
    }

    static void other() {
        // 飞船运算符, 通过调用Comparable接口的compareTo方法进行比较
        // 15==15
        assert (15 <=> 15) == 0
        // 44>22
        assert (44 <=> 22) == 1
        // 22<44
        assert (22 <=> 44) == -1

        // 安全索引运算符?[], 作用类似于?.安全引用操作符
        // 避免由于数组、Map等为null而导致的NPE
        String[] array1 = ["Amy", "Aaron"]
        array1?[1] = "Bob"
        assert array1?[0] == "Amy"
        assert array1?[1] == "Bob"
        array1 = null
        // array1为null, 将不会应用索而是直接返回null
        assert array1?[0] == null

        // 安全索引运算符同样适用于map
        def map1 = [:]
        map1?["Aaron"] = 18
        assert map1?["Aaron"] == 18
        map1 = null
        assert map1?["Aaron"] == null

        // 成员操作符
        def list1 = ["MicroSoft", "Apple", "Xiaomi", "FaceBook"]
        // 判定Apple是否是list1的成员
        assert "Apple" in list1
        // 等效于调用isCase方法
        assert list1.isCase( "Apple" )
        assert !("Huawei" in list1)

        assert 996 in Integer
        // 等效于调用isCase方法
        assert Integer.isCase(996)
        assert !(3.14f in Integer)

        assert 3.14f in Float
        assert Float.isCase( 3.1f )
    }
}

class Person {
    String name
    Integer age
    String remark

    void setAge(Integer age) {
        this.age = 200 + age
    }

    String getRemark() {
        return "<REMARK INFO>: $remark"
    }

    /**
     * 实现equals方法, 实现重载==运算符
     * @param other
     * @return
     */
    @Override
    boolean equals(Object other) {
        if (!other
            || !(other instanceof Person)
            || name != other.name
            || age != other.age
            || remark != other.remark ) {
            return  false
        }
        return true
    }

    String test1(String msg) {
        return "$name: $msg"
    }

    String test1(Integer num) {
        return "<${this.age} + ${num}> -->> ${this.age+num}"
    }

}
