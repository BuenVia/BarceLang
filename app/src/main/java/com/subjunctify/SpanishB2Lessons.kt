package com.subjunctify

// The content of the Spanish B2 course, in teaching order. See Exercise for the `{a|b}` answer
// syntax, and Explanation in MainActivity for the markup used in the notes.

private const val FUNCTIONAL = "Verbs: Functional"
private const val PAST = "Verbs: Past"
private const val DISCOURSE = "Discourse, sentence and phrase types"
private const val SUBJUNCTIVE = "Verbs: Subjuntivo"
private const val IMPERATIVE = "Verbs: Imperative"

private fun ex(id: String, english: String, pattern: String, vararg alsoAccepted: String) =
    Exercise(id, english, pattern, *alsoAccepted)

/** A subject taught in the subjunctive course too. Sharing it means progress carries across. */
private fun fromSubjunctiveCourse(title: String): Lesson {
    val lesson = subjunctiveLessons.first { it.title == title }
    return Lesson(lesson.id, lesson.title, lesson.level, lesson.explanation, lesson.exercises, SUBJUNCTIVE)
}

val spanishB2Lessons: List<Lesson> = listOf(
    Lesson(
        id = "es-b2-lesson-01",
        title = "Explaining what you can do",
        level = "B2",
        category = FUNCTIONAL,
        explanation = """
            Spanish separates having a skill from being able to do something right now.

            ## Saber or poder
            - **Sé** nadar. I know how to swim. (a skill)
            - Hoy no **puedo** nadar. I can't swim today. (circumstances)

            ## Ability and achievement
            - **ser capaz de** + infinitive: to be capable of
            - **conseguir / lograr** + infinitive: to manage to
            - **tener experiencia en**: to have experience in

            ## Being good or bad at something
            **Dársele bien/mal** works like gustar: the thing you're good at is the subject, and the person is an indirect pronoun.
            - **Se me da bien** cocinar. I'm good at cooking.
            - **Se le dan mal** las matemáticas. He's bad at maths.
            - **Se nos da bien** resolver problemas. We're good at solving problems.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-001", "I know how to swim, but I can't today.", "{|yo }sé nadar, pero hoy no puedo.", "{|yo }sé nadar, pero no puedo hoy."),
            ex("es-b2-002", "She knows how to speak three languages.", "{|ella }sabe hablar tres {idiomas|lenguas}."),
            ex("es-b2-003", "I'm good at cooking.", "Se me da bien cocinar.", "Se me da bien la cocina.", "{Soy bueno|Soy buena} cocinando."),
            ex("es-b2-004", "He's bad at maths.", "Se le dan mal las matemáticas."),
            ex("es-b2-005", "We managed to finish the project on time.", "{|nosotros |nosotras }{conseguimos|logramos} terminar el proyecto a tiempo."),
            ex("es-b2-006", "I'm capable of running ten kilometres.", "{|yo }soy capaz de correr diez kilómetros."),
            ex("es-b2-007", "Do you (tú) know how to drive a lorry?", "¿{|tú }sabes conducir un camión?", "¿Sabes {|tú }conducir un camión?"),
            ex("es-b2-008", "I can't manage to open this window.", "{|yo }no {consigo|logro} abrir esta ventana."),
            ex("es-b2-009", "She's able to work under pressure.", "{|ella }es capaz de trabajar bajo presión."),
            ex("es-b2-010", "I've never been able to sing well.", "{|yo }nunca he podido cantar bien.", "{|yo }no he podido nunca cantar bien."),
            ex("es-b2-011", "I have experience in managing teams.", "{|yo }tengo experiencia en {gestionar|dirigir} equipos.", "{|yo }tengo experiencia en {la gestión|la dirección} de equipos."),
            ex("es-b2-012", "We're good at solving problems.", "Se nos da bien resolver problemas.", "Se nos da bien solucionar problemas."),
            ex("es-b2-013", "You (tú) can't park here.", "{|tú }no puedes aparcar aquí."),
            ex("es-b2-014", "I don't know how to use this program.", "{|yo }no sé {usar|utilizar} este programa."),
            ex("es-b2-015", "I understand Italian, but I don't know how to speak it.", "{|yo }entiendo {|el }italiano, pero no {sé hablarlo|lo sé hablar}."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-02",
        title = "Reviewing past tenses",
        level = "B2",
        category = PAST,
        explanation = """
            Each past tense frames an event differently.

            ## Pretérito perfecto: he hablado
            Connected to the present, with **hoy, esta semana, ya, todavía no, nunca**:
            - Hoy **he comido** tarde. I ate late today.

            ## Pretérito indefinido: hablé
            Finished events at a finished time, with **ayer, el año pasado, en 2010**:
            - Ayer **comí** tarde. I ate late yesterday.

            ## Pretérito imperfecto: hablaba
            Descriptions, habits and actions in progress:
            - Cuando era niño, **comía** poco. When I was a child, I ate little.

            ## Pretérito pluscuamperfecto: había hablado
            Something that happened before another past event:
            - Cuando llegué, ya **habían comido**. When I arrived, they had already eaten.

            ## Together in a story
            The imperfecto sets the scene, and the indefinido moves the story on:
            - **Llovía** cuando **salí** de casa. It was raining when I left home.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-016", "I got up late today.", "Hoy {|yo }me he levantado tarde.", "{|yo }me he levantado tarde hoy."),
            ex("es-b2-017", "I got up early yesterday.", "Ayer {|yo }me levanté {temprano|pronto}.", "{|yo }me levanté {temprano|pronto} ayer."),
            ex("es-b2-018", "When I was a child, I lived in Seville.", "Cuando {|yo }era {niño|niña}, {|yo }vivía en Sevilla."),
            ex("es-b2-019", "When we arrived, the film had already started.", "Cuando {|nosotros |nosotras }llegamos, la película ya había empezado.", "Cuando {|nosotros |nosotras }llegamos, la película ya había comenzado."),
            ex("es-b2-020", "It was raining when I left home.", "Llovía cuando {|yo }salí de casa.", "Estaba lloviendo cuando {|yo }salí de casa."),
            ex("es-b2-021", "Have you (tú) ever been to Mexico?", "¿{|tú }has estado alguna vez en México?", "¿Has estado {|tú }alguna vez en México?"),
            ex("es-b2-022", "They haven't called yet.", "Todavía no {han llamado|me han llamado}.", "Aún no han llamado.", "No han llamado todavía."),
            ex("es-b2-023", "Last year we travelled to Peru.", "El año pasado {|nosotros |nosotras }viajamos a Perú."),
            ex("es-b2-024", "I was having a shower when the phone rang.", "{|yo }me estaba duchando cuando sonó el teléfono.", "{|yo }estaba duchándome cuando sonó el teléfono.", "{|yo }me duchaba cuando sonó el teléfono."),
            ex("es-b2-025", "She told me she had lost her keys.", "{|ella }me dijo que había perdido las llaves.", "{|ella }me dijo que había perdido sus llaves."),
            ex("es-b2-026", "We used to go to the beach every summer.", "{Íbamos|Solíamos ir} a la playa todos los veranos.", "{Íbamos|Solíamos ir} a la playa cada verano."),
            ex("es-b2-027", "I had never seen such a big city before.", "Antes nunca había visto una ciudad tan grande.", "Nunca había visto una ciudad tan grande antes."),
            ex("es-b2-028", "This week I've worked a lot.", "Esta semana {|yo }he trabajado mucho."),
            ex("es-b2-029", "He was tired, so he went to bed early.", "{Estaba cansado|Él estaba cansado}, así que se acostó {temprano|pronto}.", "{Estaba cansado|Él estaba cansado}, por eso se acostó {temprano|pronto}."),
            ex("es-b2-030", "The house was old and had a big garden.", "La casa era {vieja|antigua} y tenía un jardín grande.", "La casa era {vieja|antigua} y tenía un gran jardín."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-03",
        title = "Pretérito imperfecto",
        level = "B2",
        category = PAST,
        explanation = """
            The imperfecto looks at the past from the inside, without showing its beginning or end.

            ## How to form it
            |           hablar    comer   vivir
            | yo        hablaba   comía   vivía
            | tú        hablabas  comías  vivías
            | él/ella   hablaba   comía   vivía
            | nosotros  hablábamos comíamos vivíamos
            | vosotros  hablabais comíais vivíais
            | ellos     hablaban  comían  vivían

            ## Only three irregular verbs
            - **ser**: era, eras, era, éramos, erais, eran
            - **ir**: iba, ibas, iba, íbamos, ibais, iban
            - **ver**: veía, veías, veía, veíamos, veíais, veían

            ## When to use it
            - Descriptions: La casa **era** pequeña.
            - Habits: Siempre **cenábamos** a las nueve.
            - Actions in progress: **Leía** cuando me llamaste.
            - Age and time: **Tenía** diez años. **Eran** las tres.
            - Polite requests: **Quería** un café, por favor.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-031", "When I was ten, I played football every day.", "Cuando tenía diez años, {|yo }jugaba al fútbol todos los días.", "Cuando tenía diez años, {|yo }jugaba al fútbol cada día."),
            ex("es-b2-032", "My grandmother made the best bread in town.", "Mi abuela hacía el mejor pan del pueblo."),
            ex("es-b2-033", "We always had dinner at nine.", "{|nosotros |nosotras }siempre cenábamos a las nueve."),
            ex("es-b2-034", "It was three o'clock in the morning.", "Eran las tres de la mañana.", "Eran las tres de la madrugada."),
            ex("es-b2-035", "I was reading when you (tú) called me.", "{Leía|Yo leía|Estaba leyendo|Yo estaba leyendo} cuando {|tú }me llamaste."),
            ex("es-b2-036", "The children were very happy.", "Los niños {estaban muy felices|estaban muy contentos|eran muy felices}."),
            ex("es-b2-037", "I'd like a coffee, please.", "Quería un café, por favor.", "Quisiera un café, por favor.", "Querría un café, por favor."),
            ex("es-b2-038", "We used to see each other on Sundays.", "{|nosotros |nosotras }nos veíamos los domingos."),
            ex("es-b2-039", "Before, people didn't have mobile phones.", "Antes, la gente no tenía {móviles|teléfonos móviles}.", "La gente no tenía {móviles|teléfonos móviles} antes."),
            ex("es-b2-040", "My parents worked in a small shop.", "Mis padres trabajaban en una tienda pequeña.", "Mis padres trabajaban en una pequeña tienda."),
            ex("es-b2-041", "There were many trees in the square.", "Había muchos árboles en la plaza."),
            ex("es-b2-042", "You (vosotros) used to go to that school, didn't you?", "{|vosotros |vosotras }ibais a ese colegio, ¿no?", "{|vosotros |vosotras }ibais a ese colegio, ¿verdad?"),
            ex("es-b2-043", "She didn't know the answer.", "{|ella }no sabía la respuesta."),
            ex("es-b2-044", "It was cold and it was snowing.", "Hacía frío y nevaba.", "Hacía frío y estaba nevando."),
            ex("es-b2-045", "He wanted to be a doctor when he was young.", "{Quería|Él quería} ser médico cuando era joven."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-04",
        title = "Making recommendations",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            Spanish offers several ways to advise someone, from gentle to firm.

            ## With the conditional
            - **Yo que tú**, hablaría con ella. If I were you, I'd talk to her.
            - **Yo en tu lugar**, no lo compraría. In your place, I wouldn't buy it.
            - **Deberías** descansar. You should rest.
            - **Podrías** preguntar. You could ask.

            ## With the subjunctive
            Verbs of advice change subject, so they need **que** + subjunctive:
            - **Te recomiendo que** vayas pronto. I recommend you go early.
            - **Te aconsejo que** no digas nada. I advise you not to say anything.
            - **Es mejor que** lo pienses. It's better that you think about it.

            ## General advice
            With no particular person in mind, use the infinitive:
            - **Es mejor** reservar. It's better to book.
            - **Conviene** llegar pronto. It's advisable to arrive early.
            - **Lo mejor es** preguntar. The best thing is to ask.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-046", "If I were you, I'd talk to her.", "Yo que tú, hablaría con ella.", "Yo en tu lugar, hablaría con ella."),
            ex("es-b2-047", "In your (tú) place, I wouldn't buy it.", "Yo en tu lugar, no lo compraría.", "Yo que tú, no lo compraría."),
            ex("es-b2-048", "You (tú) should rest more.", "{|tú }deberías descansar más."),
            ex("es-b2-049", "You (tú) could ask the teacher.", "{|tú }podrías preguntar {al profesor|a la profesora|al maestro|a la maestra}."),
            ex("es-b2-050", "I recommend you (tú) go early.", "{|yo }te recomiendo que {|tú }vayas {pronto|temprano}."),
            ex("es-b2-051", "I advise you (tú) not to say anything.", "{|yo }te aconsejo que {|tú }no digas nada."),
            ex("es-b2-052", "It's better that you (tú) think about it.", "Es mejor que {|tú }lo pienses."),
            ex("es-b2-053", "It's better to book a table.", "Es mejor reservar {|una }mesa."),
            ex("es-b2-054", "It's advisable to arrive early.", "Conviene llegar {pronto|temprano}."),
            ex("es-b2-055", "The best thing is to ask.", "Lo mejor es preguntar."),
            ex("es-b2-056", "I recommend you (vosotros) try the paella.", "{|yo }os recomiendo que {|vosotros |vosotras }probéis la paella."),
            ex("es-b2-057", "Why don't you (tú) see a doctor?", "¿Por qué no vas al médico?"),
            ex("es-b2-058", "You (usted) should drink more water.", "{|usted }debería beber más agua."),
            ex("es-b2-059", "I'd suggest you (tú) read the contract carefully.", "{|yo }te {sugeriría|recomendaría|aconsejaría} que {|tú }leyeras el contrato con cuidado.", "{|yo }te {sugiero|recomiendo|aconsejo} que {|tú }leas el contrato con cuidado."),
            ex("es-b2-060", "You'd (tú) better not go alone.", "Es mejor que {|tú }no vayas solo.", "Es mejor que {|tú }no vayas sola.", "Más vale que {|tú }no vayas {solo|sola}."),
        ),
    ),
    fromSubjunctiveCourse("Expanding on the subjunctive"),
    fromSubjunctiveCourse("Evaluations with the subjuntivo"),
    fromSubjunctiveCourse("Imperfecto de subjuntivo"),
    Lesson(
        id = "es-b2-lesson-05",
        title = "The infinitivo and the gerundio",
        level = "B2",
        category = FUNCTIONAL,
        explanation = """
            English often uses "-ing" where Spanish needs the infinitive, so this is a common trap.

            ## The infinitive as a noun
            - **Fumar** es malo. Smoking is bad.
            - Me encanta **viajar**. I love travelling.

            ## After a preposition: always the infinitive
            - Gracias por **venir**. Thanks for coming.
            - Salió sin **decir** nada. He left without saying anything.

            ## The gerundio: forming it
            **-ar** verbs take **-ando**, while **-er** and **-ir** verbs take **-iendo**. Some verbs change: **leyendo, durmiendo, diciendo, yendo**.

            ## When to use the gerundio
            - Actions in progress: **Estoy trabajando**.
            - How something is done: Aprendí **escuchando**. I learned by listening.
            - Continuing: **Sigue lloviendo**. It's still raining.
            - Duration: **Llevo** dos años **estudiando**. I've been studying for two years.

            ## Pronouns
            They attach to either form, or go before the conjugated verb:
            - Quiero **verlo** = **Lo** quiero ver.
            - Estoy **leyéndolo** = **Lo** estoy leyendo.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-061", "Smoking is bad for your health.", "Fumar es malo para la salud."),
            ex("es-b2-062", "I love travelling by train.", "Me encanta viajar en tren."),
            ex("es-b2-063", "Thanks for coming.", "Gracias por venir."),
            ex("es-b2-064", "He left without saying anything.", "{Se fue|Se marchó|Salió} sin decir nada."),
            ex("es-b2-065", "I'm working from home today.", "Hoy {|yo }estoy trabajando desde casa.", "{|yo }estoy trabajando desde casa hoy."),
            ex("es-b2-066", "I learned Spanish by listening to the radio.", "{|yo }aprendí español escuchando la radio."),
            ex("es-b2-067", "It's still raining.", "Sigue lloviendo.", "Todavía está lloviendo."),
            ex("es-b2-068", "I've been studying Spanish for two years.", "Llevo dos años estudiando español.", "{|yo }estudio español desde hace dos años."),
            ex("es-b2-069", "She went out running.", "{|ella }salió corriendo.", "{|ella }salió a correr."),
            ex("es-b2-070", "I want to see it.", "{|yo }quiero verlo.", "{|yo }lo quiero ver.", "{|yo }quiero verla.", "{|yo }la quiero ver."),
            ex("es-b2-071", "I'm reading it now.", "{Lo estoy leyendo|Estoy leyéndolo} ahora.", "Ahora {lo estoy leyendo|estoy leyéndolo}."),
            ex("es-b2-072", "Before going out, close the windows (tú).", "Antes de salir, cierra las ventanas."),
            ex("es-b2-073", "They were sleeping when I got home.", "{Estaban durmiendo|Dormían} cuando {|yo }llegué a casa."),
            ex("es-b2-074", "I'm tired of waiting.", "{Estoy cansado|Estoy cansada} de esperar."),
            ex("es-b2-075", "Walking through the park, I met an old friend.", "Paseando por el parque, me encontré con un viejo amigo.", "Paseando por el parque, me encontré a un viejo amigo.", "Caminando por el parque, me encontré con un viejo amigo."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-06",
        title = "Expressing unlikely conditions",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            Use this structure for situations that are improbable or contrary to the present reality.

            ## The structure
            **Si** + imperfecto de subjuntivo + condicional:
            - **Si tuviera** dinero, **viajaría** más. If I had money, I'd travel more.

            The clauses can swap places:
            - **Viajaría** más **si tuviera** dinero.

            ## Likely or unlikely?
            - **Si tengo** tiempo, iré. If I have time, I'll go. (quite possible)
            - **Si tuviera** tiempo, iría. If I had time, I'd go. (unlikely)

            ## Never the conditional after si
            In this type of sentence, si is never followed by the conditional:
            - Si **fuera** rico... (not "si sería")

            ## Common forms
            - **fuera** (ser, ir), **estuviera, tuviera, pudiera, supiera, hiciera, dijera, viviera**
            - **Si yo fuera tú**, no iría. If I were you, I wouldn't go.

            Spain also uses the **-se** forms (tuviese), which mean the same.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-076", "If I had money, I'd travel more.", "Si {|yo }{tuviera|tuviese} dinero, {|yo }viajaría más.", "{|yo }viajaría más si {|yo }{tuviera|tuviese} dinero."),
            ex("es-b2-077", "If I won the lottery, I'd buy a house by the sea.", "Si {|yo }{ganara|ganase|me tocara|me tocase} la lotería, {|yo }compraría una casa junto al mar.", "Si {|yo }{ganara|ganase|me tocara|me tocase} la lotería, {|yo }me compraría una casa junto al mar."),
            ex("es-b2-078", "If I were you, I wouldn't go.", "Si yo {fuera|fuese} tú, no iría."),
            ex("es-b2-079", "What would you (tú) do if you lost your job?", "¿Qué harías {|tú }si {|tú }{perdieras|perdieses} tu trabajo?", "¿Qué harías {|tú }si {|tú }{perdieras|perdieses} el trabajo?", "Si {|tú }{perdieras|perdieses} tu trabajo, ¿qué harías?"),
            ex("es-b2-080", "If it didn't rain so much, we'd go out.", "Si no {lloviera|lloviese} tanto, {|nosotros |nosotras }saldríamos."),
            ex("es-b2-081", "We'd buy a car if we could.", "{|nosotros |nosotras }compraríamos un coche si {|nosotros |nosotras }{pudiéramos|pudiésemos}.", "{|nosotros |nosotras }nos compraríamos un coche si {|nosotros |nosotras }{pudiéramos|pudiésemos}."),
            ex("es-b2-082", "If she spoke Spanish, she'd work here.", "Si {|ella }{hablara|hablase} español, {|ella }trabajaría aquí."),
            ex("es-b2-083", "If I knew the answer, I'd tell you (tú).", "Si {|yo }{supiera|supiese} la respuesta, {|yo }te la diría."),
            ex("es-b2-084", "If I lived in Madrid, I'd go to the theatre every week.", "Si {|yo }{viviera|viviese} en Madrid, {|yo }iría al teatro todas las semanas.", "Si {|yo }{viviera|viviese} en Madrid, {|yo }iría al teatro cada semana."),
            ex("es-b2-085", "If there were more buses, I wouldn't use the car.", "Si {hubiera|hubiese} más autobuses, {|yo }no usaría el coche.", "Si {hubiera|hubiese} más autobuses, {|yo }no utilizaría el coche."),
            ex("es-b2-086", "If they came earlier, they would find a seat.", "Si {|ellos |ellas }{vinieran|viniesen|llegaran|llegasen} antes, {|ellos |ellas }encontrarían sitio."),
            ex("es-b2-087", "I'd help you (tú) if I had time.", "{|yo }te ayudaría si {|yo }{tuviera|tuviese} tiempo."),
            ex("es-b2-088", "If it weren't so expensive, I'd buy it.", "Si no {fuera|fuese} tan caro, {|yo }lo compraría."),
            ex("es-b2-089", "If you (vosotros) studied more, you'd pass.", "Si {|vosotros |vosotras }{estudiarais|estudiaseis} más, {|vosotros |vosotras }aprobaríais."),
            ex("es-b2-090", "Where would you (tú) live if you could choose?", "¿Dónde vivirías {|tú }si {|tú }{pudieras|pudieses} elegir?", "Si {|tú }{pudieras|pudieses} elegir, ¿dónde vivirías?"),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-07",
        title = "Expressing certainty and uncertainty",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            The choice of mood shows how sure you are.

            ## Certainty: indicative
            - **Estoy seguro de que** viene. I'm sure he's coming.
            - **Es evidente que** tiene razón. It's obvious that she's right.
            - **Sin duda** es el mejor. It's undoubtedly the best.

            ## Denied certainty: subjunctive
            - **No estoy seguro de que** venga. I'm not sure he's coming.
            - **No es cierto que** sea tan caro. It isn't true that it's so expensive.
            - **Dudo que** llegue a tiempo. I doubt he'll arrive on time.

            ## Possibility: subjunctive
            - **Es posible que** llueva. It may rain.
            - **Puede (ser) que** esté en casa. She may be at home.

            ## Adverbs of doubt
            - **Quizás, tal vez** and **posiblemente** can take either mood. The subjunctive sounds less sure.
            - **A lo mejor** and **igual** always take the indicative: **A lo mejor** viene.

            ## Not knowing
            - **No sé si** vendrá. I don't know if he'll come. (indicative after si)
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-091", "I'm sure he's coming tomorrow.", "{Estoy seguro|Estoy segura} de que {|él }viene mañana.", "{Estoy seguro|Estoy segura} de que {|él }vendrá mañana."),
            ex("es-b2-092", "I'm not sure she's at home.", "No {estoy seguro|estoy segura} de que {|ella }esté en casa."),
            ex("es-b2-093", "It's obvious that you're (tú) right.", "Es {evidente|obvio} que {|tú }tienes razón."),
            ex("es-b2-094", "It isn't true that the flight is cancelled.", "No es {cierto|verdad} que el vuelo esté cancelado."),
            ex("es-b2-095", "I doubt he'll arrive on time.", "{|yo }dudo que {|él }llegue a tiempo."),
            ex("es-b2-096", "It may rain this afternoon.", "Es posible que llueva esta tarde.", "Puede que llueva esta tarde.", "Puede ser que llueva esta tarde."),
            ex("es-b2-097", "Maybe he's coming with his brother.", "A lo mejor {|él }viene con su hermano.", "{Quizás|Quizá|Tal vez} {|él }venga con su hermano."),
            ex("es-b2-098", "Perhaps they don't know the address.", "{Quizás|Quizá|Tal vez} {|ellos |ellas }no sepan la dirección.", "{Quizás|Quizá|Tal vez} {|ellos |ellas }no saben la dirección.", "A lo mejor {|ellos |ellas }no saben la dirección."),
            ex("es-b2-099", "I don't know if she'll come.", "{|yo }no sé si {|ella }vendrá.", "{|yo }no sé si {|ella }viene."),
            ex("es-b2-100", "I'm convinced it's a mistake.", "{Estoy convencido|Estoy convencida} de que es un error."),
            ex("es-b2-101", "It's clear that they don't want to help.", "Está claro que {|ellos |ellas }no quieren ayudar.", "Es evidente que {|ellos |ellas }no quieren ayudar."),
            ex("es-b2-102", "I don't think it's so difficult.", "{|yo }no creo que sea tan difícil."),
            ex("es-b2-103", "It's undoubtedly the best restaurant in the city.", "Sin duda es el mejor restaurante de la ciudad.", "Es sin duda el mejor restaurante de la ciudad."),
            ex("es-b2-104", "It's unlikely that they'll accept the offer.", "Es {improbable|poco probable} que {|ellos |ellas }acepten la oferta."),
            ex("es-b2-105", "I'm not at all sure about it.", "No {estoy seguro|estoy segura} de eso en absoluto.", "No {estoy nada seguro|estoy nada segura} de eso."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-08",
        title = "Imperativo afirmativo + pronouns",
        level = "B2",
        category = IMPERATIVE,
        explanation = """
            With affirmative commands, pronouns attach to the end of the verb.

            ## Forms
            |           hablar  comer  vivir
            | tú        habla   come   vive
            | usted     hable   coma   viva
            | vosotros  hablad  comed  vivid
            | ustedes   hablen  coman  vivan

            ## Irregular tú forms
            **di, haz, pon, sal, sé, ten, ven, ve**

            ## Pronoun order
            Indirect before direct, all joined to the verb, adding an accent when needed:
            - **Dámelo.** Give it to me.
            - **Cómpralo.** Buy it.

            ## Le and les become se
            Before **lo, la, los, las**, the pronouns **le** and **les** change to **se**:
            - **Díselo.** Tell it to him/her.

            ## Reflexive verbs
            - **Siéntate.** Sit down. (tú)
            - **Siéntese.** Sit down. (usted)
            - **Sentaos.** Sit down. (vosotros: the -d disappears)
            - **Siéntense.** Sit down. (ustedes)
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-106", "Give it to me (tú). (el libro)", "Dámelo."),
            ex("es-b2-107", "Buy it (tú). (la fruta)", "Cómprala."),
            ex("es-b2-108", "Tell it to him (tú). (el secreto)", "Díselo."),
            ex("es-b2-109", "Sit down (tú).", "Siéntate."),
            ex("es-b2-110", "Sit down (usted).", "Siéntese."),
            ex("es-b2-111", "Get up (vosotros).", "Levantaos."),
            ex("es-b2-112", "Come in (ustedes) and sit down.", "Pasen y siéntense.", "Entren y siéntense."),
            ex("es-b2-113", "Put it on the table (tú). (el vaso)", "Ponlo en la mesa.", "Ponlo sobre la mesa."),
            ex("es-b2-114", "Do it now (tú). (el trabajo)", "Hazlo ahora.", "Hazlo ya."),
            ex("es-b2-115", "Send me the photos (vosotros).", "Enviadme las fotos.", "Mandadme las fotos."),
            ex("es-b2-116", "Call me tomorrow (tú).", "Llámame mañana."),
            ex("es-b2-117", "Bring them to us (usted). (los platos)", "Tráiganoslos."),
            ex("es-b2-118", "Explain it to me again (usted). (el problema)", "Explíquemelo otra vez.", "Explíquemelo de nuevo."),
            ex("es-b2-119", "Wash your hands (tú).", "Lávate las manos."),
            ex("es-b2-120", "Listen to me carefully (vosotros).", "Escuchadme con atención.", "Escuchadme atentamente."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-09",
        title = "Imperativo negativo + pronouns",
        level = "B2",
        category = IMPERATIVE,
        explanation = """
            All negative commands use the present subjunctive, and pronouns go before the verb.

            ## Forms
            |           hablar     comer     vivir
            | tú        no hables  no comas  no vivas
            | usted     no hable   no coma   no viva
            | vosotros  no habléis no comáis no viváis
            | ustedes   no hablen  no coman  no vivan

            ## Pronoun position
            Compare the two:
            - **Dímelo.** Tell me. → **No me lo digas.** Don't tell me.
            - **Cómpralo.** Buy it. → **No lo compres.** Don't buy it.
            - **Siéntate.** Sit down. → **No te sientes.** Don't sit down.

            ## Le and les still become se
            - **No se lo digas.** Don't tell him/her.

            ## Common irregular forms
            - ir: **no vayas**
            - hacer: **no hagas**
            - poner: **no pongas**
            - salir: **no salgas**
            - venir: **no vengas**
            - ser: **no seas**
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-121", "Don't tell me (tú). (la noticia)", "No me la digas.", "No me lo digas."),
            ex("es-b2-122", "Don't buy it (tú). (el coche)", "No lo compres."),
            ex("es-b2-123", "Don't tell him (tú). (el secreto)", "No se lo digas."),
            ex("es-b2-124", "Don't sit down (tú).", "No te sientes."),
            ex("es-b2-125", "Don't worry (usted).", "No se preocupe."),
            ex("es-b2-126", "Don't get up (vosotros).", "No os levantéis."),
            ex("es-b2-127", "Don't go out without a coat (tú).", "No salgas sin abrigo."),
            ex("es-b2-128", "Don't do it (ustedes). (el trabajo)", "No lo hagan."),
            ex("es-b2-129", "Don't put it there (tú). (la silla)", "No la pongas ahí.", "No la pongas allí."),
            ex("es-b2-130", "Don't be so stubborn (tú).", "No seas tan {testarudo|testaruda|cabezota}."),
            ex("es-b2-131", "Don't send them to me (vosotros). (las fotos)", "No me las enviéis.", "No me las mandéis."),
            ex("es-b2-132", "Don't come so late (tú).", "No vengas tan tarde."),
            ex("es-b2-133", "Don't touch that (vosotros).", "No toquéis eso."),
            ex("es-b2-134", "Don't go alone at night (tú).", "No vayas {solo|sola} de noche.", "No vayas {solo|sola} por la noche."),
            ex("es-b2-135", "Don't forget it (usted). (el pasaporte)", "No lo olvide.", "No se olvide de él."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-10",
        title = "Expressing conditions",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            Beyond a simple **si**, Spanish has many ways of stating conditions.

            ## With si
            **Si** + present, then present, future or imperative:
            - **Si llueve**, nos quedamos en casa.
            - **Si puedes**, llámame.

            Never use the future or present subjunctive straight after this **si**.

            ## Other conditional links: subjunctive
            - **siempre que / siempre y cuando**: provided that
            - **con tal de que**: as long as
            - **a condición de que**: on condition that
            - **a menos que / a no ser que**: unless
            - **en caso de que**: in case

            ## With an infinitive or noun
            - **De** + infinitive: **De saberlo**, te lo diría. If I knew it, I'd tell you.
            - **En caso de** + noun: **En caso de** incendio, salgan por aquí.
            - **Sin** + noun: **Sin** tu ayuda, no puedo. Without your help, I can't.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-136", "If it rains, we'll stay at home.", "Si llueve, {|nosotros |nosotras }nos {quedamos|quedaremos|vamos a quedar} en casa."),
            ex("es-b2-137", "If you (tú) can, call me.", "Si {|tú }puedes, llámame."),
            ex("es-b2-138", "If I finish early, I'll go with you (vosotros).", "Si {|yo }termino {pronto|temprano}, {|yo }{iré|voy} con vosotros.", "Si {|yo }termino {pronto|temprano}, {|yo }{iré|voy} con vosotras."),
            ex("es-b2-139", "I'll go provided that you (tú) come with me.", "{|yo }{iré|voy} {siempre que|siempre y cuando|con tal de que|a condición de que} {|tú }vengas conmigo."),
            ex("es-b2-140", "You can use my car as long as you (tú) drive carefully.", "{Puedes usar|Puedes utilizar} mi coche {siempre que|siempre y cuando|con tal de que|mientras} conduzcas con cuidado."),
            ex("es-b2-141", "They'll sign on condition that the price goes down.", "{|ellos |ellas }{firmarán|van a firmar} a condición de que baje el precio.", "{|ellos |ellas }{firmarán|van a firmar} a condición de que el precio baje."),
            ex("es-b2-142", "I won't go unless you (tú) ask me to.", "{|yo }no {iré|voy} a menos que {|tú }me lo pidas.", "{|yo }no {iré|voy} a no ser que {|tú }me lo pidas."),
            ex("es-b2-143", "In case you (tú) need anything, call me.", "En caso de que {|tú }necesites algo, llámame."),
            ex("es-b2-144", "In case of fire, leave through here (ustedes).", "En caso de incendio, salgan por aquí."),
            ex("es-b2-145", "Without your (tú) help, I can't do it.", "Sin tu ayuda, no puedo hacerlo.", "Sin tu ayuda, no lo puedo hacer."),
            ex("es-b2-146", "If you (tú) don't study, you won't pass.", "Si {|tú }no estudias, {|tú }no {aprobarás|vas a aprobar|apruebas}."),
            ex("es-b2-147", "If you (usted) have any questions, ask me.", "Si {|usted }tiene {alguna duda|alguna pregunta|dudas|preguntas}, pregúnteme."),
            ex("es-b2-148", "If I knew it, I'd tell you (tú).", "De saberlo, te lo diría.", "Si {|yo }lo {supiera|supiese}, te lo diría."),
            ex("es-b2-149", "We'll have dinner outside unless it gets cold.", "{|nosotros |nosotras }{cenaremos|cenamos|vamos a cenar} fuera a menos que haga frío.", "{|nosotros |nosotras }{cenaremos|cenamos|vamos a cenar} fuera a no ser que haga frío."),
            ex("es-b2-150", "If you're (vosotros) hungry, there's food in the fridge.", "Si {|vosotros |vosotras }tenéis hambre, hay comida en la nevera.", "Si {|vosotros |vosotras }tenéis hambre, hay comida en el frigorífico."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-11",
        title = "Expressing purpose",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            Purpose clauses depend on whether the subject stays the same.

            ## Same subject: infinitive
            - Estudio **para aprobar**. I'm studying to pass.

            ## Different subject: que + subjunctive
            - Estudio **para que** mis padres **estén** contentos. I'm studying so that my parents are happy.

            ## In the past
            Use the imperfect subjunctive after a past main verb:
            - Te llamé **para que supieras** la verdad. I called you so that you'd know the truth.

            ## Other expressions
            - **a fin de (que)** and **con el fin de (que)**: with the aim of (more formal)
            - **con el objetivo de**: with the objective of
            - **a** + infinitive after movement verbs: Vengo **a hablar** contigo.
            - **a que** + subjunctive: Vengo **a que me ayudes**.

            ## Asking about purpose
            - **¿Para qué** lo quieres? What do you want it for?
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-151", "I'm studying in order to pass the exam.", "{|yo }estudio para aprobar el examen.", "{|yo }estoy estudiando para aprobar el examen."),
            ex("es-b2-152", "I'm saving so that my children can go to university.", "{|yo }ahorro para que mis hijos puedan ir a la universidad.", "{|yo }estoy ahorrando para que mis hijos puedan ir a la universidad."),
            ex("es-b2-153", "I called you (tú) so that you'd know the truth.", "{|yo }te llamé para que {|tú }{supieras|supieses} la verdad."),
            ex("es-b2-154", "I've come to talk to you (tú).", "{|yo }he venido a hablar contigo.", "{|yo }vengo a hablar contigo."),
            ex("es-b2-155", "I've come so that you (tú) can help me.", "{|yo }he venido {a que|para que} {|tú }me ayudes.", "{|yo }vengo {a que|para que} {|tú }me ayudes."),
            ex("es-b2-156", "What do you (tú) want it for?", "¿Para qué lo quieres?", "¿Para qué la quieres?"),
            ex("es-b2-157", "We closed the window so that the noise wouldn't come in.", "{|nosotros |nosotras }cerramos la ventana para que no {entrara|entrase} el ruido."),
            ex("es-b2-158", "I'll explain it to you (tú) so that you understand it.", "{|yo }te lo {explico|explicaré|voy a explicar} para que {|tú }lo entiendas."),
            ex("es-b2-159", "The company was created with the aim of helping young people.", "La empresa se creó {con el fin de|a fin de|con el objetivo de} ayudar a los jóvenes."),
            ex("es-b2-160", "Speak louder (tú) so that everybody can hear you.", "Habla más alto para que todos te {oigan|puedan oír}.", "Habla más alto para que todo el mundo te {oiga|pueda oír}."),
            ex("es-b2-161", "I got up early to catch the first train.", "{|yo }me levanté {temprano|pronto} para {coger|tomar} el primer tren."),
            ex("es-b2-162", "She gave me the key so that I could get in.", "{|ella }me dio la llave para que {|yo }{pudiera|pudiese} entrar."),
            ex("es-b2-163", "We're going to the market to buy fruit.", "{|nosotros |nosotras }vamos al mercado a comprar fruta.", "{|nosotros |nosotras }vamos al mercado para comprar fruta."),
            ex("es-b2-164", "I'm telling you (tú) this so that you don't make the same mistake.", "{|yo }te digo esto para que {|tú }no {cometas|hagas} el mismo error.", "{|yo }te lo digo para que {|tú }no {cometas|hagas} el mismo error."),
            ex("es-b2-165", "They left early in order not to miss the plane.", "{Salieron|Se fueron} {temprano|pronto} para no perder el avión."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-12",
        title = "Expressing unlikely wishes",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            The imperfect subjunctive turns a hope into a far-fetched wish.

            ## Ojalá
            - **Ojalá venga** mañana. I hope she comes tomorrow. (possible)
            - **Ojalá viniera** mañana. If only she'd come tomorrow. (unlikely)
            - **Ojalá hubiera venido.** If only she had come. (too late)

            ## Quién
            **Quién** + imperfect subjunctive is a wish for yourself:
            - **¡Quién pudiera** vivir aquí! If only I could live here!

            ## Si + imperfect subjunctive
            - **¡Si tuviera** más tiempo! If only I had more time!

            ## With the conditional
            **Me gustaría, querría** and **preferiría** are followed by **que** + imperfect subjunctive when the subject changes:
            - **Me gustaría que** vinieras. I'd like you to come.
            - **Me gustaría** ir. I'd like to go. (same subject)

            ## Other useful phrases
            - **Qué más quisiera yo.** I only wish.
            - **Sería genial que** + imperfect subjunctive: It would be great if...
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-166", "If only she'd come tomorrow!", "¡Ojalá {|ella }{viniera|viniese} mañana!"),
            ex("es-b2-167", "I wish I could live here.", "Ojalá {|yo }{pudiera|pudiese} vivir aquí.", "¡Quién {pudiera|pudiese} vivir aquí!"),
            ex("es-b2-168", "If only I had more time!", "¡Si {|yo }{tuviera|tuviese} más tiempo!", "¡Ojalá {|yo }{tuviera|tuviese} más tiempo!"),
            ex("es-b2-169", "I'd like you (tú) to come with us.", "Me gustaría que {|tú }{vinieras|vinieses} con nosotros.", "Me gustaría que {|tú }{vinieras|vinieses} con nosotras."),
            ex("es-b2-170", "I wish it were Friday.", "Ojalá {fuera|fuese} viernes."),
            ex("es-b2-171", "I wish I knew how to play the piano.", "Ojalá {|yo }{supiera|supiese} tocar el piano.", "¡Quién {supiera|supiese} tocar el piano!"),
            ex("es-b2-172", "We'd prefer them not to know.", "{|nosotros |nosotras }preferiríamos que {|ellos |ellas }no lo {supieran|supiesen}."),
            ex("es-b2-173", "It would be great if we could see each other more.", "Sería {genial|estupendo|fantástico} que {|nosotros |nosotras }{pudiéramos|pudiésemos} vernos más.", "Sería {genial|estupendo|fantástico} que nos {viéramos|viésemos} más."),
            ex("es-b2-174", "I wish it didn't rain so much here.", "Ojalá no {lloviera|lloviese} tanto aquí."),
            ex("es-b2-175", "I'd love to live by the sea.", "Me encantaría vivir junto al mar.", "Me encantaría vivir cerca del mar."),
            ex("es-b2-176", "I'd love you (vosotros) to meet my family.", "Me encantaría que {|vosotros |vosotras }{conocierais|conocieseis} a mi familia."),
            ex("es-b2-177", "I wish I were younger.", "Ojalá {|yo }{fuera|fuese} más joven."),
            ex("es-b2-178", "If only we had a bigger house!", "¡Si {|nosotros |nosotras }{tuviéramos|tuviésemos} una casa más grande!", "¡Ojalá {|nosotros |nosotras }{tuviéramos|tuviésemos} una casa más grande!"),
            ex("es-b2-179", "I wish you (tú) lived closer.", "Ojalá {|tú }{vivieras|vivieses} más cerca."),
            ex("es-b2-180", "I only wish!", "¡Qué más quisiera yo!", "¡Ya quisiera yo!"),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-13",
        title = "“Antes de” and “después de”",
        level = "B2",
        category = "Adverbs",
        explanation = """
            These expressions change form depending on what follows them.

            ## With a noun
            - **Antes de** la cena. Before dinner.
            - **Después de** la reunión. After the meeting.

            ## Same subject: infinitive
            - **Antes de salir**, apago la luz. Before going out, I turn off the light.
            - **Después de comer**, descanso. After eating, I rest.

            ## Different subject: que + verb
            **Antes de que** always takes the subjunctive:
            - Llámame **antes de que salga**. Call me before I leave.

            **Después de que** takes the subjunctive for the future, and the indicative for the past:
            - Hablaremos **después de que vengan**. We'll talk after they come.
            - Hablamos **después de que vinieron**. We talked after they came.

            ## After a past main verb
            - Me fui **antes de que llegaran**. I left before they arrived.

            ## As adverbs alone
            - **Antes** vivía aquí. I used to live here.
            - **Después** te llamo. I'll call you afterwards.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-181", "Before dinner, we have a drink.", "Antes de la cena, {|nosotros |nosotras }tomamos algo.", "Antes de cenar, {|nosotros |nosotras }tomamos algo."),
            ex("es-b2-182", "After the meeting, I'll send you (tú) the report.", "Después de la reunión, {|yo }te {enviaré|envío|mandaré|mando} el informe."),
            ex("es-b2-183", "Before going out, I turn off the light.", "Antes de salir, {|yo }apago la luz."),
            ex("es-b2-184", "After eating, I rest for a while.", "Después de comer, {|yo }descanso un rato."),
            ex("es-b2-185", "Call me (tú) before I leave.", "Llámame antes de que {|yo }{salga|me vaya|me marche}."),
            ex("es-b2-186", "We'll talk after they come.", "{|nosotros |nosotras }{hablaremos|vamos a hablar} después de que {|ellos |ellas }{vengan|lleguen}."),
            ex("es-b2-187", "We talked after they came.", "{|nosotros |nosotras }hablamos después de que {|ellos |ellas }{vinieron|llegaron}."),
            ex("es-b2-188", "I left before they arrived.", "{|yo }me fui antes de que {|ellos |ellas }{llegaran|llegasen}.", "{|yo }me marché antes de que {|ellos |ellas }{llegaran|llegasen}."),
            ex("es-b2-189", "Wash your hands (tú) before eating.", "Lávate las manos antes de comer."),
            ex("es-b2-190", "I used to live here.", "Antes {|yo }vivía aquí."),
            ex("es-b2-191", "I'll call you (tú) afterwards.", "Después te llamo.", "Después te llamaré.", "Te llamo después.", "Te llamaré después."),
            ex("es-b2-192", "Before you (tú) say anything, listen to me.", "Antes de que {|tú }digas nada, escúchame.", "Antes de que {|tú }digas algo, escúchame."),
            ex("es-b2-193", "After working all day, he was exhausted.", "Después de trabajar todo el día, {|él }estaba agotado."),
            ex("es-b2-194", "Finish it (tú) before your boss arrives.", "Termínalo antes de que llegue tu jefe.", "Termínalo antes de que tu jefe llegue.", "Termínala antes de que llegue tu jefe."),
            ex("es-b2-195", "After having lunch, we went for a walk.", "Después de comer, {|nosotros |nosotras }{fuimos a dar un paseo|dimos un paseo}.", "Después de almorzar, {|nosotros |nosotras }{fuimos a dar un paseo|dimos un paseo}."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-14",
        title = "Proposals and suggestions",
        level = "B2",
        category = FUNCTIONAL,
        explanation = """
            Here are the most natural ways to suggest plans, and to respond to them.

            ## Questions in the present
            - **¿Por qué no** vamos al cine? Why don't we go to the cinema?
            - **¿Qué tal si** cenamos fuera? How about we eat out?
            - **¿Y si** llamamos a Ana? What if we call Ana?
            - **¿Te apetece** ir a la playa? Do you fancy going to the beach?
            - **¿Vamos** a tomar algo? Shall we go for a drink?

            ## Proposing with verbs
            - **Propongo que** + subjunctive: **Propongo que empecemos** ya.
            - **Sugiero que** + subjunctive: **Sugiero que lo dejemos** para mañana.
            - **Podríamos** + infinitive: We could...

            ## Accepting
            - **¡Buena idea!** / **¡Vale!** / **Me parece bien.**

            ## Declining politely
            - **Me encantaría, pero** no puedo.
            - **Lo siento, es que** tengo que trabajar.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-196", "Why don't we go to the cinema?", "¿Por qué no vamos al cine?"),
            ex("es-b2-197", "How about we eat out?", "¿Qué tal si {cenamos|comemos} fuera?", "¿Y si {cenamos|comemos} fuera?"),
            ex("es-b2-198", "What if we call Ana?", "¿Y si llamamos a Ana?"),
            ex("es-b2-199", "Do you (tú) fancy going to the beach?", "¿Te apetece ir a la playa?"),
            ex("es-b2-200", "Shall we go for a drink?", "¿Vamos a tomar algo?", "¿Tomamos algo?"),
            ex("es-b2-201", "I propose that we start now.", "{|yo }propongo que {empecemos|comencemos} {ya|ahora}."),
            ex("es-b2-202", "I suggest we leave it until tomorrow.", "{|yo }sugiero que lo dejemos para mañana."),
            ex("es-b2-203", "We could have dinner at my place.", "{|nosotros |nosotras }podríamos cenar en mi casa."),
            ex("es-b2-204", "Good idea! That sounds fine to me.", "¡Buena idea! Me parece bien."),
            ex("es-b2-205", "I'd love to, but I can't.", "Me encantaría, pero no puedo."),
            ex("es-b2-206", "I'm sorry, I have to work.", "Lo siento, es que {tengo que trabajar|tengo trabajo}.", "Lo siento, tengo que trabajar."),
            ex("es-b2-207", "How about going for a walk this afternoon?", "¿Qué tal si {vamos a dar|damos} un paseo esta tarde?", "¿Qué te parece si {vamos a dar|damos} un paseo esta tarde?"),
            ex("es-b2-208", "Do you (vosotros) feel like coming with us?", "¿Os apetece venir con nosotros?", "¿Os apetece venir con nosotras?"),
            ex("es-b2-209", "I suggest you (usted) speak to the manager.", "{|yo }le sugiero que {|usted }hable con {el director|la directora|el gerente|la gerente|el encargado|la encargada}."),
            ex("es-b2-210", "Why don't we meet on Saturday?", "¿Por qué no {quedamos|nos vemos} el sábado?"),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-15",
        title = "Commands and reported speech",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            A reported command becomes **que** + subjunctive.

            ## Present reporting verb
            Use the present subjunctive:
            - «Ven.» → Me dice que **venga**. She tells me to come.
            - «No salgas.» → Me pide que no **salga**.

            ## Past reporting verb
            Use the imperfect subjunctive:
            - «Ven.» → Me dijo que **viniera**. She told me to come.

            ## Reported statements
            Tenses shift back when the reporting verb is in the past:
            - «Estoy cansado.» → Dijo que **estaba** cansado.
            - «He comido.» → Dijo que **había comido**.
            - «Comí.» → Dijo que **había comido** (or **comió**).
            - «Iré.» → Dijo que **iría**.

            ## Other changes
            - **hoy** → aquel día, **mañana** → al día siguiente, **ayer** → el día anterior
            - **aquí** → allí, **este** → aquel

            ## Reported questions
            - «¿Vienes?» → Me preguntó **si** iba.
            - «¿Dónde vives?» → Me preguntó **dónde** vivía.

            ## Decir with two meanings
            Decir + indicative reports information. Decir + subjunctive reports a command.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-211", "She tells me to wait.", "{|ella }me dice que {|yo }espere."),
            ex("es-b2-212", "She told me to come.", "{|ella }me dijo que {|yo }{viniera|viniese}."),
            ex("es-b2-213", "He asks me not to go out.", "{|él }me pide que {|yo }no salga."),
            ex("es-b2-214", "My mother told us to be quiet.", "Mi madre nos dijo que nos {calláramos|callásemos}.", "Mi madre nos dijo que {estuviéramos|estuviésemos} {callados|calladas}."),
            ex("es-b2-215", "He said he was tired.", "{|él }dijo que estaba cansado."),
            ex("es-b2-216", "She said she had eaten.", "{|ella }dijo que había comido.", "{|ella }dijo que comió."),
            ex("es-b2-217", "They said they would come.", "{|ellos |ellas }dijeron que vendrían."),
            ex("es-b2-218", "He asked me if I was coming.", "{|él }me preguntó si {|yo }iba.", "{|él }me preguntó si {|yo }venía."),
            ex("es-b2-219", "She asked me where I lived.", "{|ella }me preguntó dónde vivía {|yo}.", "{|ella }me preguntó dónde {|yo }vivía."),
            ex("es-b2-220", "The doctor told me to rest.", "El médico me dijo que {descansara|descansase}.", "La médica me dijo que {descansara|descansase}."),
            ex("es-b2-221", "I asked them to wait for me.", "{|yo }les pedí que me {esperaran|esperasen}."),
            ex("es-b2-222", "He told me he would call the following day.", "{|él }me dijo que {|él }llamaría al día siguiente."),
            ex("es-b2-223", "She said it was very cold there.", "{|ella }dijo que allí hacía mucho frío.", "{|ella }dijo que hacía mucho frío allí."),
            ex("es-b2-224", "They told us not to touch anything.", "{|ellos |ellas }nos dijeron que no {tocáramos|tocásemos} nada."),
            ex("es-b2-225", "The teacher wants us to read this chapter.", "{El profesor|La profesora} quiere que {|nosotros |nosotras }leamos este capítulo."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-16",
        title = "Futuro perfecto",
        level = "B2",
        category = "Verbs: Future",
        explanation = """
            The future perfect looks back from a point in the future, and can also express guesses about the recent past.

            ## How to form it
            The future of **haber** + past participle:
            | yo        habré     hablado
            | tú        habrás    comido
            | él/ella   habrá     vivido
            | nosotros  habremos  hecho
            | vosotros  habréis   visto
            | ellos     habrán    dicho

            ## Completed by a future time
            - **Para las ocho habré terminado.** I'll have finished by eight.
            - **Dentro de un año habremos ahorrado** bastante.

            ## Guessing about the recent past
            It corresponds to the pretérito perfecto, and often means "must have" or "I suppose":
            - ¿Dónde está Ana? **Habrá salido.** She must have gone out.
            - **Se habrá olvidado.** He must have forgotten.

            ## Time expressions
            - **para** + time: by
            - **dentro de**: in, within
            - **cuando** + subjunctive: **Cuando llegues, habré cenado.**

            ## Irregular participles
            **hecho, visto, dicho, escrito, puesto, vuelto, roto, abierto**
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-226", "I'll have finished by eight.", "Para las ocho {|yo }habré terminado.", "{|yo }habré terminado para las ocho."),
            ex("es-b2-227", "In a year, we'll have saved enough.", "Dentro de un año, {|nosotros |nosotras }habremos ahorrado {suficiente|bastante}.", "En un año, {|nosotros |nosotras }habremos ahorrado {suficiente|bastante}."),
            ex("es-b2-228", "She must have gone out.", "{|ella }habrá salido."),
            ex("es-b2-229", "He must have forgotten.", "Se habrá olvidado.", "Se le habrá olvidado.", "Él se habrá olvidado."),
            ex("es-b2-230", "When you (tú) arrive, I'll have had dinner.", "Cuando {|tú }llegues, {|yo }habré cenado.", "Cuando {|tú }llegues, {|yo }ya habré cenado."),
            ex("es-b2-231", "By June, they'll have finished the building work.", "Para junio, {|ellos |ellas }habrán {terminado|acabado} las obras."),
            ex("es-b2-232", "Why isn't she answering? She must have gone to bed.", "¿Por qué no contesta? {|Ella }se habrá acostado.", "¿Por qué no responde? {|Ella }se habrá acostado."),
            ex("es-b2-233", "By tomorrow, you (tú) will have read the whole book.", "Para mañana, {|tú }habrás leído todo el libro.", "Para mañana, {|tú }habrás leído el libro entero."),
            ex("es-b2-234", "They must have missed the train.", "{|ellos |ellas }habrán perdido el tren."),
            ex("es-b2-235", "By then, you (vosotros) will have returned.", "Para entonces, {|vosotros |vosotras }habréis vuelto.", "Para entonces, {|vosotros |vosotras }habréis regresado."),
            ex("es-b2-236", "I'll have written the report before you (tú) arrive.", "{|yo }habré escrito el informe antes de que {|tú }llegues."),
            ex("es-b2-237", "Who will have broken the window?", "¿Quién habrá roto la ventana?"),
            ex("es-b2-238", "Someone must have left the door open.", "Alguien habrá dejado la puerta abierta."),
            ex("es-b2-239", "By the end of the month, we'll have sold the house.", "Para final de mes, {|nosotros |nosotras }habremos vendido la casa.", "A finales de mes, {|nosotros |nosotras }habremos vendido la casa.", "Para finales de mes, {|nosotros |nosotras }habremos vendido la casa."),
            ex("es-b2-240", "What will have happened?", "¿Qué habrá pasado?", "¿Qué habrá ocurrido?", "¿Qué habrá sucedido?"),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-17",
        title = "Adjectives + Ser y Estar",
        level = "B2",
        category = "Verbs: Ser y Estar",
        explanation = """
            Some adjectives change meaning entirely depending on the verb.

            ## Ser: inherent qualities. Estar: states.
            - Es **guapa**. She's good-looking.
            - Está **guapa**. She looks good. (today)

            ## Adjectives that change meaning
            - **ser listo**: to be clever. **estar listo**: to be ready
            - **ser aburrido**: to be boring. **estar aburrido**: to be bored
            - **ser rico**: to be rich. **estar rico**: to be tasty
            - **ser malo**: to be bad. **estar malo**: to be ill
            - **ser bueno**: to be good. **estar bueno**: to taste good
            - **ser verde**: to be green. **estar verde**: to be unripe
            - **ser vivo**: to be sharp. **estar vivo**: to be alive
            - **ser orgulloso**: to be arrogant. **estar orgulloso**: to be proud
            - **ser abierto**: to be open-minded. **estar abierto**: to be open
            - **ser seguro**: to be safe. **estar seguro**: to be sure
            - **ser delicado**: to be fragile. **estar delicado**: to be in poor health

            ## Always with estar
            **contento, enfermo, cansado, lleno, vacío, roto, muerto, de acuerdo**
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-241", "My sister is very clever.", "Mi hermana es muy lista."),
            ex("es-b2-242", "Are you (tú) ready? We're leaving.", "¿{Estás listo|Estás lista}? Nos vamos."),
            ex("es-b2-243", "This film is boring.", "Esta película es aburrida."),
            ex("es-b2-244", "I'm bored at home.", "{Estoy aburrido|Estoy aburrida} en casa."),
            ex("es-b2-245", "His family is very rich.", "Su familia es muy rica."),
            ex("es-b2-246", "This soup is delicious.", "Esta sopa está muy rica.", "Esta sopa está riquísima.", "Esta sopa está deliciosa."),
            ex("es-b2-247", "Smoking is bad.", "Fumar es malo."),
            ex("es-b2-248", "My son is ill today.", "Hoy mi hijo está malo.", "Mi hijo está malo hoy.", "Hoy mi hijo está enfermo."),
            ex("es-b2-249", "These bananas are not ripe.", "Estos plátanos están verdes.", "Estos plátanos no están maduros."),
            ex("es-b2-250", "I'm very proud of you (vosotros).", "{Estoy muy orgulloso|Estoy muy orgullosa} de vosotros.", "{Estoy muy orgulloso|Estoy muy orgullosa} de vosotras."),
            ex("es-b2-251", "He's so arrogant that he never apologises.", "{Es|Él es} tan orgulloso que nunca pide perdón.", "{Es|Él es} tan orgulloso que nunca se disculpa."),
            ex("es-b2-252", "The museum is open until six.", "El museo está abierto hasta las seis."),
            ex("es-b2-253", "I'm sure of it.", "{Estoy seguro|Estoy segura} de eso.", "{Estoy seguro|Estoy segura} de ello."),
            ex("es-b2-254", "This neighbourhood is very safe.", "Este barrio es muy seguro."),
            ex("es-b2-255", "The bottle is empty.", "La botella está vacía."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-18",
        title = "Consecutive sentences",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            Consecutive sentences state the result of something.

            ## Simple links
            - **así que**: so
            - **por eso / por lo tanto / por consiguiente**: therefore
            - **de modo que / de manera que**: so
            - **entonces**: so, then

            ## Intensity: so much that
            - **tan** + adjective/adverb + **que**: Es **tan** caro **que** no lo compro.
            - **tanto/a/os/as** + noun + **que**: Tengo **tanto** trabajo **que** no salgo.
            - verb + **tanto que**: Comí **tanto que** me dolía la barriga.
            - **tal** + noun + **que**: Hacía **tal** calor **que** no dormí.
            - **un/a** + noun + **tan** + adj + **que**: Es **una** casa **tan** grande **que** me pierdo.

            ## Negative results
            A denied result takes the subjunctive:
            - No es **tan** caro **que** no **podamos** comprarlo. It isn't so expensive that we can't buy it.

            ## With the subjunctive for purpose
            **De modo que** + subjunctive means purpose rather than result:
            - Habla **de modo que** te entiendan. Speak so that they understand you.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-256", "It was raining, so we stayed at home.", "Llovía, así que {|nosotros |nosotras }nos quedamos en casa.", "Estaba lloviendo, así que {|nosotros |nosotras }nos quedamos en casa.", "Llovía, por eso {|nosotros |nosotras }nos quedamos en casa."),
            ex("es-b2-257", "I'm tired, so I'm going to bed.", "{Estoy cansado|Estoy cansada}, así que {|yo }me voy a la cama.", "{Estoy cansado|Estoy cansada}, así que {|yo }me voy a dormir.", "{Estoy cansado|Estoy cansada}, por eso {|yo }me voy a la cama."),
            ex("es-b2-258", "It's so expensive that I'm not buying it.", "Es tan caro que {|yo }no lo compro.", "Es tan caro que {|yo }no voy a comprarlo.", "Es tan caro que {|yo }no lo voy a comprar."),
            ex("es-b2-259", "I have so much work that I never go out.", "{|yo }tengo tanto trabajo que {|yo }nunca salgo.", "{|yo }tengo tanto trabajo que {|yo }no salgo nunca."),
            ex("es-b2-260", "I ate so much that my stomach hurt.", "{|yo }comí tanto que me dolía la barriga.", "{|yo }comí tanto que me dolía el estómago."),
            ex("es-b2-261", "It was so hot that I didn't sleep.", "Hacía tal calor que {|yo }no dormí.", "Hacía tanto calor que {|yo }no dormí.", "Hacía tanto calor que {|yo }no pude dormir."),
            ex("es-b2-262", "It's such a big house that I get lost.", "Es una casa tan grande que me pierdo."),
            ex("es-b2-263", "It isn't so expensive that we can't buy it.", "No es tan caro que {|nosotros |nosotras }no {podamos comprarlo|lo podamos comprar}."),
            ex("es-b2-264", "He hasn't studied; therefore he won't pass.", "No ha estudiado; por lo tanto, no {aprobará|va a aprobar}.", "No ha estudiado; por tanto, no {aprobará|va a aprobar}.", "No ha estudiado, así que no {aprobará|va a aprobar}."),
            ex("es-b2-265", "There were so many people that we left.", "Había tanta gente que {nos fuimos|nos marchamos}."),
            ex("es-b2-266", "She was speaking so quickly that I didn't understand her.", "{|ella }hablaba tan {rápido|deprisa} que {|yo }no la entendía."),
            ex("es-b2-267", "Speak (tú) so that they understand you.", "Habla de modo que te entiendan.", "Habla de manera que te entiendan.", "Habla de forma que te entiendan."),
            ex("es-b2-268", "The train was late, so I missed the meeting.", "El tren llegó tarde, así que {|yo }me perdí la reunión.", "El tren se retrasó, así que {|yo }me perdí la reunión."),
            ex("es-b2-269", "The road was closed, so we had to go back.", "La carretera estaba cortada, de modo que {|nosotros |nosotras }tuvimos que volver.", "La carretera estaba cerrada, de modo que {|nosotros |nosotras }tuvimos que volver.", "La carretera estaba {cortada|cerrada}, así que {|nosotros |nosotras }tuvimos que volver."),
            ex("es-b2-270", "I'm so happy that I could cry.", "{Estoy tan contento|Estoy tan contenta} que podría llorar."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-19",
        title = "Relative clauses",
        level = "B2",
        category = "Referencing",
        explanation = """
            Relative clauses add information about a noun. In Spanish, the relative word can never be left out.

            ## Que
            The most common relative:
            - El libro **que** leo. The book (that) I'm reading.

            ## Preposition + article + que
            - La casa **en la que** vivo. The house I live in.
            - El amigo **con el que** viajo. The friend I travel with.
            - Las razones **por las que** me fui.

            ## El cual
            More formal, especially after longer prepositions:
            - El edificio **delante del cual** aparqué.

            ## Quien
            Only for people, mainly after a preposition:
            - El chico **con quien** hablé.

            ## Lo que
            Refers to an idea:
            - **Lo que** dices es cierto. What you're saying is true.
            - Llegó tarde, **lo que** me molestó.

            ## Cuyo
            Whose, agreeing with the thing owned:
            - La vecina **cuyo** hijo es médico.

            ## Donde, cuando, como
            - El pueblo **donde** nací.

            ## Indicative or subjunctive
            - Busco el piso **que tiene** terraza. (a specific flat)
            - Busco un piso **que tenga** terraza. (any such flat)
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-271", "The book I'm reading is very long.", "El libro que {|yo }{estoy leyendo|leo} es muy largo."),
            ex("es-b2-272", "The house I live in is very old.", "La casa {en la que|en que|donde} {|yo }vivo es muy {antigua|vieja}."),
            ex("es-b2-273", "The friend I travel with is from Cádiz.", "{El amigo con el que|El amigo con quien|La amiga con la que|La amiga con quien} {|yo }viajo es de Cádiz."),
            ex("es-b2-274", "These are the reasons why I left.", "Estas son las razones por las que {|yo }me fui.", "Estas son las razones por las que {|yo }me marché."),
            ex("es-b2-275", "The boy I spoke to is my cousin.", "El chico con {quien|el que} {|yo }hablé es mi primo."),
            ex("es-b2-276", "What you (tú) are saying is true.", "Lo que {|tú }dices es {cierto|verdad}."),
            ex("es-b2-277", "He arrived late, which annoyed me.", "{|él }llegó tarde, lo que me molestó.", "{|él }llegó tarde, lo cual me molestó."),
            ex("es-b2-278", "The neighbour whose son is a doctor lives upstairs.", "La vecina cuyo hijo es médico vive arriba.", "El vecino cuyo hijo es médico vive arriba."),
            ex("es-b2-279", "That's the village where I was born.", "Ese es el pueblo donde {|yo }nací.", "Ese es el pueblo en el que {|yo }nací."),
            ex("es-b2-280", "I'm looking for someone who speaks Basque.", "{|yo }busco a alguien que hable euskera.", "{|yo }busco a alguien que hable vasco."),
            ex("es-b2-281", "The company I work for is German.", "La empresa {para la que|en la que|donde} {|yo }trabajo es alemana."),
            ex("es-b2-282", "I don't understand what you (tú) want.", "{|yo }no entiendo lo que {|tú }quieres."),
            ex("es-b2-283", "The people who live here are very friendly.", "La gente que vive aquí es muy {amable|simpática}.", "Las personas que viven aquí son muy {amables|simpáticas}."),
            ex("es-b2-284", "The building in front of which I parked is a bank.", "El edificio delante del cual {|yo }aparqué es un banco.", "El edificio delante del que {|yo }aparqué es un banco."),
            ex("es-b2-285", "Those who want to can stay.", "Los que quieran pueden quedarse.", "Quienes quieran pueden quedarse."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-20",
        title = "Concessive phrases",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            Concessive phrases present an obstacle that doesn't stop the main action.

            ## Aunque
            - **Aunque llueve**, salgo. Although it's raining, I'm going out. (a fact)
            - **Aunque llueva**, saldré. Even if it rains, I'll go out. (a possibility)
            - **Aunque me lo pidieras**, no iría. Even if you asked me, I wouldn't go.

            ## A pesar de
            - **A pesar de** + noun: **A pesar de** la lluvia.
            - **A pesar de** + infinitive: **A pesar de** estar cansado.
            - **A pesar de que** + verb: **A pesar de que** llueve.

            ## Other phrases
            - **pese a (que)**: despite (formal)
            - **aun así**: even so
            - **por mucho/más que** + verb: however much
            - **por muy** + adjective **que** + subjunctive: however...
            - **si bien**: although (formal)

            ## Examples
            - **Por mucho que** estudio, no apruebo. However much I study, I don't pass.
            - **Por muy** caro **que** sea, lo compraré. However expensive it is, I'll buy it.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-286", "Although it's raining, I'm going out.", "Aunque llueve, {|yo }salgo.", "Aunque está lloviendo, {|yo }salgo.", "Aunque llueve, {|yo }voy a salir."),
            ex("es-b2-287", "Even if it rains, I'll go out.", "Aunque llueva, {|yo }saldré.", "Aunque llueva, {|yo }voy a salir."),
            ex("es-b2-288", "Even if you (tú) asked me, I wouldn't go.", "Aunque {|tú }me lo {pidieras|pidieses}, {|yo }no iría."),
            ex("es-b2-289", "Despite the rain, we went out.", "A pesar de la lluvia, {|nosotros |nosotras }salimos."),
            ex("es-b2-290", "Despite being tired, she kept on working.", "A pesar de estar cansada, {|ella }siguió trabajando."),
            ex("es-b2-291", "Although it's late, there are still people in the street.", "A pesar de que es tarde, todavía hay gente en la calle.", "Aunque es tarde, todavía hay gente en la calle."),
            ex("es-b2-292", "However much I study, I don't pass.", "Por mucho que {|yo }estudio, {|yo }no apruebo.", "Por más que {|yo }estudio, {|yo }no apruebo."),
            ex("es-b2-293", "However expensive it is, I'll buy it.", "Por muy caro que sea, {|yo }lo compraré.", "Por muy caro que sea, {|yo }lo voy a comprar."),
            ex("es-b2-294", "He's rich; even so, he isn't happy.", "{Es|Él es} rico; aun así, no es feliz.", "{Es|Él es} rico, pero aun así no es feliz."),
            ex("es-b2-295", "Despite what people say, I like him.", "A pesar de lo que dice la gente, me cae bien.", "Pese a lo que dice la gente, me cae bien.", "A pesar de lo que dice la gente, me gusta."),
            ex("es-b2-296", "However hard you (tú) try, you won't convince me.", "Por mucho que {|tú }lo intentes, no me {convencerás|vas a convencer}.", "Por más que {|tú }lo intentes, no me {convencerás|vas a convencer}."),
            ex("es-b2-297", "Although I don't like it, I'll do it.", "Aunque no me gusta, {|yo }lo {haré|voy a hacer}.", "Aunque no me guste, {|yo }lo {haré|voy a hacer}."),
            ex("es-b2-298", "Although the results are good, we must keep improving.", "Si bien los resultados son buenos, {|nosotros |nosotras }debemos seguir mejorando.", "Aunque los resultados son buenos, {|nosotros |nosotras }debemos seguir mejorando."),
            ex("es-b2-299", "However tired you (tú) are, you must finish it.", "Por muy {cansado|cansada} que {|tú }estés, {|tú }{tienes que|debes} terminarlo."),
            ex("es-b2-300", "I'll go even if nobody comes with me.", "{|yo }{iré|voy} aunque nadie venga conmigo."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-21",
        title = "Connectors: cause and effect",
        level = "B2",
        category = "Connectors",
        explanation = """
            These words link a cause to its effect. Their position and register differ.

            ## Cause
            - **porque**: because (never first in the sentence)
            - **como**: as, since (always first)
            - **ya que / puesto que / dado que**: since, given that
            - **debido a / a causa de** + noun: due to, because of
            - **gracias a** + noun: thanks to (positive)
            - **por culpa de** + noun: because of (negative)

            ## Examples
            - **Como** llovía, no salimos.
            - No salimos **porque** llovía.
            - **Debido a** la huelga, no hay trenes.
            - **Gracias a** ti, aprobé.
            - **Por culpa de**l tráfico, llegué tarde.

            ## Effect
            - **por eso / por lo tanto**: therefore
            - **así que**: so
            - **en consecuencia / por consiguiente**: as a result (formal)

            ## Don't confuse
            - **No es que** + subjunctive denies a cause: **No es que** no quiera, **es que** no puedo.
            - **Porque** + subjunctive after no: No lo hago **porque quiera**, sino porque debo.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-301", "As it was raining, we didn't go out.", "Como llovía, {|nosotros |nosotras }no salimos.", "Como estaba lloviendo, {|nosotros |nosotras }no salimos."),
            ex("es-b2-302", "We didn't go out because I was ill.", "{|nosotros |nosotras }no salimos porque {|yo }{estaba enfermo|estaba enferma|estaba malo|estaba mala}."),
            ex("es-b2-303", "Since you're (tú) here, help me.", "{Ya que|Puesto que|Dado que} {|tú }estás aquí, ayúdame."),
            ex("es-b2-304", "Due to the strike, there are no trains.", "Debido a la huelga, no hay trenes.", "A causa de la huelga, no hay trenes."),
            ex("es-b2-305", "Thanks to you (tú), I passed.", "Gracias a ti, {|yo }aprobé.", "Gracias a ti, {|yo }he aprobado."),
            ex("es-b2-306", "Because of the traffic, I arrived late.", "Por culpa del tráfico, {|yo }llegué tarde.", "A causa del tráfico, {|yo }llegué tarde.", "Debido al tráfico, {|yo }llegué tarde."),
            ex("es-b2-307", "I have no money, so I'm staying at home.", "{|yo }no tengo dinero, así que {|yo }me quedo en casa.", "{|yo }no tengo dinero, por eso {|yo }me quedo en casa."),
            ex("es-b2-308", "Prices have gone up; as a result, sales have fallen.", "Los precios han subido; en consecuencia, las ventas han bajado.", "Los precios han subido; por consiguiente, las ventas han bajado.", "Los precios han subido; por lo tanto, las ventas han bajado."),
            ex("es-b2-309", "Given that nobody came, we cancelled the meeting.", "{Dado que|Puesto que|Ya que|Como} no vino nadie, {|nosotros |nosotras }cancelamos la reunión."),
            ex("es-b2-310", "I do it not because I want to, but because I have to.", "No lo hago porque {|yo }quiera, sino porque {|yo }debo.", "No lo hago porque {|yo }quiera, sino porque {|yo }tengo que hacerlo."),
            ex("es-b2-311", "As I didn't know the way, I asked a policeman.", "Como no {sabía|conocía} el camino, {|yo }le pregunté a un policía.", "Como no {sabía|conocía} el camino, {|yo }pregunté a un policía."),
            ex("es-b2-312", "The flight was cancelled because of the fog.", "El vuelo se canceló {a causa de|debido a|por} la niebla.", "El vuelo fue cancelado {a causa de|debido a|por} la niebla."),
            ex("es-b2-313", "Thanks to the doctors, he's well now.", "Gracias a los médicos, ahora está bien.", "Gracias a los médicos, ya está bien."),
            ex("es-b2-314", "She's upset because of what you (tú) said.", "{|ella }está {molesta|disgustada} por lo que {|tú }dijiste.", "{|ella }está {molesta|disgustada} por lo que {|tú }has dicho."),
            ex("es-b2-315", "He has studied a lot; therefore he'll pass.", "Ha estudiado mucho; por lo tanto, {aprobará|va a aprobar}.", "Ha estudiado mucho; por tanto, {aprobará|va a aprobar}.", "Ha estudiado mucho, así que {aprobará|va a aprobar}."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-22",
        title = "Forming hypotheses and assumptions",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            Spanish can use tenses alone to show that you're guessing.

            ## Tense shifts
            - About the present, use the **future**: **Estará** en casa. He's probably at home.
            - About the recent past, use the **future perfect**: **Habrá salido.** He's probably gone out.
            - About the past, use the **conditional**: **Serían** las diez. It must have been about ten.

            ## With expressions
            - **Deber de** + infinitive: **Debe de** tener 40 años. He must be about 40.
            - **A lo mejor / igual** + indicative: **A lo mejor** está enfermo.
            - **Quizás / tal vez** + indicative or subjunctive: **Quizás** esté enfermo.
            - **Es probable que / puede que** + subjunctive: **Puede que** esté enfermo.
            - **Seguramente / probablemente** usually take the indicative.
            - **Supongo que** + indicative: **Supongo que** vendrá.
            - **Me imagino que** + indicative.

            ## Asking
            - **¿Dónde estará?** Where can it be?
            - **¿Quién será?** Who can it be?
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-316", "He's probably at home.", "Estará en casa.", "Él estará en casa.", "Probablemente está en casa.", "Seguramente está en casa."),
            ex("es-b2-317", "They've probably gone out.", "Habrán salido.", "Ellos habrán salido.", "Ellas habrán salido.", "Probablemente han salido.", "Seguramente han salido."),
            ex("es-b2-318", "It must have been about ten o'clock.", "Serían las diez.", "Debían de ser las diez."),
            ex("es-b2-319", "She must be about forty.", "{Debe de tener|Tendrá|Ella debe de tener|Ella tendrá} unos cuarenta años.", "{Debe de tener|Tendrá|Ella debe de tener|Ella tendrá} cuarenta años."),
            ex("es-b2-320", "Maybe he's ill.", "A lo mejor {|él }está {enfermo|malo}.", "{Quizás|Quizá|Tal vez} {|él }esté {enfermo|malo}.", "Igual {|él }está {enfermo|malo}."),
            ex("es-b2-321", "She might be at the office.", "Puede que {|ella }esté en la oficina.", "Es posible que {|ella }esté en la oficina."),
            ex("es-b2-322", "I suppose he'll come.", "{|yo }supongo que {|él }vendrá."),
            ex("es-b2-323", "I imagine you're (tú) tired.", "{|yo }me imagino que {|tú }estás {cansado|cansada}.", "{|yo }imagino que {|tú }estás {cansado|cansada}."),
            ex("es-b2-324", "Where can my keys be?", "¿Dónde estarán mis llaves?"),
            ex("es-b2-325", "Who can it be at this hour?", "¿Quién será a estas horas?", "¿Quién será a esta hora?"),
            ex("es-b2-326", "They were probably about twenty years old then.", "Entonces {|ellos |ellas }tendrían unos veinte años.", "{|ellos |ellas }tendrían unos veinte años entonces."),
            ex("es-b2-327", "It's likely that the shop is closed.", "Es probable que la tienda esté cerrada."),
            ex("es-b2-328", "He must have missed the bus.", "Habrá perdido el autobús.", "Él habrá perdido el autobús.", "Debe de haber perdido el autobús."),
            ex("es-b2-329", "There were probably fifty people at the party.", "Habría unas cincuenta personas en la fiesta.", "En la fiesta habría unas cincuenta personas."),
            ex("es-b2-330", "She was probably tired, because she didn't say anything.", "Estaría cansada, porque no dijo nada.", "Ella estaría cansada, porque no dijo nada."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-23",
        title = "The passive voice",
        level = "B2",
        category = "Verbs: Passive",
        explanation = """
            Spanish has several ways to focus on the action rather than on who did it.

            ## Ser + participle
            Common in writing and news. The participle agrees with the subject:
            - El puente **fue construido** en 1920.
            - Las cartas **fueron enviadas** ayer.
            - **Por** introduces who did it: El libro **fue escrito por** Cervantes.

            ## Passive se
            The most usual form in speech. The verb agrees with the thing:
            - **Se vende** piso. Flat for sale.
            - **Se venden** pisos. Flats for sale.
            - Aquí **se habla** español. Spanish is spoken here.

            ## Impersonal se
            No specific person, with people as object or no object at all:
            - **Se vive** bien aquí. People live well here.
            - **Se busca** a los ladrones. They are looking for the thieves.

            ## Estar + participle
            This describes the result, not the action:
            - La puerta **fue cerrada** a las ocho. (the action)
            - La puerta **está cerrada**. (the resulting state)

            ## Third person plural
            - **Me han robado** la cartera. My wallet has been stolen.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-331", "The bridge was built in 1920.", "El puente fue construido en 1920.", "El puente se construyó en 1920."),
            ex("es-b2-332", "The letters were sent yesterday.", "Las cartas fueron enviadas ayer.", "Las cartas se enviaron ayer.", "Ayer se enviaron las cartas."),
            ex("es-b2-333", "The book was written by Cervantes.", "El libro fue escrito por Cervantes."),
            ex("es-b2-334", "Flat for sale.", "Se vende piso.", "Se vende apartamento."),
            ex("es-b2-335", "Flats for sale.", "Se venden pisos.", "Se venden apartamentos."),
            ex("es-b2-336", "Spanish is spoken here.", "Aquí se habla español.", "Se habla español aquí."),
            ex("es-b2-337", "People live well here.", "Aquí se vive bien.", "Se vive bien aquí."),
            ex("es-b2-338", "They are looking for the thieves.", "Se busca a los ladrones.", "Están buscando a los ladrones.", "Buscan a los ladrones."),
            ex("es-b2-339", "The door is closed.", "La puerta está cerrada."),
            ex("es-b2-340", "The door was closed at eight.", "La puerta fue cerrada a las ocho.", "La puerta se cerró a las ocho."),
            ex("es-b2-341", "My wallet has been stolen.", "Me han robado la cartera."),
            ex("es-b2-342", "The results will be published tomorrow.", "Los resultados serán publicados mañana.", "Los resultados se publicarán mañana.", "Mañana se publicarán los resultados."),
            ex("es-b2-343", "The thief was arrested by the police.", "El ladrón fue detenido por la policía.", "El ladrón fue arrestado por la policía."),
            ex("es-b2-344", "How do you say this in Spanish?", "¿Cómo se dice esto en español?"),
            ex("es-b2-345", "Smoking is not allowed here.", "Aquí no se puede fumar.", "No se puede fumar aquí.", "Aquí no se permite fumar.", "No se permite fumar aquí."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-24",
        title = "Regretting decisions from the past",
        level = "B2",
        category = FUNCTIONAL,
        explanation = """
            Spanish has several ways to look back on a decision you wish had gone differently.

            ## Should have
            **Debería/tendría que** + **haber** + participle:
            - **Debería haber estudiado** más. I should have studied more.
            - **Tendría que haberte llamado.** I should have called you.
            - **No debería haber** dicho eso. I shouldn't have said that.

            ## If only
            - **Ojalá hubiera** estudiado. If only I'd studied.
            - **Si hubiera** sabido... If I'd known...

            ## Arrepentirse de
            - **Me arrepiento de** + infinitive: **Me arrepiento de** haberlo vendido.
            - **Me arrepiento de que** + subjunctive.

            ## Other useful phrases
            - **Lamento** + infinitive or **que** + subjunctive: **Lamento** no haber ido.
            - **Siento** no haber llamado. I'm sorry I didn't call.
            - **Fue un error** + infinitive: It was a mistake to...
            - **Más me habría valido** + infinitive: I'd have been better off...
            - **¿Por qué no** + past tense? **¿Por qué no** te hice caso?
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-346", "I should have studied more.", "{Debería|Tendría que|Yo debería|Yo tendría que} haber estudiado más."),
            ex("es-b2-347", "I should have called you (tú).", "{Debería|Tendría que|Yo debería|Yo tendría que} haberte llamado.", "Te {debería|tendría que} haber llamado."),
            ex("es-b2-348", "I shouldn't have said that.", "No {debería|tendría que} haber dicho eso.", "Yo no {debería|tendría que} haber dicho eso."),
            ex("es-b2-349", "If only I'd studied medicine!", "¡Ojalá {|yo }{hubiera|hubiese} estudiado medicina!"),
            ex("es-b2-350", "If I'd known, I would have helped you (tú).", "Si {|yo }lo {hubiera|hubiese} sabido, {|yo }te {habría|hubiera|hubiese} ayudado."),
            ex("es-b2-351", "I regret having sold the house.", "{|yo }me arrepiento de haber vendido la casa."),
            ex("es-b2-352", "I regret that I didn't go to the wedding.", "Lamento no haber ido a la boda.", "Siento no haber ido a la boda.", "{|yo }me arrepiento de no haber ido a la boda."),
            ex("es-b2-353", "I'm sorry I didn't call.", "Siento no haber llamado.", "Lamento no haber llamado."),
            ex("es-b2-354", "It was a mistake to accept that job.", "Fue un error aceptar ese trabajo.", "Fue un error aceptar aquel trabajo."),
            ex("es-b2-355", "Why didn't I listen to you (tú)?", "¿Por qué no te hice caso?", "¿Por qué no te escuché?"),
            ex("es-b2-356", "You (tú) should have told me the truth.", "{|tú }deberías haberme dicho la verdad.", "{|tú }me deberías haber dicho la verdad.", "{|tú }tendrías que haberme dicho la verdad.", "{|tú }me tendrías que haber dicho la verdad."),
            ex("es-b2-357", "We shouldn't have left so late.", "{|nosotros |nosotras }no deberíamos haber salido tan tarde.", "{|nosotros |nosotras }no tendríamos que haber salido tan tarde."),
            ex("es-b2-358", "I regret not having learned to play an instrument.", "{|yo }me arrepiento de no haber aprendido a tocar un instrumento."),
            ex("es-b2-359", "If only I hadn't bought that car!", "¡Ojalá no {hubiera|hubiese} comprado ese coche!", "¡Ojalá no me {hubiera|hubiese} comprado ese coche!"),
            ex("es-b2-360", "I'd have been better off staying at home.", "Más me habría valido quedarme en casa.", "Más me hubiera valido quedarme en casa."),
        ),
    ),
    Lesson(
        id = "es-b2-lesson-25",
        title = "Hypothetical situations in the past",
        level = "B2",
        category = DISCOURSE,
        explanation = """
            These sentences imagine a past that never happened.

            ## The structure
            **Si** + pluscuamperfecto de subjuntivo + condicional compuesto:
            - **Si hubiera estudiado, habría aprobado.** If I had studied, I would have passed.

            The second part can also use **hubiera**:
            - Si hubiera estudiado, **hubiera aprobado**.

            ## How to form it
            - **hubiera** (or **hubiese**) + participle
            - **habría** + participle

            ## Mixed conditions
            A past condition with a present result:
            - Si **hubiera estudiado** medicina, ahora **sería** médico. If I'd studied medicine, I'd be a doctor now.

            A present condition with a past result:
            - Si **fuera** más valiente, se lo **habría dicho**. If I were braver, I'd have told him.

            ## Other forms
            - **De** + **haber** + participle: **De haberlo sabido**, habría venido.
            - **Como si** + **hubiera**: Habla **como si hubiera estado** allí. He talks as if he'd been there.
        """.trimIndent(),
        exercises = listOf(
            ex("es-b2-361", "If I had studied, I would have passed.", "Si {|yo }{hubiera|hubiese} estudiado, {|yo }{habría|hubiera|hubiese} aprobado."),
            ex("es-b2-362", "If we had left earlier, we would have arrived on time.", "Si {|nosotros |nosotras }{hubiéramos|hubiésemos} salido antes, {|nosotros |nosotras }{habríamos|hubiéramos|hubiésemos} llegado a tiempo."),
            ex("es-b2-363", "If you (tú) had told me, I would have gone with you.", "Si {|tú }me lo {hubieras|hubieses} dicho, {|yo }{habría|hubiera|hubiese} ido contigo."),
            ex("es-b2-364", "If I had studied medicine, I'd be a doctor now.", "Si {|yo }{hubiera|hubiese} estudiado medicina, ahora {|yo }sería médico.", "Si {|yo }{hubiera|hubiese} estudiado medicina, ahora {|yo }sería médica."),
            ex("es-b2-365", "If I were braver, I'd have told him.", "Si {|yo }{fuera|fuese} más {valiente|valeroso}, se lo {habría|hubiera|hubiese} dicho."),
            ex("es-b2-366", "Had I known it, I would have come.", "De haberlo sabido, {|yo }{habría|hubiera|hubiese} venido."),
            ex("es-b2-367", "He talks as if he had been there.", "{|él }habla como si {hubiera|hubiese} estado allí."),
            ex("es-b2-368", "What would you (tú) have done in my place?", "¿Qué {habrías|hubieras|hubieses} hecho {|tú }en mi lugar?"),
            ex("es-b2-369", "If it hadn't snowed, we would have gone to the mountains.", "Si no {hubiera|hubiese} nevado, {|nosotros |nosotras }{habríamos|hubiéramos|hubiésemos} ido a la montaña."),
            ex("es-b2-370", "I would have bought it if I'd had money.", "{|yo }lo {habría|hubiera|hubiese} comprado si {|yo }{hubiera|hubiese} tenido dinero."),
            ex("es-b2-371", "If she hadn't missed the flight, she'd be here now.", "Si {|ella }no {hubiera|hubiese} perdido el vuelo, ahora estaría aquí."),
            ex("es-b2-372", "If they had told me, I wouldn't have gone.", "Si me lo {hubieran|hubiesen} dicho, {|yo }no {habría|hubiera|hubiese} ido."),
            ex("es-b2-373", "If you (vosotros) had come, you would have had a great time.", "Si {|vosotros |vosotras }{hubierais|hubieseis} venido, {|vosotros |vosotras }lo {habríais|hubierais|hubieseis} pasado {muy bien|genial}."),
            ex("es-b2-374", "If I hadn't met her, I wouldn't live here.", "Si no la {hubiera|hubiese} conocido, {|yo }no viviría aquí."),
            ex("es-b2-375", "He acted as if nothing had happened.", "{|él }actuó como si no {hubiera|hubiese} pasado nada.", "{|él }se comportó como si no {hubiera|hubiese} pasado nada."),
        ),
    ),
    fromSubjunctiveCourse("Pluscuamperfecto de subjuntivo"),
    fromSubjunctiveCourse("Indicativo y subjuntivo"),
)
