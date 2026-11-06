package com.subjunctify

// The content of the subjunctive course. See Exercise for the `{a|b}` answer syntax, and Explanation in MainActivity
// for the markup used in the notes.

private fun ex(english: String, pattern: String, vararg alsoAccepted: String) =
    Exercise(english, pattern, *alsoAccepted)

val subjunctiveLessons: List<Lesson> = listOf(
    Lesson(
        title = "Presente de subjuntivo",
        level = "B1",
        explanation = """
            The subjunctive is a mood, not a tense. The indicative states facts; the subjunctive presents something as wished for, uncertain or not yet real. It usually appears after **que**, when the two parts of the sentence have different subjects.

            ## How to form it
            Take the **yo** form of the present, drop the **-o**, and swap the vowel: **-ar** verbs take **e**, while **-er** and **-ir** verbs take **a**.

            |           hablar   comer   vivir
            | yo        hable    coma    viva
            | tú        hables   comas   vivas
            | él/ella   hable    coma    viva
            | nosotros  hablemos comamos vivamos
            | vosotros  habléis  comáis  viváis
            | ellos     hablen   coman   vivan

            ## Verbs with an irregular yo form
            Because you start from the yo form, its irregularity carries through:
            - tengo → **tenga**, hago → **haga**, vengo → **venga**
            - digo → **diga**, salgo → **salga**, conozco → **conozca**

            ## Spelling changes
            The sound stays the same, so the spelling has to adapt:
            - llegar → **llegue**, buscar → **busque**
            - empezar → **empiece**, coger → **coja**

            ## Wishes: querer que, esperar que, ojalá
            Compare one subject with two:
            - **Quiero salir.** I want to go out. (one subject: infinitive)
            - **Quiero que salgas.** I want you to go out. (two subjects: subjunctive)

            **Ojalá** (hopefully, if only) always takes the subjunctive, with or without que: **Ojalá llegue pronto.**
        """.trimIndent(),
        exercises = listOf(
            ex("I want you (tú) to speak more slowly.", "{|yo }quiero que {|tú }hables más {despacio|lentamente|lento}."),
            ex("I hope you (tú) rest well.", "{|yo }espero que {|tú }descanses bien."),
            ex("We want them to live near us.", "{|nosotros |nosotras }queremos que {|ellos |ellas }vivan cerca de {nosotros|nosotras}."),
            ex("My mother wants me to call her every day.", "Mi madre quiere que {|yo }la llame {todos los días|cada día}."),
            ex("I hope it doesn't rain tomorrow.", "{|yo }espero que no llueva mañana."),
            ex("Hopefully he arrives on time.", "Ojalá {|que }{|él }llegue {a tiempo|a la hora|puntual}."),
            ex("The teacher wants us to read this book.", "{El profesor|La profesora} quiere que {|nosotros |nosotras }leamos este libro."),
            ex("Do you (tú) want me to open the window?", "¿{|tú }quieres que {|yo }abra la ventana?"),
            ex("I don't want you (vosotros) to work on Sunday.", "{|yo }no quiero que {|vosotros |vosotras }trabajéis el domingo."),
            ex("We hope you (usted) like the flat.", "{|nosotros |nosotras }esperamos que {|a usted }le guste el {piso|apartamento}."),
            ex("I want Ana to buy the tickets.", "{|yo }quiero que Ana compre {las entradas|los billetes}."),
            ex("Hopefully we'll have time.", "Ojalá {|que }{|nosotros |nosotras }tengamos tiempo."),
            ex("They want me to learn Spanish.", "{|ellos |ellas }quieren que {|yo }aprenda {español|castellano}."),
            ex("I hope you (vosotros) do your homework.", "{|yo }espero que {|vosotros |vosotras }hagáis {los|vuestros} deberes."),
            ex("My brother doesn't want us to sell the car.", "Mi hermano no quiere que {|nosotros |nosotras }vendamos el coche."),
        ),
    ),
    Lesson(
        title = "More on presente de subjuntivo",
        level = "B1",
        explanation = """
            Wishes are only the start. The subjunctive also follows feelings, impersonal expressions and certain time and purpose words.

            ## Feelings
            When you react emotionally to what someone else does, use the subjunctive:
            - **Me alegro de que** estés aquí. I'm glad you're here.
            - **Me molesta que** hablen tan alto. It bothers me that they talk so loudly.
            - **Tengo miedo de que** te pierdas. I'm afraid you'll get lost.
            - **Siento que** tengas que esperar. I'm sorry you have to wait.

            ## Impersonal expressions
            Expressions of need, importance or possibility take the subjunctive when they have a specific subject:
            - **Es importante que** lleguéis pronto.
            - **Es necesario que** hablemos.
            - **Es posible que** esté enferma.

            Without a specific subject, use the infinitive: **Es importante llegar pronto.**

            ## Cuando and the future
            After **cuando**, a future action takes the present subjunctive, never the future tense:
            - **Cuando llegues**, llámame. When you arrive, call me.

            ## Para que and antes de que
            - Te dejo las llaves **para que puedas** entrar. (so that you can)
            - Tenemos que salir **antes de que llueva**. (before it rains)
        """.trimIndent(),
        exercises = listOf(
            ex("I'm glad you (tú) are here.", "{me alegro de|me alegra} que {|tú }estés aquí."),
            ex("It bothers me that they talk so loudly.", "Me molesta que {|ellos |ellas }hablen tan {alto|fuerte}."),
            ex("I'm afraid you (tú) will get lost.", "{tengo miedo de|me da miedo|yo tengo miedo de} que {|tú }te pierdas."),
            ex("It is important that you (vosotros) arrive early.", "Es importante que {|vosotros |vosotras }lleguéis {pronto|temprano}."),
            ex("It's necessary that we talk today.", "{Es necesario|Hace falta} que {|nosotros |nosotras }hablemos hoy."),
            ex("It's possible that she is ill.", "{Es posible|Puede} que {|ella }esté enferma."),
            ex("When you (tú) get home, call me.", "Cuando {|tú }llegues a casa, llámame.", "Llámame cuando {|tú }llegues a casa."),
            ex("I'll call you (tú) when I finish.", "{|yo }te {llamaré|llamo|voy a llamar} cuando {|yo }{termine|acabe}.", "Cuando {|yo }{termine|acabe}, {|yo }te {llamaré|llamo|voy a llamar}."),
            ex("I'm leaving you (tú) the keys so that you can get in.", "{|yo }te dejo las llaves para que {|tú }puedas entrar."),
            ex("We have to leave before it rains.", "{|nosotros |nosotras }tenemos que {salir|irnos|marcharnos} antes de que llueva."),
            ex("I love it that my children read so much.", "Me encanta que mis hijos lean tanto."),
            ex("I'm sorry that you (usted) have to wait.", "{|yo }{siento|lamento} que {|usted }tenga que esperar."),
            ex("It's better that we take the train.", "Es mejor que {|nosotros |nosotras }{cojamos|tomemos} el tren."),
            ex("She is worried that her son doesn't eat.", "{|a ella }le preocupa que su hijo no coma."),
            ex("It's difficult for them to understand it.", "Es difícil que {|ellos |ellas }lo {entiendan|comprendan}."),
        ),
    ),
    Lesson(
        title = "Presente de subjuntivo for advice",
        level = "B1",
        explanation = """
            Giving advice means trying to influence what someone else does, so the subjunctive fits naturally.

            ## The pattern
            Indirect object pronoun + verb of advice + **que** + subjunctive:
            - **Te aconsejo que** descanses. I advise you to rest.
            - **Te recomiendo que** pruebes la paella. I recommend you try the paella.
            - **Os sugiero que** reservéis una mesa. I suggest you all book a table.
            - **Le pido que** me escuche. I'm asking you (usted) to listen to me.

            The pronouns show who receives the advice: **me, te, le, nos, os, les**.

            ## Impersonal advice
            - **Lo mejor es que** hables con él. The best thing is for you to talk to him.
            - **Es recomendable que** lleves un abrigo. It's advisable to bring a coat.

            ## Decir: instruction or information?
            **Decir** changes meaning depending on the mood:
            - Me dice que **viene**. He tells me he's coming. (information: indicative)
            - Me dice que **venga**. He tells me to come. (instruction: subjunctive)

            ## In this lesson
            Spanish sometimes allows an infinitive here, as in **te aconsejo descansar**. It's correct, but the app expects the **que + subjunctive** form you're practising, so answer with that.
        """.trimIndent(),
        exercises = listOf(
            ex("I advise you (tú) to rest more.", "{|yo }te aconsejo que {|tú }descanses más."),
            ex("I recommend that you (tú) try the paella.", "{|yo }te recomiendo que {|tú }pruebes la paella."),
            ex("I suggest that you (vosotros) book a table.", "{|yo }os sugiero que {|vosotros |vosotras }reservéis una mesa."),
            ex("The doctor advises me to drink more water.", "{El médico|La médica|El doctor|La doctora} me aconseja que {beba|tome} más agua."),
            ex("The best thing is for you (tú) to talk to him.", "{Lo mejor es|Es mejor} que {|tú }hables con él."),
            ex("We recommend that you (usted) arrive early.", "{|nosotros |nosotras }le recomendamos que {|usted }llegue {pronto|temprano}."),
            ex("My friends tell me to look for another job.", "Mis amigos me dicen que busque otro trabajo."),
            ex("I'm asking you (tú) to listen to me.", "{|yo }te pido que {|tú }me escuches."),
            ex("I advise you (vosotros) not to drive at night.", "{|yo }os aconsejo que {|vosotros |vosotras }no conduzcáis {de noche|por la noche}."),
            ex("She suggests that we take a taxi.", "{|ella }nos sugiere que {|nosotros |nosotras }{cojamos|tomemos} un taxi."),
            ex("I recommend that you (tú) don't buy that car.", "{|yo }te recomiendo que {|tú }no compres ese coche."),
            ex("It's advisable for you (tú) to bring a coat.", "{Es recomendable|Es aconsejable|Conviene} que {|tú }{lleves|traigas} un abrigo."),
            ex("Tell him to call me.", "Dile que me llame."),
            ex("My father insists that I study medicine.", "Mi padre insiste en que {|yo }estudie medicina."),
            ex("I advise you (ustedes) to save money.", "{|yo }les aconsejo que {|ustedes }ahorren dinero."),
        ),
    ),
    Lesson(
        title = "Subjunctive to express doubt",
        level = "B1",
        explanation = """
            If the speaker treats something as uncertain or untrue, the subjunctive follows.

            ## Doubt and denial
            - **Dudo que** venga. I doubt he'll come.
            - **No creo que** sea verdad. I don't think it's true.
            - **No estoy seguro de que** puedan. I'm not sure they can.
            - **No es verdad que** viva aquí. It's not true that he lives here.
            - **Es poco probable que** ganemos. We're unlikely to win.

            ## Negation flips the mood
            - **Creo que** está en casa. (belief: indicative)
            - **No creo que** esté en casa. (doubt: subjunctive)

            ## Words for "maybe"
            - **Quizás, quizá, tal vez**: usually with the subjunctive: **Quizás llueva.**
            - **Puede que**: always with the subjunctive: **Puede que llueva.**
            - **A lo mejor**: always with the indicative: **A lo mejor llueve.**
        """.trimIndent(),
        exercises = listOf(
            ex("I doubt that he will come.", "{|yo }dudo que {|él }venga."),
            ex("I don't think it's true.", "{|yo }no {creo|pienso} que sea {verdad|cierto}."),
            ex("We don't believe she knows the answer.", "{|nosotros |nosotras }no {creemos|pensamos} que {|ella }sepa la respuesta."),
            ex("I'm not sure that they can come.", "{|yo }no estoy {seguro|segura} de que {|ellos |ellas }puedan venir."),
            ex("Perhaps she's at home.", "{Quizás|Quizá|Tal vez} {|ella }esté en casa."),
            ex("Maybe it will rain tomorrow.", "{Quizás|Quizá|Tal vez} llueva mañana."),
            ex("It's unlikely that we'll win.", "{Es poco probable|Es improbable} que {|nosotros |nosotras }ganemos."),
            ex("I don't think you (tú) are right.", "{|yo }no {creo|pienso} que {|tú }tengas razón."),
            ex("It's not true that Pablo lives here.", "No es {verdad|cierto} que Pablo viva aquí."),
            ex("There may be no tickets left.", "Puede que no queden {entradas|billetes}."),
            ex("I doubt that you (vosotros) know him.", "{|yo }dudo que {|vosotros |vosotras }{lo|le} conozcáis."),
            ex("They don't think the shop is open.", "{|ellos |ellas }no {creen|piensan} que la tienda esté abierta."),
            ex("It's doubtful that the train will arrive on time.", "Es dudoso que el tren llegue {a tiempo|a la hora|puntual}."),
            ex("I can't believe you're (tú) leaving.", "{no puedo creer|no me puedo creer|yo no puedo creer} que {|tú }te {vayas|marches}."),
            ex("Maybe you (tú) are tired.", "{Quizás|Quizá|Tal vez} {|tú }estés {cansado|cansada}."),
        ),
    ),
    Lesson(
        title = "Subjuntivo: irregular",
        level = "B1",
        explanation = """
            Most irregular subjunctives follow patterns. Only six verbs need learning by heart.

            ## The six truly irregular verbs
            |        yo     tú      nosotros  vosotros
            | ser    sea    seas    seamos    seáis
            | estar  esté   estés   estemos   estéis
            | ir     vaya   vayas   vayamos   vayáis
            | haber  haya   hayas   hayamos   hayáis
            | saber  sepa   sepas   sepamos   sepáis
            | dar    dé     des     demos     deis

            **Hay** becomes **haya**: **Ojalá haya comida.**

            ## Stem changes in -ar and -er verbs
            The vowel changes as in the present, but **nosotros** and **vosotros** keep the original vowel:
            - pensar → **piense, pienses, piensen**, but **pensemos, penséis**
            - volver → **vuelva, vuelvas, vuelvan**, but **volvamos, volváis**
            - poder → **pueda, puedas, puedan**, but **podamos, podáis**

            ## Stem changes in -ir verbs
            These also change in **nosotros** and **vosotros**, to a smaller vowel:
            - dormir → **duerma**, but **durmamos, durmáis**
            - sentir → **sienta**, but **sintamos, sintáis**
            - pedir → **pida, pidamos, pidáis**

            ## Reminder: irregular yo forms
            These keep the yo stem throughout: **tenga, venga, haga, diga, salga, ponga, traiga, oiga, conozca**.
        """.trimIndent(),
        exercises = listOf(
            ex("I hope it's a good film.", "{|yo }espero que sea una buena película."),
            ex("I want you (tú) to be happy.", "{|yo }quiero que {|tú }seas feliz."),
            ex("I hope you (vosotros) are well.", "{|yo }espero que {|vosotros |vosotras }estéis bien."),
            ex("We want them to go to the doctor.", "{|nosotros |nosotras }queremos que {|ellos |ellas }vayan al médico."),
            ex("Hopefully there's enough food.", "Ojalá {|que }haya {suficiente comida|bastante comida|comida suficiente}."),
            ex("I doubt he knows where it is.", "{|yo }dudo que {|él }sepa dónde está."),
            ex("I want you (tú) to give me the keys.", "{|yo }quiero que {|tú }me des las llaves."),
            ex("Hopefully you (usted) will come back soon.", "Ojalá {|que }{|usted }vuelva pronto."),
            ex("I don't want you (tú) to think that I'm angry.", "{|yo }no quiero que {|tú }pienses que estoy {enfadado|enfadada}."),
            ex("I hope the children sleep well.", "{|yo }espero que los niños duerman bien."),
            ex("It's important that we sleep eight hours.", "Es importante que {|nosotros |nosotras }durmamos ocho horas."),
            ex("The waiter wants us to order now.", "{El camarero|La camarera} quiere que {|nosotros |nosotras }pidamos ahora."),
            ex("I want you (tú) to tell me the truth.", "{|yo }quiero que {|tú }me digas la verdad."),
            ex("Hopefully you (vosotros) can come.", "Ojalá {|que }{|vosotros |vosotras }podáis venir."),
            ex("I hope you (tú) feel better.", "{|yo }espero que {|tú }te sientas mejor."),
        ),
    ),
    Lesson(
        title = "Present subjunctive",
        level = "B1",
        explanation = """
            This lesson brings the whole B1 picture together and adds some everyday set phrases.

            ## When to use it
            The subjunctive generally appears when a main clause expresses one of these, and a second subject follows **que**:
            - **Wishes:** Quiero que vengas.
            - **Feelings:** Me alegro de que estés bien.
            - **Advice:** Te aconsejo que descanses.
            - **Doubt:** Dudo que sea verdad.
            - **Future time:** Cuando tengas tiempo, escríbeme.
            - **Purpose:** Te lo digo para que lo sepas.

            ## Check the number of subjects
            - **Quiero irme.** I want to go. (one subject: infinitive)
            - **Quiero que te quedes.** I want you to stay. (two subjects: subjunctive)

            ## Que + subjunctive as a wish
            You'll hear these every day in Spain. The main verb is left unsaid, because it's understood as "I hope":
            - **¡Que tengas un buen día!** Have a good day!
            - **¡Que te mejores!** Get well soon!
            - **¡Que aproveche!** Enjoy your meal!
            - **¡Que descanses!** Rest well!
            - **¡Que te vaya bien!** Hope it goes well for you!

            The same structure can pass on an instruction: **¡Que pase!** Let him come in!
        """.trimIndent(),
        exercises = listOf(
            ex("Have a good day! (tú)", "¡Que {tengas|pases} un buen día!"),
            ex("Get well soon! (tú)", "¡Que te mejores{| pronto}!"),
            ex("Enjoy your meal!", "¡Que aproveche!"),
            ex("Rest well! (tú)", "¡Que descanses{| bien}!"),
            ex("I want to go, but I want you (tú) to stay.", "{|yo }quiero {irme|marcharme}, pero {|yo }quiero que {|tú }te quedes."),
            ex("Hopefully it will be sunny tomorrow.", "Ojalá {|que }haga sol mañana."),
            ex("We need you (tú) to help us.", "{|nosotros |nosotras }necesitamos que {|tú }nos ayudes."),
            ex("I'm glad you (vosotros) like the city.", "{me alegro de|me alegra} que {|a vosotros |a vosotras }os guste la ciudad."),
            ex("It's important that you (tú) know it.", "Es importante que {|tú }lo sepas."),
            ex("I doubt there are any tickets.", "{|yo }dudo que haya {entradas|billetes}."),
            ex("When you (tú) have time, write to me.", "Cuando {|tú }tengas tiempo, escríbeme.", "Escríbeme cuando {|tú }tengas tiempo."),
            ex("I'm telling you (tú) so that you know.", "{|yo }te lo digo para que {|tú }lo sepas."),
            ex("Let him come in!", "¡Que {|él }{pase|entre}!"),
            ex("I don't think they're at home.", "{|yo }no {creo|pienso} que {|ellos |ellas }estén en casa."),
            ex("Hope it goes well for you (tú).", "{Que|Espero que|Yo espero que} te vaya bien."),
        ),
    ),
    Lesson(
        title = "Expanding on the subjunctive",
        level = "B2",
        explanation = """
            At B2, the subjunctive moves beyond verbs of wishing and doubting, into descriptions and linking words.

            ## Describing something unknown or non-existent
            If you know the thing exists, use the indicative. If you're still looking for it, or it doesn't exist, use the subjunctive:
            - Tengo un piso que **tiene** terraza. (a real flat)
            - Busco un piso que **tenga** terraza. (any such flat)
            - No conozco a nadie que **viva** en Bilbao. (nobody)
            - ¿Hay alguien que **hable** japonés? (unknown)

            ## Conjunctions that always take the subjunctive
            - **a menos que / a no ser que**: unless
            - **con tal de que / siempre que**: provided that
            - **sin que**: without
            - **para que**: so that
            - **antes de que**: before

            ## Time words: only for the future
            **En cuanto, tan pronto como, hasta que, mientras** and **cuando** take the subjunctive when the action hasn't happened yet:
            - **En cuanto sepas** algo, dímelo. As soon as you know something, tell me.
            - Esperaré **hasta que lleguéis**. I'll wait until you arrive.

            ## Aunque: even if
            - **Aunque sea** caro, voy a comprarlo. Even if it's expensive, I'm going to buy it.

            ## Open-ended phrases
            When the choice is left open, use the subjunctive:
            - Haz **lo que quieras**. Do whatever you want.
            - Id **adonde queráis**. Go wherever you like.
        """.trimIndent(),
        exercises = listOf(
            ex("I'm looking for a flat that has a terrace.", "{|yo }busco un {piso|apartamento} que tenga {|una }terraza."),
            ex("Is there anyone who speaks Japanese?", "¿Hay alguien que hable japonés?"),
            ex("I don't know anyone who lives in Bilbao.", "{|yo }no conozco a nadie que viva en Bilbao."),
            ex("We need someone who can drive a van.", "{|nosotros |nosotras }necesitamos a alguien que {sepa|pueda} conducir una furgoneta."),
            ex("We'll go out unless it rains.", "{|nosotros |nosotras }{saldremos|salimos|vamos a salir} {a menos que|a no ser que} llueva."),
            ex("She always leaves without anyone seeing her.", "{|ella }siempre {sale|se va|se marcha} sin que nadie la vea."),
            ex("I'll lend you (tú) the car provided that you look after it.", "{|yo }te {dejo|dejaré|presto|prestaré} el coche {con tal de que|siempre que|siempre y cuando} {|tú }lo cuides."),
            ex("As soon as you (tú) know something, tell me.", "{En cuanto|Tan pronto como} {|tú }sepas algo, dímelo."),
            ex("I'll wait until you (vosotros) arrive.", "{|yo }{esperaré|espero|voy a esperar} hasta que {|vosotros |vosotras }lleguéis."),
            ex("Even if it's expensive, I'm going to buy it.", "Aunque sea caro, {|yo }{voy a comprarlo|lo voy a comprar|lo compraré}."),
            ex("Tell me before they leave.", "Dímelo antes de que {|ellos |ellas }se {vayan|marchen}."),
            ex("As long as you (tú) live here, you won't pay anything.", "Mientras {|tú }vivas aquí, {|tú }no {pagarás|vas a pagar|pagas} nada."),
            ex("There's nothing that surprises me.", "No hay nada que me sorprenda."),
            ex("Do whatever you (tú) want.", "Haz lo que {|tú }quieras."),
            ex("Go wherever you (vosotros) like.", "Id {adonde|a donde|donde} {|vosotros |vosotras }queráis."),
        ),
    ),
    Lesson(
        title = "Evaluations with the subjuntivo",
        level = "B2",
        explanation = """
            When you pass judgement on something, the subjunctive follows, even if what you're judging is plainly true. The focus is on your reaction, not on the fact.

            ## Common patterns
            - **Es + adjective + que:** Es normal que estés nervioso.
            - **Es una pena / lástima que:** Es una pena que no puedas venir.
            - **Me parece + adjective/adverb + que:** Me parece bien que viajéis juntos.
            - **Qué + adjective/noun + que:** ¡Qué bien que estés aquí!

            Useful adjectives: **lógico, normal, raro, extraño, increíble, ridículo, justo, sorprendente, genial**.

            ## Evaluation or statement of fact?
            Expressions of truth take the indicative when positive:
            - **Es verdad que** está cansado. (statement: indicative)
            - **Es lógico que** esté cansado. (evaluation: subjunctive)

            ## Evaluating the recent past
            To judge something that has already happened, use the **perfect subjunctive**: **haya, hayas, haya, hayamos, hayáis, hayan** + participle:
            - Es una pena que **os hayáis ido**. It's a shame you've left.
            - Está bien que **te haya dicho** la verdad. It's good she has told you the truth.
        """.trimIndent(),
        exercises = listOf(
            ex("It's a pity you (tú) can't come.", "{Es una pena|Es una lástima|Qué pena|Qué lástima} que {|tú }no puedas venir."),
            ex("It's strange that he doesn't answer the phone.", "{Es raro|Es extraño|Qué raro} que {|él }no {conteste al|responda al|coja el} teléfono."),
            ex("It's logical that they're tired.", "Es lógico que {|ellos |ellas }estén {cansados|cansadas}."),
            ex("It's normal for you (tú) to be nervous.", "Es normal que {|tú }estés {nervioso|nerviosa}."),
            ex("I think it's good that you (vosotros) travel together.", "Me parece bien que {|vosotros |vosotras }viajéis {juntos|juntas}."),
            ex("I think it's wrong that they pay so little.", "Me parece mal que {|ellos |ellas }paguen tan poco."),
            ex("It's incredible that there are so many people.", "{Es increíble|Parece increíble|Qué increíble} que haya tanta gente."),
            ex("How nice that you're (tú) here!", "¡Qué bien que {|tú }estés aquí!"),
            ex("What a shame that it's raining!", "¡Qué {pena|lástima} que {esté lloviendo|llueva}!"),
            ex("It's great that you (tú) have a job.", "{Es genial|Es estupendo|Es fantástico|Qué bien} que {|tú }tengas trabajo."),
            ex("It's ridiculous that we have to wait so long.", "Es ridículo que {|nosotros |nosotras }tengamos que esperar tanto."),
            ex("It's a shame you (vosotros) have left.", "Es una {pena|lástima} que {|vosotros |vosotras }os hayáis {ido|marchado}."),
            ex("It's good that she has told you (tú) the truth.", "{Está bien|Es bueno} que {|ella }te haya dicho la verdad."),
            ex("It's fair that everyone pays the same.", "Es justo que {todos paguen|todo el mundo pague} lo mismo."),
            ex("It's surprising that nobody knows it.", "Es sorprendente que nadie lo sepa."),
        ),
    ),
    Lesson(
        title = "Imperfecto de subjuntivo",
        level = "B2",
        explanation = """
            The imperfect subjunctive carries the same meanings as the present subjunctive, but for the past or for hypothetical situations.

            ## How to form it
            Take the **ellos** form of the preterite, drop **-ron**, and add the endings. There are two sets, which mean the same. **-ra** is more common in speech.

            | hablaron → habla-
            | yo        hablara     hablase
            | tú        hablaras    hablases
            | él/ella   hablara     hablase
            | nosotros  habláramos  hablásemos
            | vosotros  hablarais   hablaseis
            | ellos     hablaran    hablasen

            Any irregularity in the preterite carries through:
            - tuvieron → **tuviera**, hicieron → **hiciera**
            - fueron → **fuera** (both ser and ir), dijeron → **dijera**
            - pudieron → **pudiera**, supieron → **supiera**, vinieron → **viniera**

            ## When to use it
            - **After a past trigger:** Quería que vinieras. Me pidió que esperara.
            - **Unreal conditions:** Si tuviera tiempo, viajaría más. Use the conditional in the other half, never in the si clause.
            - **Como si (as if):** Habla como si lo supiera todo.
            - **Unlikely wishes:** ¡Ojalá pudiera volar!
            - **Me gustaría que:** Me gustaría que os quedarais.
            - **Politeness:** Quisiera un café, por favor.

            The app accepts both the -ra and -se forms when translating. Spoken sentences use -ra.
        """.trimIndent(),
        exercises = listOf(
            ex("I wanted you (tú) to come.", "{|yo }quería que {|tú }{vinieras|vinieses}."),
            ex("If I had more time, I would travel more.", "Si {|yo }{tuviera|tuviese} más tiempo, {|yo }viajaría más.", "{|yo }viajaría más si {|yo }{tuviera|tuviese} más tiempo."),
            ex("My parents wanted me to study law.", "Mis padres querían que {|yo }{estudiara|estudiase} derecho."),
            ex("I would like you (vosotros) to stay.", "Me gustaría que {|vosotros |vosotras }os {quedarais|quedaseis}."),
            ex("If it were cheaper, we would buy it.", "Si {fuera|fuese} más barato, {|nosotros |nosotras }lo compraríamos.", "{|nosotros |nosotras }lo compraríamos si {fuera|fuese} más barato."),
            ex("He talks as if he knew everything.", "{|él }habla como si lo {supiera|supiese} todo.", "{|él }habla como si {supiera|supiese} todo."),
            ex("The teacher asked us to write an essay.", "{El profesor|La profesora} nos pidió que {|nosotros |nosotras }{escribiéramos|escribiésemos} {una redacción|un ensayo}."),
            ex("I doubted that she would say anything.", "{|yo }dudaba que {|ella }{dijera|dijese} {nada|algo}."),
            ex("I wish I could fly!", "¡Ojalá {|que }{|yo }{pudiera|pudiese} volar!"),
            ex("It was important that we arrived early.", "Era importante que {|nosotros |nosotras }{llegáramos|llegásemos} {pronto|temprano}."),
            ex("I was looking for someone who knew French.", "{|yo }buscaba a alguien que {supiera|supiese|hablara|hablase} francés."),
            ex("They told me to wait.", "{|ellos |ellas }me dijeron que {|yo }{esperara|esperase}."),
            ex("If I were you (tú), I would go.", "Si {|yo }{fuera|fuese} tú, {|yo }iría.", "{|yo }iría si {|yo }{fuera|fuese} tú."),
            ex("I left so that they could rest.", "{me fui|me marché|salí|yo me fui|yo me marché|yo salí} para que {|ellos |ellas }{pudieran|pudiesen} descansar."),
            ex("I would like a coffee, please.", "{Quisiera|Me gustaría} un café, por favor."),
        ),
    ),
    Lesson(
        title = "Pluscuamperfecto de subjuntivo",
        level = "B2",
        explanation = """
            The pluperfect subjunctive looks back at something that came before a past moment, or at something that never happened.

            ## How to form it
            The imperfect subjunctive of **haber** + past participle:

            | yo        hubiera     hubiese
            | tú        hubieras    hubieses
            | él/ella   hubiera     hubiese
            | nosotros  hubiéramos  hubiésemos
            | vosotros  hubierais   hubieseis
            | ellos     hubieran    hubiesen

            Irregular participles to watch: **visto, hecho, dicho, escrito, puesto, vuelto, abierto, roto, muerto**.

            ## Unreal conditions in the past
            The main clause takes **habría** + participle. In Spain you'll also hear **hubiera** in both halves:
            - **Si lo hubiera sabido, habría venido.** If I had known, I would have come.
            - **Si lo hubiera sabido, hubiera venido.** (same meaning)

            ## Regrets
            - **¡Ojalá hubiera estudiado más!** I wish I had studied more!

            ## Reacting to an earlier past
            - **Me sorprendió que se hubiera ido.** I was surprised that she had left.
            - **No creía que lo hubieran hecho.** I didn't think they had done it.

            ## Como si
            - **Hablaba como si hubiera visto un fantasma.** He spoke as if he had seen a ghost.
        """.trimIndent(),
        exercises = listOf(
            ex("If I had known, I would have come.", "Si {|yo }lo {hubiera|hubiese} sabido, {|yo }{habría|hubiera|hubiese} venido."),
            ex("I wish I had studied more.", "Ojalá {|que }{|yo }{hubiera|hubiese} estudiado más."),
            ex("If you (tú) had called me, I would have helped you.", "Si {|tú }me {hubieras|hubieses} llamado, {|yo }te {habría|hubiera|hubiese} ayudado."),
            ex("I was surprised that she had left.", "Me sorprendió que {|ella }se {hubiera|hubiese} {ido|marchado}."),
            ex("He spoke as if he had seen a ghost.", "{|él }hablaba como si {hubiera|hubiese} visto un fantasma.", "{|él }habló como si {hubiera|hubiese} visto un fantasma."),
            ex("It was a pity that you (vosotros) hadn't come.", "{Fue|Era} una {pena|lástima} que {|vosotros |vosotras }no {hubierais|hubieseis} venido."),
            ex("I didn't think they had done it.", "{|yo }no {creía|pensaba|creí|pensé} que {|ellos |ellas }lo {hubieran|hubiesen} hecho."),
            ex("If we had left earlier, we wouldn't have missed the train.", "Si {|nosotros |nosotras }{hubiéramos|hubiésemos} salido antes, {|nosotros |nosotras }no {habríamos|hubiéramos|hubiésemos} perdido el tren."),
            ex("I wish you (tú) had told me!", "¡Ojalá {|que }{|tú }me lo {hubieras|hubieses} dicho!"),
            ex("There was nobody who had read the book.", "No había nadie que {hubiera|hubiese} leído el libro."),
            ex("She would have liked you (tú) to have been there.", "{|a ella }le {habría|hubiera|hubiese} gustado que {|tú }{hubieras|hubieses} estado allí."),
            ex("It was strange that nobody had heard anything.", "{Era|Fue} {raro|extraño} que nadie {hubiera|hubiese} oído nada."),
            ex("If it hadn't rained, we would have gone to the beach.", "Si no {hubiera|hubiese} llovido, {|nosotros |nosotras }{habríamos|hubiéramos|hubiésemos} ido a la playa."),
            ex("I was glad that you (usted) had come.", "{me alegró|me alegré de} que {|usted }{hubiera|hubiese} venido."),
            ex("If I had had money, I would have bought the house.", "Si {|yo }{hubiera|hubiese} tenido dinero, {|yo }{habría|hubiera|hubiese} comprado la casa."),
        ),
    ),
    Lesson(
        title = "Indicativo y subjuntivo",
        level = "B2",
        explanation = """
            Many structures can take either mood, and the choice changes the meaning. The indicative asserts; the subjunctive doesn't.

            ## Belief and certainty
            - **Creo que** está en casa. / **No creo que** esté en casa.
            - **Es verdad que** está cansado. / **No es verdad que** esté cansado.
            - **Sé que** tienes razón. (knowledge: always indicative when positive)

            ## Cuando: habit or future?
            - **Cuando llego**, siempre llamo. Whenever I arrive, I always call. (habit)
            - **Cuando llegue**, te llamaré. When I arrive, I'll call you. (future)

            ## Aunque: fact or possibility?
            - **Aunque está lloviendo**, voy a salir. Although it's raining... (known fact)
            - **Aunque llueva**, voy a salir. Even if it rains... (possibility)

            ## Real or unknown?
            - Conozco a una chica que **habla** árabe. (she exists)
            - Busco a alguien que **hable** árabe. (anyone at all)

            ## Decir: information or instruction?
            - Me dice que **viene**. He tells me he's coming.
            - Me dice que **venga**. He tells me to come.

            ## Words for "maybe"
            - **A lo mejor viene.** (indicative only)
            - **Quizás venga.** (subjunctive is the norm, and shows more doubt)
        """.trimIndent(),
        exercises = listOf(
            ex("I think she's at home.", "{|yo }{creo|pienso} que {|ella }está en casa."),
            ex("I don't think she's at home.", "{|yo }no {creo|pienso} que {|ella }esté en casa."),
            ex("Whenever I arrive, I always call.", "Cuando {|yo }llego, {|yo }siempre llamo."),
            ex("When I arrive, I'll call you (tú).", "Cuando {|yo }llegue, {|yo }te {llamaré|llamo|voy a llamar}."),
            ex("Although it's raining right now, I'm going to go out.", "Aunque está lloviendo, {|yo }voy a salir."),
            ex("Even if it rains, I'm going to go out.", "Aunque llueva, {|yo }voy a salir."),
            ex("I know a girl who speaks Arabic.", "{|yo }conozco a una chica que habla árabe."),
            ex("I'm looking for someone who speaks Arabic.", "{|yo }busco a alguien que hable árabe."),
            ex("It's true that he's tired.", "Es {verdad|cierto} que {|él }está cansado."),
            ex("It's not true that he's tired.", "No es {verdad|cierto} que {|él }esté cansado."),
            ex("I know that you (tú) are right.", "{|yo }sé que {|tú }tienes razón."),
            ex("He tells me he's coming.", "{|él }me dice que {|él }viene."),
            ex("He tells me to come.", "{|él }me dice que {|yo }venga."),
            ex("Maybe he's coming. (use \"a lo mejor\")", "A lo mejor {|él }viene."),
            ex("Perhaps he'll come. (use \"quizás\")", "{Quizás|Quizá} {|él }venga."),
        ),
    ),
    Lesson(
        title = "Giving opinions in the “indicativo” and “subjuntivo”",
        level = "C1",
        explanation = """
            At C1, choosing the mood is a way to express how firmly you hold an opinion, and how much you accept someone else's.

            ## Stating an opinion: indicative
            When you assert a view, use the indicative:
            - **Creo que, pienso que, opino que, me parece que, considero que**
            - **Es evidente que, está claro que, es obvio que**
            - **En mi opinión, a mi juicio** (no que needed)

            ## Rejecting an opinion: subjunctive
            Negating these makes the idea something you don't assert:
            - **No creo que** esa sea la solución.
            - **No me parece que** sea justo.
            - **No está claro que** la situación esté mejorando.

            ## Conceding: no niego que
            **No niego que tengas razón** grants a point without fully endorsing it. Using the indicative (**tienes razón**) would acknowledge the point more firmly.

            ## Refining what you mean
            - **No es que no quiera, es que no puedo.** It's not that I don't want to; I just can't.
            - **No digo que estés equivocado.** I'm not saying you're wrong.
            - **El hecho de que sea joven** no significa nada. The fact that he's young means nothing. The fact is taken as given, so the point can move on.

            ## Negative questions: indicative
            A negative question that really pushes a view takes the indicative:
            - **¿No crees que es demasiado caro?** Don't you think it's too expensive?

            ## Evaluating
            - **Estoy de acuerdo en que** es un problema grave. (accepting a fact: indicative)
            - **No estoy de acuerdo en que** suban los impuestos. (rejecting: subjunctive)
            - **Me parece inaceptable que** nadie asuma la responsabilidad.

            ## Limiting your claim
            These fixed phrases hedge what you're saying:
            - **Que yo sepa,** nadie se ha quejado. As far as I know...
            - **Que yo recuerde,** estaba cerrado. As far as I remember...
        """.trimIndent(),
        exercises = listOf(
            ex("I think we should change the law.", "{creo|pienso|opino|yo creo|yo pienso|yo opino|me parece} que {deberíamos|debemos} cambiar la ley."),
            ex("I don't think that's the solution.", "{no creo|no pienso|yo no creo|yo no pienso|no me parece} que {esa|eso} sea la solución."),
            ex("In my opinion, the government is wrong.", "{En mi opinión|A mi juicio|A mi parecer}, el gobierno {se equivoca|está equivocado}."),
            ex("It's obvious that the situation is getting better.", "{Es evidente|Está claro|Es obvio} que la situación {está mejorando|mejora}."),
            ex("It's not obvious that the situation is getting better.", "No {es evidente|está claro|es obvio} que la situación {esté mejorando|mejore}."),
            ex("I don't deny that you (tú) have a point.", "{|yo }no niego que {|tú }{tengas|tienes} razón."),
            ex("It's not that I don't want to; I just can't.", "No es que {|yo }no quiera, {es que|sino que|pero} {|yo }no puedo."),
            ex("The fact that he's young doesn't mean anything.", "El hecho de que {|él }sea joven no {significa|quiere decir} nada."),
            ex("I'm not saying you're (tú) wrong.", "{|yo }no digo que {|tú }{estés equivocado|estés equivocada|te equivoques|no tengas razón}."),
            ex("Don't you (tú) think it's too expensive?", "¿{|tú }no crees que es demasiado caro?", "¿No crees {|tú }que es demasiado caro?"),
            ex("As far as I know, nobody has complained.", "Que yo sepa, nadie se ha quejado."),
            ex("As far as I remember, it was closed.", "Que yo recuerde, estaba cerrado."),
            ex("I agree that it's a serious problem.", "{|yo }estoy de acuerdo en que es un problema {grave|serio}."),
            ex("I don't agree that taxes should go up.", "{|yo }no estoy de acuerdo en que {suban|aumenten} los impuestos."),
            ex("I find it unacceptable that nobody takes responsibility.", "Me parece inaceptable que nadie {asuma|tome|se haga cargo de} la responsabilidad."),
        ),
    ),
)
