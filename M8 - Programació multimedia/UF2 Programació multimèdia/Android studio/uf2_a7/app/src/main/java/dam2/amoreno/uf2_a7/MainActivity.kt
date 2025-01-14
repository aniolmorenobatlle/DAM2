package dam2.amoreno.uf2_a7

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val photoComment = findViewById<TextView>(R.id.comment)

        val text = "ajuntamentdepalamos El poblat ibèric de Castell torna a estar obert al públic després de més..."

        val firstWord = text.substringBefore(" ")
        val restOfText = text.substringAfter(" ")

        val formattedText = "<b>$firstWord</b> $restOfText"

        photoComment.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)
    }
}