package dam2.amoreno.uf2_a2

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import dam2.amoreno.uf2_a2.Adapter.SliderAdapter

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2

    lateinit var sliderAdapter: SliderAdapter

    // Llistat d'imatges a mostrar
    val imageList = listOf(
        R.drawable.burtalist,
        R.drawable.dune2,
        R.drawable.robot,
        R.drawable.thebatman,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.black))
        )


        // Obtenim la mida de la pantalla
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels

        // Defineix la mida del logo com un percentatge de l'amplada de la pantalla
        val logoWidth = (screenWidth * 0.3).toInt()  // 30% de l'amplada de la pantalla
        val logoHeight = (logoWidth * 0.5).toInt()   // Alçada proporcional (50% de l'amplada)

        // Crear un ImageView amb el teu logotip
        val logo = ImageView(this).apply {
            setImageResource(R.drawable.primelogo)

            val layoutParams = ActionBar.LayoutParams(
                logoWidth,
                logoHeight
            )
            this.layoutParams = layoutParams
        }

        // Afegeix la imatge al ActionBar
        supportActionBar?.setCustomView(logo)

        // Habilitar la personalització del ActionBar
        supportActionBar?.setDisplayShowCustomEnabled(true)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Assignem la vista del view pager definit a l'XML
        viewPager = findViewById(R.id.slider)

        // Creem una instància i assignem l'adaptador
        sliderAdapter = SliderAdapter(this, imageList)
        viewPager.adapter = sliderAdapter

        // Mètode per gestionar l'slider
        startImageSlideshow()


        val pelicules = findViewById<Button>(R.id.pelicules)
        val series = findViewById<Button>(R.id.series)
        val esports = findViewById<Button>(R.id.esports)
        val tv = findViewById<Button>(R.id.directes)
        val documentals = findViewById<Button>(R.id.documentals)
        val anime = findViewById<Button>(R.id.anime)

        pelicules.setOnClickListener {
            Toast.makeText(this, "Pelicules", Toast.LENGTH_SHORT).show()
        }

        series.setOnClickListener {
            Toast.makeText(this, "Series", Toast.LENGTH_SHORT).show()
        }

        esports.setOnClickListener {
            Toast.makeText(this, "Esports", Toast.LENGTH_SHORT).show()
        }

        tv.setOnClickListener {
            Toast.makeText(this, "Directes", Toast.LENGTH_SHORT).show()
        }

        documentals.setOnClickListener {
            Toast.makeText(this, "Documentals", Toast.LENGTH_SHORT).show()
        }

        anime.setOnClickListener {
            Toast.makeText(this, "Anime", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startImageSlideshow() {

        // Handler per executar tasques amb delay
        val handler = android.os.Handler()

        // Codi per tal d'executar un fil
        val runnable = object : Runnable {

            // inicializtem la variable per mostrar la imatge actual
            var currentPage = 0

            // Codi que es crida cada cop que es llança el handler
            override fun run() {
                // Mostra la imatge actual
                viewPager.setCurrentItem(currentPage, true)

                // S'avança a la següent imatge
                currentPage++

                // Es passa de l'última imatge a la primera
                if (currentPage >= imageList.size) {
                    currentPage = 0
                }

                // Cada 3 segons es llança el codi anterior
                handler.postDelayed(this, 5000)
            }
        }
        // El procés s'inicia als 3 segons
        handler.postDelayed(runnable, 5000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}