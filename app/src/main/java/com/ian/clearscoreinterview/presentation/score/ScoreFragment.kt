package com.ian.clearscoreinterview.presentation.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ian.clearscoreinterview.common.Constants
import com.ian.clearscoreinterview.databinding.FragmentScoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreFragment : Fragment() {

    //viewModel
    private val viewModel: ScoreViewModel by viewModels()

    //binding
    private lateinit var binding: FragmentScoreBinding

    //context
    private val mCtx by lazy {
        requireActivity()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //initialize binding
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel.fetchScore()
    }

    //init observers
    private fun initObservers(){
        //observe state
        viewModel.state.observe(viewLifecycleOwner, {
            if(it.isLoading){
                binding.txtScore.text = Constants.WAIT
            }else{

                if(it.creditScore == null){
                    binding.txtScore.text = it.error
                }else{
                    binding.txtScore.text = "${it.creditScore.score} / ${it.creditScore.maxScore}"
                }

            }

        })
    }



}