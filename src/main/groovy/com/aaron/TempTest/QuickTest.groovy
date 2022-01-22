package com.aaron.TempTest

/**
 * @author Aaron Zhu
 * @date 2022-01-22
 */
class QuickTest {
    static void main(String[] args){
        Plane plane = new Plane()

        assert plane instanceof GroovyObject
        assert plane instanceof Object
        assert plane instanceof Plane

    }
}

class Plane {
    String name
    String type;
}
