package dam2.amoreno.prova_view_pager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import dam2.amoreno.prova_view_pager.adapter.SliderAdapter

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var sliderAdapter: SliderAdapter

    val imageList = listOf(
        R.drawable.estartit2,
        R.drawable.estartit3,
        R.drawable.estartit4,
        R.drawable.estartit5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Inicialitzar slider
        viewPager = findViewById(R.id.slider)
        sliderAdapter = SliderAdapter(this, imageList)
        viewPager.adapter = sliderAdapter

        // Mètode per gestionar l'slider
        startImageSlideshow()


    }

    private fun startImageSlideshow() {

        val handler = android.os.Handler()

        val runnable = object : Runnable {

            var currentPage = 0

            override fun run() {
                viewPager.setCurrentItem(currentPage, true)

                currentPage++

                if (currentPage >= imageList.size) {
                    currentPage = 0
                }

                handler.postDelayed(this, 5000)
            }
        }
        handler.postDelayed(runnable, 5000)
    }
}