package com.example.quizapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val questions = Questions()
    private var currentQuestion: Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load first question
        loadNextQuestion()

        // Button listeners
        binding.btnNext.setOnClickListener {
            loadNextQuestion()
        }
        binding.btnContinue.setOnClickListener {
            loadNextQuestion()
            resetAnswerViews()
            binding.viewCorrect.isVisible = false
            binding.viewNeutral.isVisible = true
        }

        binding.btnBack.setOnClickListener {

        }

        // Setup answer click listeners
        setupAnswerListeners()
    }

    private fun loadNextQuestion() {
        currentQuestion = questions.getNextQuestion()
        currentQuestion?.let {
            binding.tvMainQuest.text = it.text
            binding.tv1.text = "1"
            binding.tvAnsw1.text = it.variants[0]
            binding.tv2.text = "2"
            binding.tvAnsw2.text = it.variants[1]
            binding.tv3.text = "3"
            binding.tvAnsw3.text = it.variants[2]
            binding.tv4.text = "4"
            binding.tvAnsw4.text = it.variants[3]

            // Reset the answers' UI state
            resetAnswerViews()
        } ?: run {
            // No more questions available
            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show()
            binding.tvMainQuest.text = "Results"
            binding.llAnswersContainer.isVisible = false
            binding.viewNeutral.isVisible = false
        }
    }

    private fun setupAnswerListeners() {
        binding.llAnswer1.setOnClickListener {
            handleAnswerSelection(binding.llAnswer1, binding.tv1, binding.tvAnsw1)
        }
        binding.llAnswer2.setOnClickListener {
            handleAnswerSelection(binding.llAnswer2, binding.tv2, binding.tvAnsw2)
        }
        binding.llAnswer3.setOnClickListener {
            handleAnswerSelection(binding.llAnswer3, binding.tv3, binding.tvAnsw3)
        }
        binding.llAnswer4.setOnClickListener {
            handleAnswerSelection(binding.llAnswer4, binding.tv4, binding.tvAnsw4)
        }
    }

    private fun handleAnswerSelection(llAnswer: LinearLayout, tv: TextView, tvAnswer: TextView) {
        val selectedAnswer = tvAnswer.text.toString()
        val isCorrect = questions.checkAnswer(selectedAnswer)

        // Mark answers visually
        if (isCorrect) {
            markAnswerCorrect(llAnswer, tv, tvAnswer)
        } else {
            markAnswerIncorrect(llAnswer, tv, tvAnswer)
        }

        // Show result message
        showResultMessage(isCorrect)
    }

    private fun markAnswerNeutral(
        llAnswer: LinearLayout,
        tv: TextView,
        tvAnswer: TextView
    ) {
        llAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container
        )

        tvAnswer.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.textvariantcolor
            )
        )

        tv.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_rounded_variant
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.textvariantcolor
                )
            )
        }
    }

    private fun markAnswerCorrect(
        llAnswer: LinearLayout,
        tv: TextView,
        tvAnswer: TextView
    ) {
        llAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container_correct
        )

        tv.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variant_correct
        )

        tv.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        tvAnswer.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctanswercolor
            )
        )
    }

    private fun markAnswerIncorrect(
        llAnswer: LinearLayout,
        tv: TextView,
        tvAnswer: TextView
    ) {
        llAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container_uncorrect
        )

        tv.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variant_uncorrect
        )

        tv.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        tvAnswer.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.uncorrectanswercolor
            )
        )
    }

    private fun showResultMessage(isCorrect: Boolean) {
        val color: Int
        val messageText: String
        val resultIconResource: Int

        if (isCorrect) {
            color = ContextCompat.getColor(this, R.color.correctanswercolor)
            resultIconResource = R.drawable.ic_correct
            messageText = "Correct!"
        } else {
            color = ContextCompat.getColor(this, R.color.uncorrectanswercolor)
            resultIconResource = R.drawable.ic_wrong
            messageText = "InCorrect!"
        }

        with(binding) {
            viewNeutral.isVisible = false
            viewCorrect.isVisible = true
            btnContinue.setTextColor(color)
            viewCorrect.setBackgroundColor(color)
            tvMessageText.text = messageText
            imgViewMessage.setImageResource(resultIconResource)
        }
    }

    private fun resetAnswerViews() {
        markAnswerNeutral(binding.llAnswer1, binding.tv1, binding.tvAnsw1)
        markAnswerNeutral(binding.llAnswer2, binding.tv2, binding.tvAnsw2)
        markAnswerNeutral(binding.llAnswer3, binding.tv3, binding.tvAnsw3)
        markAnswerNeutral(binding.llAnswer4, binding.tv4, binding.tvAnsw4)
    }
}
