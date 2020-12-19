package negron.kaya.training3

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.properties.Delegates

class InfoLayout(context: Context, attributeSet: AttributeSet): ConstraintLayout(context, attributeSet)  {

    private val textCharacteristic: TextView
    private val textCharacteristicName: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_info, this, true)
        textCharacteristic = findViewById(R.id.text_characteristic)
        textCharacteristicName = findViewById(R.id.text_characteristic_name)

        val attrs = context.theme.obtainStyledAttributes(attributeSet, R.styleable.InfoLayout, 0, 0)
        attrs.getString(R.styleable.InfoLayout_characteristicName)?.let {
            textCharacteristicName.text = it
        }
    }

    fun setCharacteristic(value: String) {
        textCharacteristic.text = value
    }
}