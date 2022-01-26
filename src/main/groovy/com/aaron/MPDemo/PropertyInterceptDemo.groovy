package com.aaron.MPDemo

/**
 * Groovy MP元编程示例: 属性拦截
 * @author Aaron Zhu
 * @date 2022-01-23
 */
class PropertyInterceptDemo {
    static void main(String[] args){
        testType()
        testProperty()
    }

    static void testType() {
        Elephant elephant = new Elephant()
        assert elephant instanceof Elephant
        // Groovy类的超类均是Java的Object类
        assert elephant instanceof Object
        // Groovy类均隐式实现了Groovy的GroovyObject接口
        assert elephant instanceof GroovyObject
    }

    /**
     * 重写GroovyObject接口的getProperty、setProperty默认方法
     * 实现对属性访问、修改的自定义拦截
     */
    static void testProperty() {
        Elephant elephant = new Elephant()
        elephant.name = "Aaron"
        assert elephant.name == "Aaron"
        // 通过GroovyObject接口的默认方法访问、修改属性
        elephant.setProperty("name", "Bob")
        assert elephant.getProperty("name") == "Bob"

        Eagle eagle = new Eagle(name:"Tina")
        assert eagle.name == "Tina"
        // 重写GroovyObject接口的默认方法getProperty拦截对属性的访问
        assert eagle.age == 18
        assert eagle.getProperty("age") == 18

        // 重写GroovyObject接口的默认方法setProperty拦截对属性的修改
        eagle.remark = "二级保护动物"
        assert eagle.remark == "Note: 二级保护动物"
        eagle.setProperty("remark", "濒危动物")
        assert eagle.remark == "Note: 濒危动物"
    }
}

class Elephant {
    String name
}

/**
 * 重写GroovyObject接口的默认方法: 实现对属性访问、修改的拦截
 */
class Eagle {
    String name
    String remark

    /**
     * 重写GroovyObject接口的默认方法getProperty
     * @param propertyName
     * @return
     */
    def getProperty(String propertyName) {
        if( propertyName == "age" ) {
            return 18
        }
        // 通过metaClass获取属性值
        // 即利用GroovyObject接口getProperty默认方法的逻辑实现属性访问
        // Note: 不可通过 this."${propertyName}" 方式获取属性
        // 因为其会继续被重写后的getProperty方法拦截, 从而陷入无限递归当中
        return metaClass.getProperty(this, propertyName)
    }

    /**
     * 重写GroovyObject接口的默认方法setProperty
     * @param propertyName
     * @param newValue
     */
    void setProperty(String propertyName, Object newValue) {
        if( propertyName == "remark" ) {
            metaClass.setProperty(this, propertyName, "Note: ${newValue}")
        } else {
            metaClass.setProperty(this, propertyName, newValue )
        }
    }

}
