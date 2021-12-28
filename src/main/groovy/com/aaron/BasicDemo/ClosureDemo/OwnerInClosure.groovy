package com.aaron.BasicDemo.ClosureDemo

/**
 * Groovy 闭包 示例: 闭包中的owner
 */
class OwnerInClosure {
    static void main(String[] args){
        Person person = new Person("Bob", 25)

        /*************** 闭包定义在类中 ***************/
        Closure closure1 = person.test1()
        // 闭包中的owner 指的是 闭包定义处所在类的相应实例
        assert closure1.call() == person

        Closure closure2 = person.test2()
        // 闭包中的getOwner()方法 作用于owner类似
        assert closure2.call() == person

        /*************** 闭包定义在内部类中 ***************/
        Person.Inner inner = person.getInnerInstance()
        Closure closure3 = inner.test3()
        // 闭包如果定义在内部类中, 则 闭包中的owner 指的是 相应的内部类实例
        assert closure3.call() != person
        assert closure3.call() == inner

        /*************** 闭包嵌套定义在另一个闭包中 ***************/
        Closure closure4 = person.test4()
        // 嵌套定义的闭包, 则 嵌套闭包中的owner 指的是 嵌套闭包所在的外部闭包实例, 见test4方法内的(1)处
        assert closure4.call() != person
        assert closure4.call() == closure4
    }
}

class Person {
    String name

    int age

    Person(String name, int age) {
        this.name = name
        this.age = age
    }

    def test1() {
        // 闭包定义处
        def closure = { owner }
        return closure
    }

    def test2() {
        // 闭包中的 getOwner()方法 和 owner 是完全等价的, 后者是前者的快捷方式
        def closure = { getOwner() }
        return closure
    }

    def test4() {
        def closure = { ->
            // 嵌套定义一个新闭包nestedClosure, 并在该闭包中使用owner
            def nestedClosure = { -> owner }
            // 调用该嵌套闭包, 并将结果返回
            def result = nestedClosure.call()
            return result
        }

        // 嵌套闭包中的owner 指的是 嵌套闭包定义处的外部闭包实例
        assert closure() == closure   // (1)

        return closure
    }

    /**
     * 获取内部类实例
     * @return
     */
    def getInnerInstance() {
        return new Inner()
    }

    /**
     * 内部类
     */
    class Inner{
        def test3() {
            // 闭包定义处
            def closure = { owner }
            return closure
        }
    }
}