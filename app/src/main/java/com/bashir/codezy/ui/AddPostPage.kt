package com.bashir.codezy.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bashir.codezy.R
import com.bashir.codezy.data.model.Post
import com.bashir.codezy.databinding.FragmentAddPostPageBinding
import com.bashir.codezy.databinding.FragmentRegisterMainBinding
import com.bashir.codezy.viewmodel.AddPostUIViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AddPostPage : Fragment() {

    private lateinit var binding: FragmentAddPostPageBinding
    private lateinit var title: EditText
    private lateinit var text: EditText
    private val viewModel: AddPostUIViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPostPageBinding.inflate(inflater, container, false)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = requireActivity().window
            window.statusBarColor =
                ContextCompat.getColor(requireContext(), R.color.codezy_light_green)
        }
        return binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = view.findViewById(R.id.AddPostTitle)
        text = view.findViewById(R.id.AddPostText)

        view.findViewById<Button>(R.id.share_button).setOnClickListener {
            Toast.makeText(context, "Post shared", Toast.LENGTH_SHORT).show()

            fun getTimeDate(timestamp: Long): String {
                return try {
                    val netDate = Date(timestamp)
                    val sfd = SimpleDateFormat("dd MMM. yyyy, HH:mm", Locale.getDefault())
                    sfd.format(netDate)
                } catch (e: Exception) {
                    "date"
                }
            }

            val content = text.text.toString()
            val user = FirebaseAuth.getInstance().currentUser
            val userName = user?.displayName


            val post = Post(
//                profilePhoto = user?.photoUrl.toString(),
                username = userName ?: "",
                title = title.text.toString(),
                contentText = content,
                date = getTimeDate(System.currentTimeMillis())

            )

            viewModel.insertPostFirebase(post)
            return@setOnClickListener
        }
    }
}

