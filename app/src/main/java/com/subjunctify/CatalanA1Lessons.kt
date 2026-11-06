package com.subjunctify

// The content of the Catalan A1 course, in teaching order, using central Catalan forms. See Exercise
// for the `{a|b}` answer syntax, and Explanation in MainActivity for the markup used in the notes.

private const val COMMUNICATION = "Communication"
private const val BASIC_VERBS = "Verbs: Essentials"
private const val PRESENT = "Verbs: Present"
private const val NOUNS = "Nouns and adjectives"
private const val NUMBERS = "Numbers and time"

private fun ex(english: String, pattern: String, vararg alsoAccepted: String) =
    Exercise(english, pattern, *alsoAccepted)

val catalanA1Lessons: List<Lesson> = listOf(
    Lesson(
        title = "Greetings and introductions",
        level = "A1",
        category = COMMUNICATION,
        explanation = """
            Start with the phrases you'll use every day.

            ## Greetings
            - **Hola!** Hello!
            - **Bon dia!** Good morning!
            - **Bona tarda!** Good afternoon!
            - **Bona nit!** Good night!
            - **Adéu!** Goodbye!
            - **Fins demà!** See you tomorrow!

            ## Introducing yourself
            - **Com et dius?** What's your name?
            - **Em dic** Anna. My name is Anna.
            - **D'on ets?** Where are you from?
            - **Sóc de** Londres. I'm from London.
            - **Molt de gust!** Pleased to meet you!

            ## Being polite
            - **Si us plau.** Please.
            - **Gràcies** or **Moltes gràcies.** Thank you.
            - **De res.** You're welcome.
            - **Perdona.** Excuse me. (informal)
            - **Perdoni.** Excuse me. (formal)

            ## Formal or informal
            Use **tu** with friends. Use **vostè** with strangers and in formal settings.
        """.trimIndent(),
        exercises = listOf(
            ex("Good morning!", "Bon dia!"),
            ex("Good afternoon!", "Bona tarda!"),
            ex("Good night and see you tomorrow!", "Bona nit i fins demà!"),
            ex("What's your (tu) name?", "Com et dius?"),
            ex("My name is Anna.", "Em dic Anna."),
            ex("Where are you (tu) from?", "D'on ets?"),
            ex("I'm from London.", "{|Jo }sóc de Londres."),
            ex("Pleased to meet you!", "Molt de gust!"),
            ex("Thank you very much.", "Moltes gràcies."),
            ex("You're welcome.", "De res."),
            ex("A coffee, please.", "Un cafè, si us plau."),
            ex("Excuse me (vostè), where is the station?", "Perdoni, on és l'estació?"),
            ex("How are you (tu)?", "Com estàs?", "Com va?"),
            ex("I'm very well, thank you.", "{|Jo }estic molt bé, gràcies."),
            ex("Goodbye, Marta!", "Adéu, Marta!"),
        ),
    ),
    Lesson(
        title = "Ser and estar",
        level = "A1",
        category = BASIC_VERBS,
        explanation = """
            Catalan has two verbs for "to be".

            ## Forms
            |             ser    estar
            | jo          sóc    estic
            | tu          ets    estàs
            | ell/ella    és     està
            | nosaltres   som    estem
            | vosaltres   sou    esteu
            | ells/elles  són    estan

            ## Ser: who or what something is
            - **Sóc** professor. I'm a teacher.
            - **És** de Girona. She's from Girona.
            - La casa **és** gran. The house is big.

            ## Estar: how someone is now
            - **Estic** cansat. I'm tired.
            - **Està** content. He's happy.

            ## Where something is
            Catalan normally uses **ser** for location:
            - Barcelona **és** a Catalunya.
            - La Marta **és** a casa. (**està** is also common for people)

            ## Names
            In speech, names usually take an article: **la** Marta, **en** Pau.
        """.trimIndent(),
        exercises = listOf(
            ex("I'm a teacher.", "{|Jo }sóc {professor|professora|mestre|mestra}."),
            ex("She's from Girona.", "{|Ella }és de Girona."),
            ex("We're students.", "{|Nosaltres }som {estudiants|alumnes}."),
            ex("Are you (tu) English?", "Ets anglès?", "Ets anglesa?", "Tu ets anglès?", "Tu ets anglesa?"),
            ex("The house is big.", "La casa és gran."),
            ex("I'm tired.", "{|Jo }{estic cansat|estic cansada}."),
            ex("He's very happy today.", "Avui {|ell }està molt content.", "{|Ell }està molt content avui."),
            ex("Marta is ill.", "La Marta està malalta."),
            ex("How are you (vosaltres)?", "Com esteu?"),
            ex("Barcelona is in Catalonia.", "Barcelona és a Catalunya."),
            ex("Pau is at home.", "En Pau és a casa.", "En Pau està a casa.", "El Pau és a casa.", "El Pau està a casa."),
            ex("You (vosaltres) are very kind.", "{|Vosaltres }sou molt {amables|simpàtics|simpàtiques}."),
            ex("They're from Valencia.", "{|Ells |Elles }són de València."),
            ex("We're fine.", "{|Nosaltres }estem bé."),
            ex("Marta is intelligent.", "La Marta és intel·ligent."),
        ),
    ),
    Lesson(
        title = "Articles and gender",
        level = "A1",
        category = NOUNS,
        explanation = """
            Every Catalan noun is masculine or feminine.

            ## Definite articles: the
            |             singular   plural
            | masculine   el         els
            | feminine    la         les

            ## Before a vowel or h
            Singular **el** and **la** usually shorten to **l'**:
            - **l'**home, **l'**amic, **l'**hora, **l'**amiga

            ## Indefinite articles: a, some
            - **un** llibre, **una** taula
            - **uns** llibres, **unes** taules

            ## Spotting the gender
            - Words ending in **-a** are usually feminine: la casa, la taula.
            - Many others are masculine: el gos, el cotxe.
            - Some must simply be learned: **la** clau, **el** dia, **la** llet.

            ## Masculine to feminine
            - el noi → la noia
            - el gat → la gata
            - el professor → la professora
        """.trimIndent(),
        exercises = listOf(
            ex("The dog is big.", "El gos és gran."),
            ex("The table is small.", "La taula és petita."),
            ex("The man is here.", "L'home és aquí.", "L'home està aquí."),
            ex("The friend (m) is from Lleida.", "L'amic és de Lleida."),
            ex("I have a book.", "{|Jo }tinc un llibre."),
            ex("She has a house.", "{|Ella }té una casa."),
            ex("The boy and the girl are friends.", "El noi i la noia són amics."),
            ex("The school is nearby.", "L'escola és a prop."),
            ex("The car is new.", "El cotxe és nou."),
            ex("The woman is a doctor.", "La dona és metgessa.", "La dona és doctora."),
            ex("The key is on the table.", "La clau és a la taula.", "La clau és sobre la taula."),
            ex("The cat is black.", "El gat és negre."),
            ex("I want a coffee and a glass of water.", "{|Jo }vull un cafè i un got d'aigua."),
            ex("The hotel is in the centre.", "L'hotel és al centre."),
            ex("It's a good day.", "És un bon dia."),
        ),
    ),
    Lesson(
        title = "Plurals and adjectives",
        level = "A1",
        category = NOUNS,
        explanation = """
            Adjectives agree with the noun and normally follow it.

            ## Forming plurals
            - Most words add **-s**: el gat → els gat**s**
            - Words ending in **-a** change to **-es**: la casa → les cas**es**
            - Some spelling changes: la plaça → les pla**ces**, l'amiga → les ami**gues**
            - Many words ending in **-s** add **-os**: el gos → els gos**sos**

            ## Adjective agreement
            |             sing.     plural
            | masculine   petit     petits
            | feminine    petita    petites

            ## Examples
            - un cotxe **petit**, uns cotxes **petits**
            - una casa **petita**, unes cases **petites**

            ## Same in both genders
            Many adjectives in **-e** or a consonant have one singular form:
            - un home **gran**, una dona **gran**
            - els homes **grans**, les dones **grans**
            - **interessant**, **interessants**

            ## Useful adjectives
            **gran, petit, nou/nova, vell/vella, bonic/bonica, alt, baix, car, barat, simpàtic**
        """.trimIndent(),
        exercises = listOf(
            ex("The cats are small.", "Els gats són petits."),
            ex("The houses are big.", "Les cases són grans."),
            ex("The dogs are very nice.", "Els gossos són molt simpàtics.", "Els gossos són molt bonics."),
            ex("I have two new books.", "{|Jo }tinc dos llibres nous."),
            ex("She has a new car.", "{|Ella }té un cotxe nou."),
            ex("The tables are new.", "Les taules són noves."),
            ex("It's a pretty city.", "És una ciutat bonica."),
            ex("They're very pretty squares.", "Són unes places molt boniques.", "Són places molt boniques."),
            ex("My friends (f) are tall.", "Les meves amigues són altes."),
            ex("The old man is short.", "L'home vell és baix."),
            ex("The apples are expensive.", "Les pomes són cares."),
            ex("These shoes are cheap.", "Aquestes sabates són barates."),
            ex("We have some interesting books.", "{|Nosaltres }tenim uns llibres interessants."),
            ex("The girls are nice.", "Les noies són simpàtiques."),
            ex("The boys are tall.", "Els nois són alts."),
        ),
    ),
    Lesson(
        title = "Tenir and hi ha",
        level = "A1",
        category = BASIC_VERBS,
        explanation = """
            **Tenir** means "to have", and **hi ha** means "there is" or "there are".

            ## Tenir
            | jo          tinc
            | tu          tens
            | ell/ella    té
            | nosaltres   tenim
            | vosaltres   teniu
            | ells/elles  tenen

            ## Age and feelings
            Catalan uses tenir where English uses "to be":
            - **Tinc** vint anys. I'm twenty.
            - **Tinc** gana. I'm hungry.
            - **Tinc** set. I'm thirsty.
            - **Tinc** son. I'm sleepy.
            - **Tinc** fred / calor. I'm cold / hot.

            ## Hi ha
            The same form covers singular and plural:
            - **Hi ha** un banc. There's a bank.
            - **Hi ha** molts turistes. There are many tourists.
            - **No hi ha** pa. There's no bread.
            - **Hi ha** llet? Is there any milk?

            ## Obligation
            **Haver de** + infinitive means "to have to": **He de** treballar.
        """.trimIndent(),
        exercises = listOf(
            ex("I'm twenty years old.", "{|Jo }tinc vint anys."),
            ex("How old are you (tu)?", "Quants anys tens?"),
            ex("I'm hungry.", "{|Jo }tinc gana."),
            ex("Are you (tu) thirsty?", "Tens set?", "Tu tens set?"),
            ex("We're cold.", "{|Nosaltres }tenim fred."),
            ex("She has two brothers.", "{|Ella }té dos germans."),
            ex("Do you (vosaltres) have a car?", "Teniu cotxe?", "Vosaltres teniu cotxe?"),
            ex("They have a big house.", "{|Ells |Elles }tenen una casa gran."),
            ex("There's a bank nearby.", "Hi ha un banc a prop."),
            ex("There are many tourists.", "Hi ha molts turistes."),
            ex("There's no bread.", "No hi ha pa."),
            ex("Is there any milk?", "Hi ha llet?"),
            ex("There's a cat in the garden.", "Hi ha un gat al jardí."),
            ex("I don't have time today.", "Avui {|jo }no tinc temps.", "{|Jo }no tinc temps avui."),
            ex("I have to work.", "{|Jo }he de treballar."),
        ),
    ),
    Lesson(
        title = "Present tense: -ar verbs",
        level = "A1",
        category = PRESENT,
        explanation = """
            Most Catalan verbs end in **-ar**, and they are the most regular.

            ## Forms: parlar (to speak)
            | jo          parlo
            | tu          parles
            | ell/ella    parla
            | nosaltres   parlem
            | vosaltres   parleu
            | ells/elles  parlen

            ## Pronouns are optional
            The ending shows who is acting, so **Parlo català** is enough. Use **jo** or **tu** only for emphasis.

            ## Formal you
            **Vostè** takes the "ell/ella" form: **Vostè parla** anglès?

            ## Useful verbs
            - **parlar** to speak
            - **treballar** to work
            - **estudiar** to study
            - **comprar** to buy
            - **menjar** to eat
            - **escoltar** to listen
            - **mirar** to look at, to watch
            - **arribar** to arrive
            - **agafar** to take
        """.trimIndent(),
        exercises = listOf(
            ex("I speak Catalan.", "{|Jo }parlo català."),
            ex("Do you (tu) speak English?", "Parles anglès?", "Tu parles anglès?"),
            ex("She works in a hospital.", "{|Ella }treballa en un hospital.", "{|Ella }treballa a un hospital."),
            ex("We study at home.", "{|Nosaltres }estudiem a casa."),
            ex("Do you (vosaltres) buy bread here?", "Compreu pa aquí?", "Vosaltres compreu pa aquí?"),
            ex("They eat at two.", "{|Ells |Elles }mengen a les dues."),
            ex("I listen to the radio.", "{|Jo }escolto la ràdio."),
            ex("You (tu) watch a lot of television.", "{|Tu }mires molt la televisió.", "{|Tu }mires molta televisió."),
            ex("The train arrives at eight.", "El tren arriba a les vuit."),
            ex("I take the bus every day.", "{|Jo }agafo l'autobús cada dia.", "{|Jo }agafo l'autobús tots els dies."),
            ex("Do you (vostè) speak French?", "Parla francès?", "Vostè parla francès?"),
            ex("I don't work on Sundays.", "{|Jo }no treballo els diumenges.", "{|Jo }no treballo el diumenge."),
            ex("We buy fruit at the market.", "{|Nosaltres }comprem fruita al mercat."),
            ex("My sister studies medicine.", "La meva germana estudia medicina."),
            ex("They speak very quickly.", "{|Ells |Elles }parlen molt de pressa.", "{|Ells |Elles }parlen molt ràpid."),
        ),
    ),
    Lesson(
        title = "Present tense: other verbs",
        level = "A1",
        category = PRESENT,
        explanation = """
            Verbs in **-re**, **-er** and **-ir** follow different patterns.

            ## Viure (to live) and beure (to drink)
            |             viure   beure
            | jo          visc    bec
            | tu          vius    beus
            | ell/ella    viu     beu
            | nosaltres   vivim   bevem
            | vosaltres   viviu   beveu
            | ells/elles  viuen   beuen

            ## -ir verbs: two groups
            |             dormir   servir
            | jo          dormo    serveixo
            | tu          dorms    serveixes
            | ell/ella    dorm     serveix
            | nosaltres   dormim   servim
            | vosaltres   dormiu   serviu
            | ells/elles  dormen   serveixen

            Most -ir verbs add **-eix-** like servir, for example **llegir** (to read). A few, such as **obrir** (to open), don't: **obro, obres, obre**.

            ## Other common verbs
            - **aprendre** (to learn): aprenc, aprens, aprèn, aprenem, apreneu, aprenen
            - **llegir** (to read): llegeixo, llegeixes, llegeix, llegim, llegiu, llegeixen
            - **escriure** (to write): escric, escrius, escriu, escrivim, escriviu, escriuen
        """.trimIndent(),
        exercises = listOf(
            ex("I live in Barcelona.", "{|Jo }visc a Barcelona."),
            ex("Where do you (tu) live?", "On vius?", "On vius tu?"),
            ex("They live in a small town.", "{|Ells |Elles }viuen en un poble petit.", "{|Ells |Elles }viuen a un poble petit."),
            ex("I drink a lot of water.", "{|Jo }bec molta aigua."),
            ex("Do you (vosaltres) drink wine?", "Beveu vi?", "Vosaltres beveu vi?"),
            ex("She doesn't drink coffee.", "{|Ella }no beu cafè."),
            ex("I sleep eight hours.", "{|Jo }dormo vuit hores."),
            ex("The baby sleeps a lot.", "El nadó dorm molt."),
            ex("I read the newspaper every day.", "{|Jo }llegeixo el diari cada dia.", "{|Jo }llegeixo el diari tots els dies."),
            ex("He reads a book.", "{|Ell }llegeix un llibre."),
            ex("We're learning Catalan.", "{|Nosaltres }aprenem català.", "{|Nosaltres }estem aprenent català."),
            ex("I write a letter.", "{|Jo }escric una carta."),
            ex("We live near the sea.", "{|Nosaltres }vivim a prop del mar."),
            ex("The shop opens at nine.", "La botiga obre a les nou."),
            ex("You (tu) write very well.", "{|Tu }escrius molt bé."),
        ),
    ),
    Lesson(
        title = "Anar, fer, voler and poder",
        level = "A1",
        category = BASIC_VERBS,
        explanation = """
            These four common verbs are irregular and are worth learning early.

            ## Forms
            |             anar   fer    voler  poder
            | jo          vaig   faig   vull   puc
            | tu          vas    fas    vols   pots
            | ell/ella    va     fa     vol    pot
            | nosaltres   anem   fem    volem  podem
            | vosaltres   aneu   feu    voleu  podeu
            | ells/elles  van    fan    volen  poden

            ## Going places
            - **Vaig a** Barcelona. I'm going to Barcelona.
            - **a** + **el** becomes **al**: Vaig **al** mercat.
            - **de** + **el** becomes **del**: Vinc **del** mercat.

            ## Watch out: vaig + infinitive
            This looks like English "I'm going to", but it's a past tense:
            - **Vaig menjar.** I ate.
            - To talk about the future, use **Menjaré** (I'll eat).

            ## Fer
            - **Què fas?** What are you doing?
            - **Fa** sol. It's sunny.
            - **Fa** fred / calor. It's cold / hot.

            ## Voler and poder + infinitive
            - **Vull** menjar. I want to eat.
            - **Puc** entrar? May I come in?
        """.trimIndent(),
        exercises = listOf(
            ex("I'm going to Barcelona.", "{|Jo }vaig a Barcelona."),
            ex("We're going to the market.", "{|Nosaltres }anem al mercat."),
            ex("Where are you (tu) going?", "On vas?", "On vas tu?"),
            ex("They go to school by bus.", "{|Ells |Elles }van a l'escola amb autobús.", "{|Ells |Elles }van a l'escola en autobús."),
            ex("I'm coming from the market.", "{|Jo }vinc del mercat."),
            ex("What are you (tu) doing?", "Què fas?", "Què fas tu?"),
            ex("We're making dinner.", "{|Nosaltres }fem el sopar."),
            ex("It's sunny today.", "Avui fa sol.", "Fa sol avui."),
            ex("It's very cold.", "Fa molt de fred.", "Fa molt fred."),
            ex("I want to eat.", "{|Jo }vull menjar."),
            ex("What do you (tu) want?", "Què vols?", "Què vols tu?"),
            ex("We want two beers, please.", "{|Nosaltres }volem dues cerveses, si us plau."),
            ex("May I come in?", "Puc entrar?"),
            ex("You (tu) can't park here.", "{|Tu }no pots aparcar aquí."),
            ex("Can you (vosaltres) come tomorrow?", "Podeu venir demà?", "Vosaltres podeu venir demà?"),
        ),
    ),
    Lesson(
        title = "Numbers, days and the time",
        level = "A1",
        category = NUMBERS,
        explanation = """
            ## Numbers
            | 1 un/una   2 dos/dues   3 tres     4 quatre
            | 5 cinc     6 sis        7 set      8 vuit
            | 9 nou      10 deu       11 onze    12 dotze
            | 13 tretze  14 catorze   15 quinze  16 setze
            | 17 disset  18 divuit    19 dinou   20 vint

            Then **vint-i-un**, **trenta**, **quaranta**, **cinquanta**, **cent**.

            ## One and two have feminine forms
            - **un** llibre, **una** casa
            - **dos** llibres, **dues** cases

            ## Days
            **dilluns, dimarts, dimecres, dijous, divendres, dissabte, diumenge**

            ## Telling the time
            - **Quina hora és?** What time is it?
            - **És la una.** It's one o'clock.
            - **Són les tres.** It's three o'clock.
            - **A les cinc.** At five o'clock.

            ## Half past
            Catalan counts quarters towards the next hour:
            - **Dos quarts de quatre.** Half past three (literally "two quarters of four").

            ## Parts of the day
            **al matí** (in the morning), **a la tarda** (in the afternoon), **a la nit** (at night)
        """.trimIndent(),
        exercises = listOf(
            ex("What time is it?", "Quina hora és?", "Quina hora és ara?"),
            ex("It's one o'clock.", "És la una."),
            ex("It's three o'clock.", "Són les tres."),
            ex("It's half past three.", "Són dos quarts de quatre."),
            ex("The class starts at five.", "La classe comença a les cinc."),
            ex("I have two houses and two cars.", "{|Jo }tinc dues cases i dos cotxes."),
            ex("Today is Monday.", "Avui és dilluns."),
            ex("Tomorrow is Saturday.", "Demà és dissabte."),
            ex("We work from Monday to Friday.", "{|Nosaltres }treballem de dilluns a divendres."),
            ex("I'm thirty-one.", "{|Jo }tinc trenta-un anys."),
            ex("There are twelve people.", "Hi ha dotze persones."),
            ex("In the morning, I drink a coffee.", "Al matí, {|jo }bec un cafè."),
            ex("At night, we watch television.", "A la nit, {|nosaltres }mirem la televisió."),
            ex("The shop closes on Sundays.", "La botiga tanca els diumenges.", "La botiga tanca el diumenge."),
            ex("It costs fifteen euros.", "Costa quinze euros."),
        ),
    ),
    Lesson(
        title = "Asking questions",
        level = "A1",
        category = COMMUNICATION,
        explanation = """
            Questions are easy to form, because the word order often stays the same.

            ## Yes/no questions
            Just raise your voice at the end. Written Catalan only uses the final question mark:
            - **Parles anglès?** Do you speak English?

            ## Question words
            - **Què?** What?
            - **Qui?** Who?
            - **On?** Where?
            - **Quan?** When?
            - **Com?** How?
            - **Per què?** Why?
            - **Quin/quina?** Which?
            - **Quant/quanta/quants/quantes?** How much / how many?

            ## Answering why
            **Per què** is written as two words in questions, while **perquè** is one word and means "because".
            - **Per què** estudies català? **Perquè** vull viure aquí.

            ## Quant agrees with its noun
            - **Quants** germans tens? (masculine plural)
            - **Quantes** germanes tens? (feminine plural)

            ## Useful phrases
            - **Quant costa?** How much does it cost?
            - **A quina hora?** At what time?
        """.trimIndent(),
        exercises = listOf(
            ex("Who is it?", "Qui és?"),
            ex("What's this?", "Què és això?"),
            ex("Where's the bathroom?", "On és el lavabo?"),
            ex("When does the train arrive?", "Quan arriba el tren?"),
            ex("How do you say this in Catalan?", "Com es diu això en català?"),
            ex("Why are you (tu) studying Catalan?", "Per què estudies català?"),
            ex("Because I want to live here.", "Perquè {|jo }vull viure aquí."),
            ex("How much does it cost?", "Quant costa?"),
            ex("What time does the shop open?", "A quina hora obre la botiga?"),
            ex("How many brothers do you (tu) have?", "Quants germans tens?"),
            ex("How many sisters does she have?", "Quantes germanes té?", "Quantes germanes té ella?"),
            ex("Which book do you (tu) want?", "Quin llibre vols?"),
            ex("Which house is yours (tu)?", "Quina casa és la teva?"),
            ex("Do you (vosaltres) speak Spanish?", "Parleu castellà?", "Parleu espanyol?", "Vosaltres parleu castellà?"),
            ex("Who do you (tu) live with?", "Amb qui vius?"),
        ),
    ),
    Lesson(
        title = "Family and possessives",
        level = "A1",
        category = NOUNS,
        explanation = """
            Catalan possessives usually come with the definite article.

            ## Forms
            |              masc.       fem.
            | my           el meu      la meva
            | your (tu)    el teu      la teva
            | his/her      el seu      la seva
            | our          el nostre   la nostra
            | your (vos.)  el vostre   la vostra
            | their        el seu      la seva

            ## Plurals
            - els meus, les meves
            - els teus, les teves
            - els seus, les seves
            - els nostres, les nostres
            - els vostres, les vostres

            ## Agreement
            The possessive agrees with the thing owned, not the owner:
            - **la meva** mare, **el meu** pare

            ## Family words
            - **la mare, el pare**: mother, father
            - **el germà, la germana**: brother, sister
            - **el fill, la filla**: son, daughter
            - **l'avi, l'àvia**: grandfather, grandmother
            - **l'oncle, la tia**: uncle, aunt
            - **el cosí, la cosina**: cousin
            - **el marit, la dona**: husband, wife
            - **els pares**: parents
        """.trimIndent(),
        exercises = listOf(
            ex("My mother is from Tarragona.", "La meva mare és de Tarragona."),
            ex("My father works in a bank.", "El meu pare treballa en un banc.", "El meu pare treballa a un banc."),
            ex("Your (tu) brother is very tall.", "El teu germà és molt alt."),
            ex("Your (tu) sisters are nice.", "Les teves germanes són simpàtiques."),
            ex("His daughter lives in Paris.", "La seva filla viu a París."),
            ex("Her son is six.", "El seu fill té sis anys."),
            ex("Our house is small.", "La nostra casa és petita."),
            ex("Our grandfather is eighty.", "El nostre avi té vuitanta anys."),
            ex("Your (vosaltres) parents are at home.", "Els vostres pares són a casa.", "Els vostres pares estan a casa."),
            ex("My grandparents live in a village.", "Els meus avis viuen en un poble.", "Els meus avis viuen a un poble."),
            ex("My grandmother cooks very well.", "La meva àvia cuina molt bé."),
            ex("I have an uncle and an aunt in Reus.", "{|Jo }tinc un oncle i una tia a Reus."),
            ex("My cousin (f) has a dog.", "La meva cosina té un gos."),
            ex("Her husband is a doctor.", "El seu marit és metge."),
            ex("These are my children.", "Aquests són els meus fills."),
        ),
    ),
    Lesson(
        title = "Agradar and negatives",
        level = "A1",
        category = COMMUNICATION,
        explanation = """
            Talk about what you like, and learn to say no.

            ## Agradar
            The thing you like is the subject, so the verb agrees with it:
            - **M'agrada** el cafè. I like coffee.
            - **M'agraden** els gossos. I like dogs.
            - **M'agrada** llegir. I like reading.

            ## The pronouns
            | m'agrada   I like
            | t'agrada   you like
            | li agrada  he/she likes
            | ens agrada we like
            | us agrada  you (vosaltres) like
            | els agrada they like

            ## Negatives
            Put **no** before the verb:
            - **No** parlo rus. I don't speak Russian.
            - **No m'agrada** el te. I don't like tea.

            In central Catalan, you may hear **pas** added for emphasis: **No** ho sé **pas**.

            ## Other negative words
            - **res**: nothing: **No** vull **res**.
            - **ningú**: nobody: **No** hi ha **ningú**.
            - **mai**: never: **No** bec **mai** vi.
            - **tampoc**: neither: **Jo tampoc**.
        """.trimIndent(),
        exercises = listOf(
            ex("I like coffee.", "M'agrada el cafè."),
            ex("I like dogs.", "M'agraden els gossos."),
            ex("I like reading.", "M'agrada llegir."),
            ex("Do you (tu) like football?", "T'agrada el futbol?"),
            ex("She likes cats.", "Li agraden els gats.", "A ella li agraden els gats."),
            ex("We like the beach.", "Ens agrada la platja."),
            ex("Do you (vosaltres) like this restaurant?", "Us agrada aquest restaurant?"),
            ex("They like to travel.", "Els agrada viatjar.", "A ells els agrada viatjar.", "A elles els agrada viatjar."),
            ex("I don't speak Russian.", "{|Jo }no parlo rus."),
            ex("I don't like tea.", "No m'agrada el te."),
            ex("I don't want anything.", "{|Jo }no vull res."),
            ex("There's nobody here.", "Aquí no hi ha ningú.", "No hi ha ningú aquí."),
            ex("I never drink wine.", "{|Jo }no bec mai vi.", "{|Jo }mai no bec vi.", "{|Jo }no bec vi mai."),
            ex("I don't know.", "{|Jo }no ho sé."),
            ex("Me neither.", "Jo tampoc."),
        ),
    ),
)
