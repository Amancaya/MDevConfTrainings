package negron.kaya.training6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import negron.kaya.training6.model.Speaker

typealias OnClickDelete = (Speaker) -> Unit
typealias OnClickUpdate = (Speaker) -> Unit

class SpeakerAdapter(
   private val onClickDelete: OnClickDelete,
   private val onClickUpdate: OnClickUpdate
) : RecyclerView.Adapter<SpeakerItem>() {

    private var speakers: MutableList<Speaker> = mutableListOf()

    fun addSpeakers(speakers: List<Speaker>) {
        if (this.speakers.isNotEmpty()) { this.speakers.clear() }
        this.speakers.addAll(speakers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerItem {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false)
        return SpeakerItem(itemView)
    }

    override fun getItemCount(): Int = speakers.size

    override fun onBindViewHolder(holder: SpeakerItem, position: Int) {
        holder.bindData(
            speaker = speakers[position],
            onClickDelete = onClickDelete,
            onClickUpdate = onClickUpdate
        )
    }
}