package com.aaron.ScriptDemo

println("Hello, This is a Groovy Script")

// 调用方法
assert sum(1,2) == 3

// 定义变量使用类型、def进行修饰, 则该变量在脚本中的其他方法内是不可见的
int x2 = 10
def y2 = 11
def z2 = sum(x2,y2)
assert z2==21
println( "z2: $z2")

// 定义方法
int sum(int a, int b) {
    int c = a + b
    return c
}

// 定义变量时不使用类型、def修饰, 则该变量脚本中的其他方法是可见的
// 即可以直接printNum方法体中使用该变量
num =1

// 调用方法
printNum()

// 定义方法
def printNum() {
    assert num == 1
    println ("[printNum]-num: $num")
}
