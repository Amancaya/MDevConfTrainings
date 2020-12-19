package negron.kaya.training6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import negron.kaya.training6.model.Speaker

private const val KEY_SPEAKER = "KeySpeaker"

class AddFragment : BottomSheetDialogFragment() {

    private var speaker: Speaker? = null

    companion object {
        fun getAddFragment(speaker: Speaker): AddFragment {
            val bundle = Bundle().apply { putParcelable(KEY_SPEAKER, speaker) }
            return AddFragment().apply { arguments = bundle }
        }
    }

    private val editName: EditText by lazy {
        requireView().findViewById<EditText>(R.id.edit_name)
    }

    private val editTalk: EditText by lazy {
        requireView().findViewById<EditText>(R.id.edit_talk)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
            saveSpeaker()
        }
    }

    private fun setupView() {
        speaker = arguments?.getParcelable(KEY_SPEAKER)
        editName.setText(speaker?.name)
        editTalk.setText(speaker?.talk)
    }

    private fun saveSpeaker() {
        if(speaker == null ){
            val speaker = Speaker(
                name = editName.text.toString(),
                talk = editTalk.text.toString()
            )
            FirebaseHelper.addSpeaker(speaker)
        } else {
            speaker?.let { speaker ->
                val updateSpeaker = speaker.copy(
                    name = editName.text.toString(),
                    talk = editTalk.text.toString()
                )
                FirebaseHelper.updateSpeaker(updateSpeaker)
            }
        }
        dismiss()
    }
}