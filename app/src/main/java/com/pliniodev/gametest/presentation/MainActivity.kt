package com.pliniodev.gametest.presentation

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver.OnPreDrawListener
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.button.MaterialButton
import com.pliniodev.gametest.R
import com.pliniodev.gametest.constants.Constants
import com.pliniodev.gametest.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var lastStepSelected: LottieAnimationView
    private var coordenates = intArrayOf(0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFocusDown()
        setListeners()
        observers()
        requestLastStep()
        updateBD()
    }

    override fun onResume() {
        super.onResume()
        //if first run
        viewModel.registerDate()
    }

    private fun updateBD() {
        viewModel.updateBD()
    }

    private fun getLocationOnScreen(view: View): IntArray {
        val location = IntArray(2)
        when (view.id) {
            R.id.step_1 -> location[0] = 405
            R.id.step_2 -> location[0] = 140
            R.id.step_3 -> location[0] = 405
            R.id.step_4 -> location[0] = 140
            R.id.step_5 -> location[0] = 0
        }
        return location
    }

    private fun setFocusDown() {

        binding.nestedScrollView.viewTreeObserver
            .addOnPreDrawListener(object : OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    binding.nestedScrollView.viewTreeObserver.removeOnPreDrawListener(this)
                    binding.nestedScrollView.smoothScrollTo(0, 5000, 2000)
                    return false
                }
            })
    }

    private fun observers() {
        viewModel.lastSelectedStepLiveData.observe(this, { step ->
            when (step) {
                1 -> lastStepSelected = binding.animationStep1
                2 -> lastStepSelected = binding.animationStep2
                3 -> lastStepSelected = binding.animationStep3
                4 -> lastStepSelected = binding.animationStep4
                5 -> lastStepSelected = binding.animationStep5
            }
            initStartAnimation(lastStepSelected)
        })

        viewModel.phraseLiveData.observe(this, { phrase ->
            showToaster(phrase)
        })

        viewModel.updatedStep.observe(this, {
            when (it) {
                1 -> lastStepSelected = binding.animationStep1
                2 -> lastStepSelected = binding.animationStep2
                3 -> lastStepSelected = binding.animationStep3
                4 -> lastStepSelected = binding.animationStep4
                5 -> lastStepSelected = binding.animationStep5
            }
        })

        viewModel.errorLiveData.observe(this, { error ->
            if (error != "") {
                Toast.makeText(this, "Erro: $error", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showToaster(phrase: String?) {
        phrase?.let {
            Toast(this).showCustomToast(
                it, this,
                coordenates[0],
                coordenates[1]
            )
        }
    }

    private fun initStartAnimation(lastStepSelected: LottieAnimationView) {
        changeVisibility(lastStepSelected)
        fadeInAnimation(lastStepSelected)
        lastStepSelected.playAnimation()
        //todo toaster
    }

    private fun setListeners() {
        binding.step1.setOnClickListener(this)
        binding.step2.setOnClickListener(this)
        binding.step3.setOnClickListener(this)
        binding.step4.setOnClickListener(this)
        binding.step5.setOnClickListener(this)
    }

    private fun requestLastStep() {
        viewModel.requestLastStepSelected()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.step_1 -> {
                startOnClickAnimation(binding.animationStep1, lastStepSelected)
                coordenates = getLocationOnScreen(binding.step1)
                viewModel.getPhrase(Constants.STEP1) // busca a frase na api
                viewModel.updateSelectedStep(Constants.STEP1) //escreve no banco de dados

            }
            R.id.step_2 -> {
                startOnClickAnimation(binding.animationStep2, lastStepSelected)
                coordenates = getLocationOnScreen(binding.step2)
                viewModel.getPhrase(Constants.STEP2)
                viewModel.updateSelectedStep(Constants.STEP2) //escreve no banco de dados
            }
            R.id.step_3 -> {
                startOnClickAnimation(binding.animationStep3, lastStepSelected)
                coordenates = getLocationOnScreen(binding.step3)
                viewModel.getPhrase(Constants.STEP3)
                viewModel.updateSelectedStep(Constants.STEP3) //escreve no banco de dados
            }
            R.id.step_4 -> {
                startOnClickAnimation(binding.animationStep4, lastStepSelected)
                coordenates = getLocationOnScreen(binding.step4)
                viewModel.getPhrase(Constants.STEP4)
                viewModel.updateSelectedStep(Constants.STEP4) //escreve no banco de dados
            }
            R.id.step_5 -> {
                startOnClickAnimation(binding.animationStep5, lastStepSelected)
                coordenates = getLocationOnScreen(binding.step5)
                viewModel.getPhrase(Constants.STEP5)
                viewModel.updateSelectedStep(Constants.STEP5) //escreve no banco de dados
            }
        }
    }

    private fun startOnClickAnimation(
        stepSelected: LottieAnimationView,
        lastStepSelected: LottieAnimationView
    ) {
        fadeOutAnimation(lastStepSelected)
        changeVisibility(lastStepSelected)
        changeVisibility(stepSelected)
        fadeInAnimation(stepSelected)
        stepSelected.playAnimation()
    }

    private fun changeVisibility(view: View) {
        if (view.visibility == View.GONE) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    private fun fadeInAnimation(animationView: LottieAnimationView) {
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        animationView.startAnimation(animationFadeIn)
    }

    private fun fadeOutAnimation(animationView: LottieAnimationView) {
        val animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        animationView.startAnimation(animationFadeOut)
    }

    private fun Toast.showCustomToast(message: String, activity: Activity, x: Int, y: Int) {
        val layout = activity.layoutInflater.inflate(
            R.layout.custom_toaster,
            activity.findViewById(R.id.ll_wrapper)
        )

        val textView = layout.findViewById<TextView>(R.id.tv_toaster)
        textView.text = message

        this.apply {
            setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            setGravity(Gravity.START, x, 0)
            layout.findViewById<MaterialButton>(R.id.ok_button).setOnClickListener {
                cancel()
            }
            view = layout
            show()
        }
    }
}