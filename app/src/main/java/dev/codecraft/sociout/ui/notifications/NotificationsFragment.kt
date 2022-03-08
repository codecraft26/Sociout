package dev.codecraft.sociout.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.google.firebase.auth.FirebaseAuth
import dev.codecraft.sociout.R
import dev.codecraft.sociout.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root






        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = FirebaseAuth.getInstance().currentUser
        val displayName = currentUser?.displayName
        val emailName=currentUser?.email


        with(binding) {
            imageViewUserImage.load(R.drawable.ic_baseline_account_circle_24) {
                crossfade(true)
                transformations(CircleCropTransformation())

            }



            textViewVersionName.text = "Version 3.55"
            textViewUserName.text=displayName
            textViewUserEmail.text=emailName

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}