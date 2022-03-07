package dev.codecraft.sociout.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.codecraft.sociout.databinding.FragmentInputCodeDialogBinding


class InputCodeDialogFragment : Fragment() {
    private lateinit var binding: FragmentInputCodeDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment
        binding = FragmentInputCodeDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDone.setOnClickListener {

        }

        binding.buttonCancel.setOnClickListener {

        }
    }

}