package com.example.quizapp

import android.os.Bundle
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



        binding.llanswer1.setOnClickListener {
            markAnswerUncorrect()
        }
        binding.llAnswer3.setOnClickListener {
            markAnswerCorrect()
        }

        binding.btnContinueLike.setOnClickListener {
            markAnswerNeutral();
        }

        binding.btnContinueWrong.setOnClickListener {
            markAnswerNeutral();
        }

    }

    private fun markAnswerNeutral() {

        with(binding) {
            for (layout in listOf(llanswer1, llAnswer3)) {
                layout.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_container
                )
            }

            for (textView in listOf(tvAnsw1, tvAnsw3)) {
                textView.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.textvariantcolor
                    )
                )
            }

            for (textView in listOf(tv1, tv3)) {
                textView.apply {
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

            viewNeutral.isVisible = true
            viewWrong.isVisible = false
            viewCorrect.isVisible = false


        }
    }

    private fun markAnswerCorrect() {

        binding.llAnswer3.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container_correct
        )

        binding.tv3.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variant_correct
        )

        binding.tv3.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        binding.tvAnsw3.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctanswercolor
            )
        )
        binding.viewCorrect.isVisible = true
        binding.viewWrong.isVisible = false
        binding.viewNeutral.isVisible = false


    }

    private fun markAnswerUncorrect() {

        binding.llanswer1.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_container_uncorrect
        )

        binding.tv1.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variant_uncorrect
        )

        binding.tv1.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        binding.tvAnsw1.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.uncorrectanswercolor
            )
        )


        binding.viewWrong.isVisible = true
        binding.viewCorrect.isVisible = false
        binding.viewNeutral.isVisible = false

    }
}
