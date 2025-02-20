package com.example.quizapp

data class Question(
    val text: String,
    val variants: List<String>,
    val correctAnswer: String,
)

class Questions {

    private val questionBank = listOf(
        Question("Что измеряет метрика F1-score?", listOf("Среднее точности и полноты", "Точность", "Полноту", "Количество ошибок"), "Среднее точности и полноты"),
        Question("Какая структура данных работает по принципу FIFO?", listOf("Стек", "Очередь", "Дек", "Хеш-таблица"), "Очередь"),
        Question("Какой алгоритм используется для сжатия изображений в формате JPEG?", listOf("LZW", "RLE", "DCT", "Huffman Coding"), "DCT"),
        Question("Какой язык чаще всего используется в анализе данных?", listOf("C++", "Java", "Python", "PHP"), "Python"),
        Question("Какой SQL-запрос выбирает уникальные значения?", listOf("SELECT ALL", "SELECT UNIQUE", "SELECT DISTINCT", "SELECT DIFFERENT"), "SELECT DISTINCT"),
        Question("Как называется процесс подготовки данных для анализа?", listOf("Кластеризация", "Нормализация", "Предобработка данных", "Регрессия"), "Предобработка данных"),
        Question("Какой протокол используется для защищенной передачи данных?", listOf("HTTP", "FTP", "HTTPS", "SMTP"), "HTTPS"),
        Question("Какова сложность алгоритма быстрой сортировки в среднем случае?", listOf("O(n)", "O(n log n)", "O(n²)", "O(log n)"), "O(n log n)"),
        Question("Какая функция отображает графики в Matplotlib?", listOf("draw()", "plot()", "show()", "graph()"), "show()"),
        Question("Какая концепция ООП скрывает детали реализации?", listOf("Инкапсуляция", "Полиморфизм", "Наследование", "Композиция"), "Инкапсуляция")
    )

    private var currentQuestion: Question? = null
    private var askedQuestions = mutableSetOf<Question>()

    fun getNextQuestion(): Question? {
        val availableQuestions = questionBank.filter { it !in askedQuestions }
        if (availableQuestions.isEmpty()) return null

        return availableQuestions.random().also { currentQuestion = it; askedQuestions.add(it) }
    }

    fun checkAnswer(userAnswer: String?): Boolean {
        return currentQuestion?.let {
            userAnswer == it.correctAnswer
        } ?: false
    }
}
