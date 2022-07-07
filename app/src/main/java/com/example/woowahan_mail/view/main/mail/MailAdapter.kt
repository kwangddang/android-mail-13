package com.example.woowahan_mail.view.main.mail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.woowahan_mail.data.Mail
import com.example.woowahan_mail.databinding.ItemMailBinding

class MailAdapter: RecyclerView.Adapter<MailViewHolder>() {

    var mails = listOf<Mail>()

    fun setDummyData(dummyData: List<Mail>){
        mails = dummyData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        return MailViewHolder(ItemMailBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        holder.bind(mails[position])
    }

    override fun getItemCount(): Int = mails.size

}
class MailViewHolder(private val binding: ItemMailBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(mail: Mail){
        binding.mail = mail
    }
}