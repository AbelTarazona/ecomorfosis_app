package com.ecomorfosis.global.mock

import com.ecomorfosis.global.R
import com.ecomorfosis.global.models.Answer
import com.ecomorfosis.global.models.Country
import com.ecomorfosis.global.models.ItemMain
import com.ecomorfosis.global.models.Question
import com.ecomorfosis.global.utils.KeyConstants

/**
 * Created by AbelTarazona on 30/08/2020
 */

/*fun getQuestions(): List<Question> = listOf(
    Question(1,"¿Pregunta #1?","","","","", getAnswers()),
    Question(1,"¿Pregunta #1?","","","","", getAnswers()),
    Question(1,"¿Pregunta #1?","","","","", getAnswers())
)*/

fun getAnswers(): List<Answer> = listOf(
    Answer(1,"Respuesta ${(0..20).random()}", "0"),
    Answer(1,"Respuesta ${(0..20).random()}", "0"),
    Answer(1,"Respuesta ${(0..20).random()}", "0"),
    Answer(1,"Respuesta ${(0..20).random()}", "0")
)

// Official
fun getCountries() = listOf(
    Country(id = 1, title = "PERU", resource = R.drawable.peru),
    Country(id = 2, title = "ARGENTINA", resource = R.drawable.argentina),
    Country(id = 3, title = "COSTA RICA", resource = R.drawable.costarica),
    Country(id = 4, title = "COLOMBIA", resource = R.drawable.colombia),
    Country(id = 5, title = "URUGUAY", resource = R.drawable.uruguay),
    Country(id = 6, title = "MEXICO", resource = R.drawable.mexico)
)

fun getModulesHome() = listOf(
    ItemMain(
        id = KeyConstants.HomeModules.GUIA,
        title = "nosotros",
        icon = R.drawable.ic_guide,
        img = R.drawable.bolita,
        order = 1,
        background = R.drawable.bck_main_module_guide
    ),
    ItemMain(
        id = KeyConstants.HomeModules.GAME,
        title = "tablero",
        icon = R.drawable.ic_gamepad,
        img = R.drawable.perrito,
        order = 3,
        background = R.drawable.bck_main_module_game
    ),
    ItemMain(
        id = KeyConstants.HomeModules.GUIA,
        title = "guía",
        icon = R.drawable.ic_guide,
        img = R.drawable.bolita,
        order = 2,
        background = R.drawable.bck_main_module_guide
    ),
    ItemMain(
        id = KeyConstants.HomeModules.MATERIALES,
        title = "materiales",
        icon = R.drawable.ic_jigsaw,
        img = R.drawable.tortuguita,
        order = 4,
        background = R.drawable.bck_main_module_material
    )
)

fun getIcons() =
    listOf(R.drawable.estrellita, R.drawable.tortuguita, R.drawable.perrito, R.drawable.bolita)