/*
    Основы программирования на Kotlin
*/

// Переменные
var variable_1 = 1 // Объявление изменяемой переменной (тип данных определяется значением)
val variable_2 = 2 // Объявление неизменяемой переменной (константа)
var variable_3 = 2.2
var variable_str: String = "Hello" // Объявление переменной с указанием конкретного типа данных

// Типы данных
// Числовые типы данных
var variable_int: Int = 2147483647 // Целочисленный тип данных (со знаком + или -), занимает 4 байта (32 бита)
var variable_long: Long = 9223372036854775807 // Целочисленный тип данных (со знаком + или -), занимает 8 байт (64 бит)
var variable_short: Short = 32767 // Целочисленный тип данных (со знаком + или -), занимает 2 байта (16 бита)
var variable_byte: Byte = 127 // Целочисленный тип данных (со знаком + или -), занимает 1 байт (8 бит)

var variable_uint: UInt = 4294967295U // Целочисленный тип данных (без знака), занимает 4 байта (32 бита)
var variable_ulong: ULong = 18446744073709551615U // Целочисленный тип данных (без знака), занимает 8 байт (64 бит)
var variable_ushort: UShort = 65535U // Целочисленный тип данных (без знака), занимает 2 байта (16 бита)
var variable_ubyte: UByte = 255U // Целочисленный тип данных (без знака), занимает 1 байт (8 бит)

var variable_float: Float = 2.5555556f // Тип данных с плавающей точкой (со знаком), занимает 4 байта (32 бита)
var variable_double: Double = 2.5555555555555554 // Тип данных с плавающей точкой (со знаком), занимает 8 байт (64 бита)

// По умолчанию при объявлении переменных с числовым значением без указания типа данных определяется тип Int. Для чисел с плавающей точкой без указания типа определяется тип Double.

// Символьный тип данных
var variable_char1: Char = 'a'
var variable_char2: Char = '\n'
var variable_char3: Char = '\u0061'

// Строчный тип данных
var variable_string1: String = "Kotlin"
var variable_string_special: String = "Hello\tKotlin!"
var variable_string2: String = "I'm Steve"
var variable_string3: String = "Магазин \"Подсолнух\""
var variable_string4: String = """Духовной жаждою томим,
В пустыне мрачной я влачился, —
И шестикрылый серафим
На перепутье мне явился."""

// Логический тип данных
var variable_true: Boolean = true
var variable_false: Boolean = false

fun basic(){
    print("Hello Kotlin!") // Вывод данных в консоль
    print("Have a nice day!")
    println("Good job!") // Вывод данных в консоль, последующие данные выведутся с новой строки
    println("Goodbye!")

    println(variable_1)
    variable_1 = 10
    println(variable_1)
    println(variable_2)

    println(variable_int)

    println(variable_char1)
    println(variable_char2)
    println(variable_char3)

    println(variable_string_special)
    println(variable_string3)
    println(variable_string4)
}

// Вывод и ввод данных
fun inputOutput(){
    // $ - используется для вставки внешних значений в строку
    var name: String = "Андрей"
    var age: Int = 27
    println("Имя: $name, Возраст: $age") // шаблон строки для вывода значений из переменных

    print("Введите логин: ")
    var login = readln() // ввод данных через консоль
    println("Вы вошли под именем $login")
}

// Арифметические операции
fun arithmetic(){
    var x = 5
    var y = 2
    var z = 0

    z = x + y // + (сложение): возвращает сумму двух чисел
    println(z)

    z = x - y // - (вычитание): возвращает разность двух чисел
    println(z)

    z = x * y // * (умножение): возвращает произведение двух чисел
    println(z)

    z = x / y // / (деление): возвращает частное двух чисел. Результат будет целым, если исходные числа - целые
    println(z)

    var v = 5.0
    var w = 2.0
    var u: Any // Принимает любой изначальный тип данных

    u = v / w // / (деление): возвращает частное двух чисел. Результат будет дробным, если хотя бы одно изначальное число - дробное
    println(u)

    z = x % y // % (деление с остатком): возвращает остаток от целочисленного деления двух чисел
    println(z)

    var a = 10
    a++ // Инкремент - увеличивает значение переменной на единицу
    println(a)
    a-- // Декремент - уменьшает значение переменной на единицу
    println(a)

    var b = 0
    var c = 0
    println("b = $b, c = $c")
    println("b = ${b++}, c = ${c++}") // ++ справа возвращает значения до увеличения переменной
    println("b = ${++b}, c = ${++c}") // ++ слева возвращает значения после увеличения переменной
    println("b = $b, c = $c")

    // Присваивание с математической операцией
    x = 5
    y = 6
    x += y // аналогично x = x + 1
    println(x)
    x -= y // аналогично x = x - 1
    println(x)
    x *= y // аналогично x = x + 1
    println(x)
    x /= y // аналогично x = x + 1
    println(x)

    a = readln().toInt()
    b = readln().toInt()
    println("$a + $b = ${a + b}")
}

// Условные выражения. Условные операторы.
fun conditionals(){
    // Операторы сравнения
    val var_a = 11
    val var_b = 12
    // > (больше чем): возвращает true, если первое значение больше второго, иначе false
    println(var_a > var_b) // false
    println(32 > 12) // true

    // < (меньше чем): возвращает true, если первое значение меньше второго, иначе false
    println(var_a < var_b) // true
    println(32 < 12) // false

    // >= (больше чем или равно): возвращает true, если первое значение больше или равно второму, иначе false
    println(var_a >= var_b) // false
    println(32 >= 32) // true

    // <= (меньше чем или равно): возвращает true, если первое значение меньше или равно второму, иначе false
    println(var_a <= var_b) // false
    println(33 <= 32) // true

    // == (равно): возвращает true, если оба значения равны между собой, иначе false
    println(var_a == var_b)
    println(32 == 32)

    // != (не равно): возвращает true, если значения не равны между собой, иначе false
    println(var_a != var_b)
    println(32 != 32)

    println("__________________________________________")
    // Логические операторы
    var a = 11 < 12
    var b = 32 >= 22
    // and (и): возвращает true, если оба значения равны true
    println(a and b) // true
    println((2 > 3) and (3 > 2)) // false

    // or (или): возвращает true, если хотя бы одно из значений равно true
    a = 11 > 12
    b = 32 > 22
    println(a or b) // true
    println((32 > 34) or (12 > 31)) // false
    println((11 < 12) or (32 > 22)) // true

    // ! (не): возвращает true, если изначальное значение равно false и возвращает false, если изначальное равно true
    a = 11 < 12
    println(!a)
    b = 12 < 11
    println(!b)

    // xor (исключающее или): возвращает true, если только одно из значений равно true, иначе false
    a = 11 > 12
    b = 32 > 22
    println(a xor b) // true
    println((11 > 12) xor (32 < 22)) // false
    println((11 < 12) xor (32 > 22)) // false
}

// Функция main() - точка запуска программы
fun main(){
    basic()
    inputOutput()
    conditionals()

    // Функции преобразования значений
    var x = readln().toInt() // Перевод значения в тип данных Int
    println(x + 1)

    var y = readln().toFloat() // Перевод значения в тип данных Float
    println(y)

    var z = 23
    var str_z = z.toString() // Перевод значения в тип данных String
    println(str_z)
}