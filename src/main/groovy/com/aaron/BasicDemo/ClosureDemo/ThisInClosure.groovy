package com.aaron.BasicDemo.ClosureDemo


/**
 * Groovy 闭包 示例: 闭包中的this
 */
class ThisInClosure {
    static void main(String[] args) {
        Employee employee = new Employee("Aaron", 18);
        assert employee.getInfo() == "[Employee Info]: <name> : Aaron, <age>: 18"
        assert employee.getInfo2() == "[Employee Info]: <name> : Aaron, <age>: 18"

        /*************** 闭包定义在类中 ***************/
        Closure closure1 = employee.test1()
        // 闭包中的this 指的是 闭包定义处所在类的相应实例
        assert closure1.call() == employee
        // 闭包中的getThisObject()方法 作用于this类似
        Closure closure2 = employee.test2()
        assert closure2.call() == employee

        /*************** 闭包定义在内部类中 ***************/
        Employee.Inner inner = employee.getInnerInstance();
        Closure closure3 = inner.test3()
        // 闭包如果定义在内部类中, 则 闭包中的this 指的是 相应的内部类实例
        assert closure3.call() != employee
        assert closure3.call() == inner

        /*************** 闭包嵌套定义在另一个闭包中 ***************/
        Closure closure4 = employee.test4()
        // 嵌套定义的闭包, 则 嵌套闭包中的this 指的依然是 嵌套闭包所在类的相应实例
        assert closure4.call() == employee
    }
}

class Employee {
    String name

    int age

    Employee(String name, int age) {
        this.name = name
        this.age = age
    }

    String getInfo() {
        return "[Employee Info]: <name> : $name, <age>: $age"
    }

    String getInfo2() {
        // 同Java一样, Groovy this 表示 调用该方法的对象实例
        return this.getInfo()
    }

    def test1() {
        // 闭包定义处
        def closure = { this }
        return closure
    }

    def test2() {
        // 闭包中的 getThisObject()方法 和 this 是完全等价的, 后者是前者的快捷方式
        def closure = { getThisObject() }
        return closure
    }

    def test4() {
        def closure = { ->
            // 嵌套定义一个新闭包nestedClosure, 并在该闭包中使用this
            def nestedClosure = { -> this }
            // 调用该嵌套闭包, 并将结果返回
            def result = nestedClosure.call()
            return result
        }
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
            def closure = { this }
            return closure
        }
    }
}