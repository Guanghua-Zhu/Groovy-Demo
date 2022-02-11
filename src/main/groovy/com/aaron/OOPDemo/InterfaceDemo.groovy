package com.aaron.OOPDemo

/**
 * Groovy 接口的示例
 */
// Aaron: todo: output 2 blog
class InterfaceDemo {
    static void main(String[] args){
        Speak dog = new Dog()
        assert dog.sayHello("Aaron") == "[Dog]: I'm Aaron"
        assert dog.sayBye() == "[Dog]: Bye～"
        assert dog.getType() == 3.0

        Speak cat = new Cat()
        assert cat.sayHello("Bob") == "[Cat]: My Name is Bob"
        assert cat.sayBye() == "[Cat]: Bye..."
        assert cat.getType() == 3.0
    }
}

/**
 * 定义接口
 */
interface Speak {
    // 接口方法只能是public(可省略),不可以使用protected、private
    String sayHello(String name)

    String sayBye()

    // Groovy接口同样支持默认方法
    default double getType() {
        return 3.0d
    }
}

/**
 * 实现类
 */
class Dog implements Speak{

    @Override
    String sayHello(String name) {
        return "[Dog]: I'm $name"
    }

    @Override
    String sayBye() {
        return "[Dog]: Bye～"
    }
}

/**
 * 实现类
 */
class Cat implements Speak{

    @Override
    String sayHello(String name) {
        return "[Cat]: My Name is $name"
    }

    @Override
    String sayBye() {
        return "[Cat]: Bye..."
    }
}