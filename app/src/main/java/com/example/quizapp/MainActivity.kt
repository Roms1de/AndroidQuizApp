package com.example.quizapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }






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
}
