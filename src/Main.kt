import kotlin.math.sqrt

//Треугольник расположен на координатной плоскости и описан координатами своих вершин.
//Написать программу вычисляющую координаты центра описанной вокруг треугольника окружности и ее радиус.





// Класс для представления точки
data class Point(val x: Double, val y: Double)

// Класс для представления треугольника
class Triangle(val a: Point, val b: Point, val c: Point) {

    // Метод для вычисления длины стороны треугольника
    private fun length(p1: Point, p2: Point): Double {
        return sqrt((p2.x - p1.x).pow(2) + (p2.y - p1.y).pow(2))
    }

    // Метод для вычисления периметра треугольника
    private fun perimeter(): Double {
        return length(a, b) + length(b, c) + length(c, a)
    }

    // Метод для вычисления площади треугольника по формуле Герона
    private fun area(): Double {
        val p = perimeter() / 2
        return sqrt(p * (p - length(a, b)) * (p - length(b, c)) * (p - length(c, a)))
    }

    // Метод для вычисления центра описанной окружности
    fun circumcenter(): Point {
        val D = 2 * (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y))
        val Ux = ((a.x * a.x + a.y * a.y) * (b.y - c.y) +
                (b.x * b.x + b.y * b.y) * (c.y - a.y) +
                (c.x * c.x + c.y * c.y) * (a.y - b.y)) / D
        val Uy = ((a.x * a.x + a.y * a.y) * (c.x - b.x) +
                (b.x * b.x + b.y * b.y) * (a.x - c.x) +
                (c.x * c.x + c.y * c.y) * (b.x - a.x)) / D

        return Point(Ux, Uy)
    }

    // Метод для вычисления радиуса описанной окружности
    fun circumradius(): Double {
        val aLength = length(a, b)
        val bLength = length(b, c)
        val cLength = length(c, a)
        val area = area()

        return (aLength * bLength * cLength) / (4 * area)
    }

    private fun Double.pow(exponent: Int): Double {
        return this.toPow(exponent)
    }

    private fun Double.toPow(exponent: Int): Double {
        var result = 1.0
        repeat(exponent) {
            result *= this
        }
        return result
    }
}

fun main() {
    // Пример использования
    val pointA = Point(0.0, 0.0)
    val pointB = Point(4.0, 0.0)
    val pointC = Point(2.0, 3.0)

    val triangle = Triangle(pointA, pointB, pointC)

    val circumcenter = triangle.circumcenter()
    val circumradius = triangle.circumradius()

    println("Центр описанной окружности: (${circumcenter.x}, ${circumcenter.y})")
    println("Радиус описанной окружности: $circumradius")
}
