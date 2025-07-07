package com.example.myapplication.view.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.view.Notification.NotificationHelper
import com.example.myapplication.R
import com.example.myapplication.data.Entity.Book
import com.example.myapplication.databinding.FragmentNotificationBinding
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.rv_adapters.BookListRecyclerAdapter

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var bookAdapter: BookListRecyclerAdapter
    private lateinit var book: Book
    private var notificationList: MutableList<Book> = mutableListOf(book)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delete()
        remind()
        val notificationList: List<Book> = emptyList()
        NotificationHelper.createNotification(requireContext(), book)

        binding.notificationRecycler.apply {
            bookAdapter =
                BookListRecyclerAdapter(object : BookListRecyclerAdapter.OnItemClickListener {
                    override fun click(book: Book) {
                        (requireActivity() as MainActivity).launchDetailsFragment(book)
                    }
                })
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
        bookAdapter.addItems(notificationList)

    }

    private fun delete() {
        val deleteButton: Button = requireView().findViewById(R.id.delete_button)
        deleteButton.setOnClickListener {
            notificationList.removeAt(0)
            bookAdapter.notifyItemRemoved(1)
            bookAdapter.notifyItemRangeChanged(1, bookAdapter.itemCount)
        }
    }

    private fun remind(){
        val time: Button = requireView().findViewById(R.id.time)
        time.setOnClickListener {
         NotificationHelper.notificationSet(requireContext(), book)
        }
    }
}



