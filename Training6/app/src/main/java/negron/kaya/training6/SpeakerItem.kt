package negron.kaya.training6

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import negron.kaya.training6.model.Speaker

class SpeakerItem(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindData(speaker: Speaker, onClickDelete: OnClickDelete, onClickUpdate: OnClickUpdate) {
        itemView.apply {
            findViewById<TextView>(R.id.text_name).text = speaker.name
            findViewById<TextView>(R.id.text_talk).text = speaker.talk

            findViewById<ImageView>(R.id.img_delete).setOnClickListener {
                onClickDelete(speaker)
            }
            findViewById<ImageView>(R.id.img_edit).setOnClickListener {
                onClickUpdate(speaker)
            }
        }
    }
}