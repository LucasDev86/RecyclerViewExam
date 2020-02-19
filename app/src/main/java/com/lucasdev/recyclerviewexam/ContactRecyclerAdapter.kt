package com.lucasdev.recyclerviewexam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasdev.recyclerviewexam.models.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactRecyclerAdapter : RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder>() {

    var mItems: MutableList<Contact> = arrayListOf()
    var mCheckedMap: MutableMap<Contact, Boolean> = mutableMapOf()

    var mCheckedContact: MutableList<Contact> = arrayListOf()

    fun setItems(items: MutableList<Contact>) {
        mItems = items
    }

    fun removeItems(): Boolean {
        val result: Boolean = mItems.removeAll(mCheckedContact)
        if (result) {
            notifyDataSetChanged()
        }
        return result
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        val viewHolder = ContactViewHolder(view)
        viewHolder.checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            val contact = mItems?.get(viewHolder.adapterPosition)
            mCheckedMap?.put(contact, isChecked)

            if (isChecked) {
                mCheckedContact.add(contact)
            } else {
                mCheckedContact.remove(contact)
            }
        }
        return viewHolder
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int = mItems?.size

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        val contact = mItems?.get(position)

        holder?.imageView.setImageResource(R.mipmap.ic_launcher)
        holder?.nameTextView?.text = "${contact.name}"

        var isChecked = if (mCheckedMap?.get(contact) == null) {
            false
        } else {
            mCheckedMap?.get(contact)!!
        }

        holder.checkBox.isChecked = isChecked
    }

    // ViewHolder -> item에 이미지,텍스트,체크박스 담는 곳
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.picture_image_view
        var nameTextView = itemView.nameTextview
        var checkBox = itemView.checkBox
    }
}
