package com.aaron.BasicDemo.ClosureDemo

/**
 * Groovy 闭包 示例: 闭包中的delegate
 */
class DelegateInClosure {
    static void main(String[] args){
        basic()
        modifyDelegate()
    }

    /**
     * delegate 基本作用
     */
    static void basic(){
        Staff staff = new Staff("Amy", 38)

        // 默认情况下, 闭包的 delegate 被设置为 owner
        // 故当一个闭包被定义在类、内部类、嵌套的闭包时
        // delegate所指向的对象与owner的行为完全一致

        Closure closure1 = staff.test1()
        assert closure1.call() == staff

        Closure closure2 = staff.test2()
        // 闭包中的getDelegate()方法 作用于delegate类似
        assert closure2.call() == staff
    }

    /**
     * 修改 delegate
     */
    static void modifyDelegate() {
        Staff staff1 = new Staff("Tony", 22)
        Animal animal1 = new Animal("Tom", "Cat")

        // 定义一个闭包
        def closure = { ->
            // 获取delegate所指向对象的name字段
            def nameStr = delegate.name
            // 将nameStr全部转换为大写
            nameStr.toUpperCase()
        }

        // 如前所述, 闭包的delegate属性默认指向其定义时所在类的相应实例
        // 由于这里闭包定义在静态方法中, 故其指向的是Class对象
        assert closure.delegate == DelegateInClosure.class

        // 修改闭包的delegate属性
        closure.delegate = staff1
        assert closure.call() == "TONY"

        // 修改闭包的delegate属性
        closure.delegate = animal1
        assert closure.call() == "TOM"

        // 如果不借助闭包的delegate属性, 而通过本地变量进行实现的话, 则可能是这样
        def temp = staff1
        def closure2 = { temp.name.toUpperCase() }
        assert closure2.call() == "TONY"
        temp = animal1
        assert closure2() == "TOM"
    }
}

class Staff {
    String name

    int age

    Staff(String name, int age) {
        this.name = name
        this.age = age
    }

    def test1() {
        // 闭包定义处
        def closure = { delegate }
        return closure
    }

    def test2() {
        // 闭包中的 getDelegate()方法 和 delegate 是完全等价的, 后者是前者的快捷方式
        def closure = { getDelegate() }
        return closure
    }
}

class Animal{
    String name

    String type

    Animal(String name, String type) {
        this.name = name
        this.type = type
    }
}