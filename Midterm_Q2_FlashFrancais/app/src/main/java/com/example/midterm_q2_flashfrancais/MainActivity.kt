package com.example.midterm_q2_flashfrancais

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var flashcardsMap: Map<String, List<Flashcard>>
    private var currentCardIndex = 0
    private lateinit var currentCategory: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val categorySpinner: Spinner = findViewById(R.id.category_spinner)
        val nextFlashcardButton: Button = findViewById(R.id.next_flashcard_button)

        // Set up Spinner
        categorySpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.french_categories,
            android.R.layout.simple_spinner_item
        )
        categorySpinner.setAdapter(categorySpinner.adapter)

        // Initialize flashcards map with data
        flashcardsMap = mapOf(
            "Colors" to listOf(
                Flashcard("Rouge", "Red"),
                Flashcard("Vert", "Green"),
                Flashcard("Bleu", "Blue"),
                Flashcard("Jaune", "Yellow"),
                Flashcard("Noir", "Black"),
                Flashcard("Blanc", "White"),
                Flashcard("Gris", "Gray"),
                Flashcard("Orange", "Orange"),
                Flashcard("Violet", "Purple"),
                Flashcard("Rose", "Pink"),
                Flashcard("Marron", "Brown"),
                Flashcard("Turquoise", "Turquoise"),
                Flashcard("Argenté", "Silver"),
                Flashcard("Doré", "Gold"),
                Flashcard("Bordeaux", "Maroon"),
                // Add more colors as needed
            ),
            "Days" to listOf(
                Flashcard("Lundi", "Monday"),
                Flashcard("Mardi", "Tuesday"),
                Flashcard("Mercredi", "Wednesday"),
                Flashcard("Jeudi", "Thursday"),
                Flashcard("Vendredi", "Friday"),
                Flashcard("Samedi", "Saturday"),
                Flashcard("Dimanche", "Sunday")
                // All days of the week
            ),
            "Greetings" to listOf(
                Flashcard("Bonjour", "Hello"),
                Flashcard("Salut", "Hi"),
                Flashcard("Bonsoir", "Good evening"),
                Flashcard("Bon après-midi", "Good afternoon"),
                Flashcard("Bonne nuit", "Good night"),
                Flashcard("Bienvenue", "Welcome"),
                Flashcard("Coucou", "Hey there")
                // Add more greetings as needed
            ),
            "Animals" to listOf(
                Flashcard("Chat", "Cat"),
                Flashcard("Chien", "Dog"),
                Flashcard("Oiseau", "Bird"),
                Flashcard("Lapin", "Rabbit"),
                Flashcard("Poisson", "Fish"),
                Flashcard("Cheval", "Horse"),
                Flashcard("Vache", "Cow"),
                Flashcard("Souris", "Mouse"),
                Flashcard("Éléphant", "Elephant"),
                Flashcard("Girafe", "Giraffe"),
                Flashcard("Serpent", "Snake"),
                Flashcard("Tigre", "Tiger"),
                Flashcard("Lion", "Lion"),
                Flashcard("Renard", "Fox"),
                Flashcard("Abeille", "Bee"),
                Flashcard("Poule", "Chicken"),
                Flashcard("Cochon", "Pig"),
                Flashcard("Papillon", "Butterfly"),
                Flashcard("Coccinelle", "Ladybug"),
                Flashcard("Loup", "Wolf")
                // Add more animals as needed
            )
        )

        // Set default category
        //currentCategory = "Colors"
        //displayFlashcard()

        // Set up Spinner onItemSelectedListener
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentCategory = parent?.getItemAtPosition(position).toString()
                currentCardIndex = 0 // Reset index when category changes
                displayFlashcard()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected
            }
        }

        // Set up Button onClickListener
        nextFlashcardButton.setOnClickListener {
            currentCardIndex = (currentCardIndex + 1) % flashcardsMap[currentCategory]!!.size
            displayFlashcard()
        }
    }

    private fun displayFlashcard() {
        val flashcard = flashcardsMap[currentCategory]!![currentCardIndex]
        val frenchWordTextView: TextView = findViewById(R.id.french_word)
        val englishTranslationTextView: TextView = findViewById(R.id.english_translation)


        frenchWordTextView.text = flashcard.frenchWord
        englishTranslationTextView.text = flashcard.englishTranslation


        // Hide all text views except the first one (French word) initially
        frenchWordTextView.visibility = View.VISIBLE
        englishTranslationTextView.visibility = View.GONE

        // Set up click listener to switch between views
        frenchWordTextView.setOnClickListener {
            toggleVisibility(frenchWordTextView, englishTranslationTextView)
        }
        englishTranslationTextView.setOnClickListener {
            toggleVisibility(englishTranslationTextView, frenchWordTextView)
        }


    }

    private fun toggleVisibility(viewToHide: TextView, viewToShow: TextView) {
        viewToHide.visibility = View.GONE
        viewToShow.visibility = View.VISIBLE
    }

}
