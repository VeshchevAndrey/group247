/* Функции в Kotlin */

// fun - оператор объявления (создания) функции
// Пример объявления функции
fun welcome() {
    print("Введите своё имя: ")
    val name = readln()
    println("С возвращением, уважаемый $name!")
}

// Функция с параметрами
fun new_user(name: String, age: Byte){
    println("Добавлен новый пользователь.\nИмя: $name, возраст: $age.")
}

// Функция с необязательными параметрами
fun employee(name: String, surname: String, age: Byte, education: String = "Middle"){
    println("Employee data: Name: $surname $name, age: $age, education: $education")
}

// Функция с необязательными параметрами в разных местах
fun student(name: String = "No name", surname: String, age: Byte = 18, hometown: String){
    println("Student data: Name: $surname $name, age: $age, hometown: $hometown")
}


// Функция с возвращением результата.
// return - возвращение результата из функции
fun sum(x: Int, y: Int): Int{
    return x + y
}

// Описать функцию sign(x) целого типа, возвращающую для вещественного числа X следующие значения: –1, если x < 0; 0, если x = 0; 1, если x > 0. С помощью этой функции найти значение выражения Sign(A) + Sign(B) для данных вещественных чисел A и B.
fun sign(x: Double): Int {
    if (x < 0) return -1
    else if (x > 0) return 1
    else return 0
}

// Функция с переменным количеством параметров
fun students(group: String, vararg students: String){
    println("Название группы: $group\nУчащиеся:")
    for (i in students) println(i)
}

// Функция расчёта среднего значения множества дробных (вещественных) чисел
fun mean(vararg numbers: Double): Double{
    var sum = 0.0
    for (i in numbers) sum += i
    return sum / numbers.size
}

// Функция с переменным параметром и стандартными параметрами
fun country(name: String, vararg bigCity: String, capital: String){
    println("Страна: $name")
    print("Крупные города: ")
    for (i in bigCity) print("$i, ")
    println()
    println("Столица: $capital")
}

fun square(vararg numbers: Int){
    for (i in numbers) println(i * i)
}

// Однострочная функция (тело функции состоит из одного выражения)
fun singleSquare(number: Int) = number * number

// Анонимная функция
val anonymous = fun (x: Int, y: Int): Int{
    return x * y
}

// Лямбда-выражение
val number = {num: Int -> println(singleSquare(num))}

fun main(){
    // welcome() // вызов функции
    new_user("Мария", 20) // передача обязательных параметров
    employee("John", "Smith", 30, "high")
    employee("Jane", "Doe", 20)
    student(name = "John", "Doe", age = 19, "Boston")
    student(
        hometown = "New York",
        age = 19,
        surname = "Black",
        name = "Jack"
    )
    val z = sum(2, 3) // вызов функции с возвратом значения
    println(z)
    println(sum(4, 7))
    println(sign(2.4))
    println(sign(0.0))
    println(sign(-1.4))
    println(sign(2.4) + sign(-4.2))
    students("СУ-11", "Иванов", "Сергеев", "Александров", "Николаев")
    val arithMean = mean(22.0, 74.0, 16.0)
    println(arithMean)
    country("Россия", "Санкт-Петербург", "Казань", "Екатеринбург", "Новосибирск", capital = "Москва")
    val arrayNumbers = intArrayOf(4, 7, 16, 42)
    square(*arrayNumbers) // * - указание распаковки массива
    val x = singleSquare(256)
    println(x)
    val result = anonymous(5, 8)
    println(result)
    number(6)
}
