package negron.kaya.training6

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import negron.kaya.training6.model.Speaker
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val speakerAdapter: SpeakerAdapter by lazy { SpeakerAdapter(::deleteSpeaker, ::updateSpeaker) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecycler()
        FirebaseHelper.getSpeakers(::renderSpeakers)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_speaker, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.item_add -> {
                AddFragment()
                    .show(supportFragmentManager, "TAG")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun setupRecycler() {
        findViewById<RecyclerView>(R.id.list_speakers).apply {
            adapter = speakerAdapter
        }
    }

    private fun renderSpeakers(speakers: List<Speaker>){
        speakerAdapter.addSpeakers(speakers)
    }

    private fun deleteSpeaker(speaker: Speaker) {
        val message = String.format(getString(R.string.delete_message), speaker.name)
        AlertDialog.Builder(this)
            .setTitle(R.string.delete_title)
            .setMessage(message)
            .setPositiveButton(R.string.delete_positive_action) { dialog, _ ->
                FirebaseHelper.deleteSpeaker(speaker)
            }
            .setNegativeButton(R.string.delete_negative_action) { _, _ ->
                Timber.tag("MainActivity").e("click negative")
            }.create().show()
    }

    private fun updateSpeaker(speaker: Speaker) {
        AddFragment
            .getAddFragment(speaker)
            .show(supportFragmentManager, "TagEdit")
    }
}