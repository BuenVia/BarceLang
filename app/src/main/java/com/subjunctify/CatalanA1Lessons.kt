package com.subjunctify

// The content of the Catalan A1 course, in teaching order, using central Catalan forms. See Exercise
// for the `{a|b}` answer syntax, and Explanation in MainActivity for the markup used in the notes.

private const val COMMUNICATION = "Communication"
private const val BASIC_VERBS = "Verbs: Essentials"
private const val PRESENT = "Verbs: Present"
private const val NOUNS = "Nouns and adjectives"
private const val NUMBERS = "Numbers and time"
private const val POSITION = "Position and order"

private fun ex(id: String, english: String, pattern: String, vararg alsoAccepted: String) =
    Exercise(id, english, pattern, *alsoAccepted)

val catalanA1Lessons: List<Lesson> = listOf(
    Lesson(
        id = "ca-a1-lesson-01",
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
            ex("ca-a1-001", "Good morning!", "Bon dia!"),
            ex("ca-a1-002", "Good afternoon!", "Bona tarda!"),
            ex("ca-a1-003", "Good night and see you tomorrow!", "Bona nit i fins demà!"),
            ex("ca-a1-004", "What's your (tu) name?", "Com et dius?"),
            ex("ca-a1-005", "My name is Anna.", "Em dic Anna."),
            ex("ca-a1-006", "Where are you (tu) from?", "D'on ets?"),
            ex("ca-a1-007", "I'm from London.", "{|Jo }sóc de Londres."),
            ex("ca-a1-008", "Pleased to meet you!", "Molt de gust!"),
            ex("ca-a1-009", "Thank you very much.", "Moltes gràcies."),
            ex("ca-a1-010", "You're welcome.", "De res."),
            ex("ca-a1-011", "A coffee, please.", "Un cafè, si us plau."),
            ex("ca-a1-012", "Excuse me (vostè), where is the station?", "Perdoni, on és l'estació?"),
            ex("ca-a1-013", "How are you (tu)?", "Com estàs?", "Com va?"),
            ex("ca-a1-014", "I'm very well, thank you.", "{|Jo }estic molt bé, gràcies."),
            ex("ca-a1-015", "Goodbye, Marta!", "Adéu, Marta!"),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-02",
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
            ex("ca-a1-016", "I'm a teacher.", "{|Jo }sóc {professor|professora|mestre|mestra}."),
            ex("ca-a1-017", "She's from Girona.", "{|Ella }és de Girona."),
            ex("ca-a1-018", "We're students.", "{|Nosaltres }som {estudiants|alumnes}."),
            ex("ca-a1-019", "Are you (tu) English?", "Ets anglès?", "Ets anglesa?", "Tu ets anglès?", "Tu ets anglesa?"),
            ex("ca-a1-020", "The house is big.", "La casa és gran."),
            ex("ca-a1-021", "I'm tired.", "{|Jo }{estic cansat|estic cansada}."),
            ex("ca-a1-022", "He's very happy today.", "Avui {|ell }està molt content.", "{|Ell }està molt content avui."),
            ex("ca-a1-023", "Marta is ill.", "La Marta està malalta."),
            ex("ca-a1-024", "How are you (vosaltres)?", "Com esteu?"),
            ex("ca-a1-025", "Barcelona is in Catalonia.", "Barcelona és a Catalunya."),
            ex("ca-a1-026", "Pau is at home.", "En Pau és a casa.", "En Pau està a casa.", "El Pau és a casa.", "El Pau està a casa."),
            ex("ca-a1-027", "You (vosaltres) are very kind.", "{|Vosaltres }sou molt {amables|simpàtics|simpàtiques}."),
            ex("ca-a1-028", "They're from Valencia.", "{|Ells |Elles }són de València."),
            ex("ca-a1-029", "We're fine.", "{|Nosaltres }estem bé."),
            ex("ca-a1-030", "Marta is intelligent.", "La Marta és intel·ligent."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-03",
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
            ex("ca-a1-031", "The dog is big.", "El gos és gran."),
            ex("ca-a1-032", "The table is small.", "La taula és petita."),
            ex("ca-a1-033", "The man is here.", "L'home és aquí.", "L'home està aquí."),
            ex("ca-a1-034", "The friend (m) is from Lleida.", "L'amic és de Lleida."),
            ex("ca-a1-035", "I have a book.", "{|Jo }tinc un llibre."),
            ex("ca-a1-036", "She has a house.", "{|Ella }té una casa."),
            ex("ca-a1-037", "The boy and the girl are friends.", "El noi i la noia són amics."),
            ex("ca-a1-038", "The school is nearby.", "L'escola és a prop."),
            ex("ca-a1-039", "The car is new.", "El cotxe és nou."),
            ex("ca-a1-040", "The woman is a doctor.", "La dona és metgessa.", "La dona és doctora."),
            ex("ca-a1-041", "The key is on the table.", "La clau és a la taula.", "La clau és sobre la taula."),
            ex("ca-a1-042", "The cat is black.", "El gat és negre."),
            ex("ca-a1-043", "I want a coffee and a glass of water.", "{|Jo }vull un cafè i un got d'aigua."),
            ex("ca-a1-044", "The hotel is in the centre.", "L'hotel és al centre."),
            ex("ca-a1-045", "It's a good day.", "És un bon dia."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-04",
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
            ex("ca-a1-046", "The cats are small.", "Els gats són petits."),
            ex("ca-a1-047", "The houses are big.", "Les cases són grans."),
            ex("ca-a1-048", "The dogs are very nice.", "Els gossos són molt simpàtics.", "Els gossos són molt bonics."),
            ex("ca-a1-049", "I have two new books.", "{|Jo }tinc dos llibres nous."),
            ex("ca-a1-050", "She has a new car.", "{|Ella }té un cotxe nou."),
            ex("ca-a1-051", "The tables are new.", "Les taules són noves."),
            ex("ca-a1-052", "It's a pretty city.", "És una ciutat bonica."),
            ex("ca-a1-053", "They're very pretty squares.", "Són unes places molt boniques.", "Són places molt boniques."),
            ex("ca-a1-054", "My friends (f) are tall.", "Les meves amigues són altes."),
            ex("ca-a1-055", "The old man is short.", "L'home vell és baix."),
            ex("ca-a1-056", "The apples are expensive.", "Les pomes són cares."),
            ex("ca-a1-057", "These shoes are cheap.", "Aquestes sabates són barates."),
            ex("ca-a1-058", "We have some interesting books.", "{|Nosaltres }tenim uns llibres interessants."),
            ex("ca-a1-059", "The girls are nice.", "Les noies són simpàtiques."),
            ex("ca-a1-060", "The boys are tall.", "Els nois són alts."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-05",
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
            ex("ca-a1-061", "I'm twenty years old.", "{|Jo }tinc vint anys."),
            ex("ca-a1-062", "How old are you (tu)?", "Quants anys tens?"),
            ex("ca-a1-063", "I'm hungry.", "{|Jo }tinc gana."),
            ex("ca-a1-064", "Are you (tu) thirsty?", "Tens set?", "Tu tens set?"),
            ex("ca-a1-065", "We're cold.", "{|Nosaltres }tenim fred."),
            ex("ca-a1-066", "She has two brothers.", "{|Ella }té dos germans."),
            ex("ca-a1-067", "Do you (vosaltres) have a car?", "Teniu cotxe?", "Vosaltres teniu cotxe?"),
            ex("ca-a1-068", "They have a big house.", "{|Ells |Elles }tenen una casa gran."),
            ex("ca-a1-069", "There's a bank nearby.", "Hi ha un banc a prop."),
            ex("ca-a1-070", "There are many tourists.", "Hi ha molts turistes."),
            ex("ca-a1-071", "There's no bread.", "No hi ha pa."),
            ex("ca-a1-072", "Is there any milk?", "Hi ha llet?"),
            ex("ca-a1-073", "There's a cat in the garden.", "Hi ha un gat al jardí."),
            ex("ca-a1-074", "I don't have time today.", "Avui {|jo }no tinc temps.", "{|Jo }no tinc temps avui."),
            ex("ca-a1-075", "I have to work.", "{|Jo }he de treballar."),
        ),
    ),
    // Lessons 13 and 14 were added later but are taught here; IDs only ever count up, whatever the order.
    Lesson(
        id = "ca-a1-lesson-13",
        title = "Rooms and furniture",
        level = "A1",
        category = NOUNS,
        explanation = """
            Learn the rooms of a house and what you find in them.

            ## Rooms
            - **la sala d'estar** or **la sala**: living room
            - **el dormitori** or **l'habitació**: bedroom
            - **el menjador**: dining room
            - **la cuina**: kitchen
            - **el bany**: bathroom

            ## In the living room
            **el sofà** (sofa), **la butaca** (armchair), **la televisió** or **la tele** (TV), **el llum** (lamp)

            ## In the bedroom
            **el llit** (bed), **l'armari** (wardrobe), **la calaixera** (chest of drawers)

            ## In the dining room
            **la taula** (table), **la cadira** (chair)

            ## In the kitchen
            **la nevera** (fridge), **el forn** (oven), **la pica** (sink)

            ## In the bathroom
            **el lavabo** (washbasin), **la dutxa** (shower), **la banyera** (bath), **el vàter** (toilet), **el mirall** (mirror)

            ## Watch out: el lavabo
            **El lavabo** is the washbasin, but people also use it for the toilet, as in **On és el lavabo?**

            ## Around the house
            **el pis** (flat), **la finestra** (window), **la porta** (door), **el jardí** (garden), **la terrassa** (terrace)

            ## In a room
            **a** + **el** becomes **al**:
            - **al** menjador, **al** bany
            - **a la** cuina, **a l'**habitació
        """.trimIndent(),
        exercises = listOf(
            ex("ca-a1-181", "There's a sofa in the living room.", "Hi ha un sofà a la sala{ d'estar|}.", "A la sala{ d'estar|} hi ha un sofà."),
            ex("ca-a1-182", "The television is in the living room.", "La {televisió|tele} és a la sala{ d'estar|}."),
            ex("ca-a1-183", "The bedroom is very big.", "{El dormitori|L'habitació} és molt gran."),
            ex("ca-a1-184", "There's a bed and a wardrobe in the bedroom.", "Hi ha un llit i un armari {al dormitori|a l'habitació}.", "{Al dormitori|A l'habitació} hi ha un llit i un armari."),
            ex("ca-a1-185", "The chest of drawers is new.", "La calaixera és nova."),
            ex("ca-a1-186", "There are six chairs in the dining room.", "Hi ha sis cadires al menjador.", "Al menjador hi ha sis cadires."),
            ex("ca-a1-187", "The table and the chairs are old.", "La taula i les cadires són velles."),
            ex("ca-a1-188", "The bathroom is small.", "El bany és petit."),
            ex("ca-a1-189", "There's a sink and a mirror in the bathroom.", "Hi ha {un lavabo|una pica} i un mirall al bany.", "Al bany hi ha {un lavabo|una pica} i un mirall."),
            ex("ca-a1-190", "The kitchen has a big window.", "La cuina té una finestra gran."),
            ex("ca-a1-191", "There's no fridge in the kitchen.", "No hi ha nevera a la cuina.", "A la cuina no hi ha nevera."),
            ex("ca-a1-192", "Is there a shower?", "Hi ha {|una }dutxa?"),
            ex("ca-a1-193", "The flat has two bedrooms and a bathroom.", "El pis té {dos dormitoris|dues habitacions} i un bany."),
            ex("ca-a1-194", "The sofa is very comfortable.", "El sofà és molt còmode."),
            ex("ca-a1-195", "The house has a garden and a terrace.", "La casa té un jardí i una terrassa."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-14",
        title = "Where things are and ordinal numbers",
        level = "A1",
        category = POSITION,
        explanation = """
            Say where something is, and which one it is in a row.

            ## Where things are
            - **a sobre de** or **damunt de**: on, on top of
            - **per sobre de**: above
            - **sota** or **a sota de**: under, below
            - **davant de**: in front of
            - **darrere de**: behind
            - **al costat de**: next to, beside
            - **a l'esquerra (de)**: on the left (of)
            - **a la dreta (de)**: on the right (of)

            ## Use ser
            Location normally takes **ser**: El gat **és** sota el llit.

            ## de + el becomes del
            - davant **del** sofà, al costat **del** llit
            - davant **de la** taula, al costat **de l'**armari

            ## Ordinal numbers
            |      masc.     fem.
            | 1st  primer    primera
            | 2nd  segon     segona
            | 3rd  tercer    tercera
            | 4th  quart     quarta
            | 5th  cinquè    cinquena
            | 6th  sisè      sisena
            | 7th  setè      setena
            | 8th  vuitè     vuitena
            | 9th  novè      novena
            | 10th desè      desena

            ## Agreement
            Ordinals agree with the noun and come before it:
            - el **primer** pis, la **primera** porta
            - els **primers** dies, les **primeres** cases

            ## Floors and doors
            Catalan addresses give the floor and then the door, so **segon primera** (2n 1a) is the second floor, first door. The ground floor is **la planta baixa**.
        """.trimIndent(),
        exercises = listOf(
            ex("ca-a1-196", "The lamp is on top of the chest of drawers.", "{El llum|La làmpada} és {a sobre de|damunt de|a sobre|sobre} la calaixera."),
            ex("ca-a1-197", "The cat is under the bed.", "El gat {és|està} {sota el|a sota del} llit."),
            ex("ca-a1-198", "The television is in front of the sofa.", "La {televisió|tele} és davant del sofà."),
            ex("ca-a1-199", "The garden is behind the house.", "El jardí és darrere {de |}la casa."),
            ex("ca-a1-200", "The chair is next to the table.", "La cadira és al costat de la taula."),
            ex("ca-a1-201", "The bathroom is on the left.", "El bany és a l'esquerra."),
            ex("ca-a1-202", "The wardrobe is to the right of the bed.", "L'armari és a la dreta del llit."),
            ex("ca-a1-203", "There's a mirror above the sink.", "Hi ha un mirall {a sobre del|per sobre del|damunt del} lavabo."),
            ex("ca-a1-204", "It's the first door on the left.", "És la primera porta a l'esquerra."),
            ex("ca-a1-205", "The bathroom is the second door on the right.", "El bany és la segona porta a la dreta."),
            ex("ca-a1-206", "The office is on the third floor.", "L'oficina és {al tercer pis|a la tercera planta}."),
            ex("ca-a1-207", "We're on the tenth floor.", "{|Nosaltres }{som|estem} al desè pis.", "{|Nosaltres }{som|estem} a la desena planta."),
            ex("ca-a1-208", "It's the fifth street on the right.", "És el cinquè carrer a la dreta."),
            ex("ca-a1-209", "The seventh house is very pretty.", "La setena casa és molt bonica."),
            ex("ca-a1-210", "This is the ninth time.", "És la novena vegada.", "És el novè cop."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-06",
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
            ex("ca-a1-076", "I speak Catalan.", "{|Jo }parlo català."),
            ex("ca-a1-077", "Do you (tu) speak English?", "Parles anglès?", "Tu parles anglès?"),
            ex("ca-a1-078", "She works in a hospital.", "{|Ella }treballa en un hospital.", "{|Ella }treballa a un hospital."),
            ex("ca-a1-079", "We study at home.", "{|Nosaltres }estudiem a casa."),
            ex("ca-a1-080", "Do you (vosaltres) buy bread here?", "Compreu pa aquí?", "Vosaltres compreu pa aquí?"),
            ex("ca-a1-081", "They eat at two.", "{|Ells |Elles }mengen a les dues."),
            ex("ca-a1-082", "I listen to the radio.", "{|Jo }escolto la ràdio."),
            ex("ca-a1-083", "You (tu) watch a lot of television.", "{|Tu }mires molt la televisió.", "{|Tu }mires molta televisió."),
            ex("ca-a1-084", "The train arrives at eight.", "El tren arriba a les vuit."),
            ex("ca-a1-085", "I take the bus every day.", "{|Jo }agafo l'autobús cada dia.", "{|Jo }agafo l'autobús tots els dies."),
            ex("ca-a1-086", "Do you (vostè) speak French?", "Parla francès?", "Vostè parla francès?"),
            ex("ca-a1-087", "I don't work on Sundays.", "{|Jo }no treballo els diumenges.", "{|Jo }no treballo el diumenge."),
            ex("ca-a1-088", "We buy fruit at the market.", "{|Nosaltres }comprem fruita al mercat."),
            ex("ca-a1-089", "My sister studies medicine.", "La meva germana estudia medicina."),
            ex("ca-a1-090", "They speak very quickly.", "{|Ells |Elles }parlen molt de pressa.", "{|Ells |Elles }parlen molt ràpid."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-07",
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
            ex("ca-a1-091", "I live in Barcelona.", "{|Jo }visc a Barcelona."),
            ex("ca-a1-092", "Where do you (tu) live?", "On vius?", "On vius tu?"),
            ex("ca-a1-093", "They live in a small town.", "{|Ells |Elles }viuen en un poble petit.", "{|Ells |Elles }viuen a un poble petit."),
            ex("ca-a1-094", "I drink a lot of water.", "{|Jo }bec molta aigua."),
            ex("ca-a1-095", "Do you (vosaltres) drink wine?", "Beveu vi?", "Vosaltres beveu vi?"),
            ex("ca-a1-096", "She doesn't drink coffee.", "{|Ella }no beu cafè."),
            ex("ca-a1-097", "I sleep eight hours.", "{|Jo }dormo vuit hores."),
            ex("ca-a1-098", "The baby sleeps a lot.", "El nadó dorm molt."),
            ex("ca-a1-099", "I read the newspaper every day.", "{|Jo }llegeixo el diari cada dia.", "{|Jo }llegeixo el diari tots els dies."),
            ex("ca-a1-100", "He reads a book.", "{|Ell }llegeix un llibre."),
            ex("ca-a1-101", "We're learning Catalan.", "{|Nosaltres }aprenem català.", "{|Nosaltres }estem aprenent català."),
            ex("ca-a1-102", "I write a letter.", "{|Jo }escric una carta."),
            ex("ca-a1-103", "We live near the sea.", "{|Nosaltres }vivim a prop del mar."),
            ex("ca-a1-104", "The shop opens at nine.", "La botiga obre a les nou."),
            ex("ca-a1-105", "You (tu) write very well.", "{|Tu }escrius molt bé."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-08",
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
            ex("ca-a1-106", "I'm going to Barcelona.", "{|Jo }vaig a Barcelona."),
            ex("ca-a1-107", "We're going to the market.", "{|Nosaltres }anem al mercat."),
            ex("ca-a1-108", "Where are you (tu) going?", "On vas?", "On vas tu?"),
            ex("ca-a1-109", "They go to school by bus.", "{|Ells |Elles }van a l'escola amb autobús.", "{|Ells |Elles }van a l'escola en autobús."),
            ex("ca-a1-110", "I'm coming from the market.", "{|Jo }vinc del mercat."),
            ex("ca-a1-111", "What are you (tu) doing?", "Què fas?", "Què fas tu?"),
            ex("ca-a1-112", "We're making dinner.", "{|Nosaltres }fem el sopar."),
            ex("ca-a1-113", "It's sunny today.", "Avui fa sol.", "Fa sol avui."),
            ex("ca-a1-114", "It's very cold.", "Fa molt de fred.", "Fa molt fred."),
            ex("ca-a1-115", "I want to eat.", "{|Jo }vull menjar."),
            ex("ca-a1-116", "What do you (tu) want?", "Què vols?", "Què vols tu?"),
            ex("ca-a1-117", "We want two beers, please.", "{|Nosaltres }volem dues cerveses, si us plau."),
            ex("ca-a1-118", "May I come in?", "Puc entrar?"),
            ex("ca-a1-119", "You (tu) can't park here.", "{|Tu }no pots aparcar aquí."),
            ex("ca-a1-120", "Can you (vosaltres) come tomorrow?", "Podeu venir demà?", "Vosaltres podeu venir demà?"),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-09",
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
            ex("ca-a1-121", "What time is it?", "Quina hora és?", "Quina hora és ara?"),
            ex("ca-a1-122", "It's one o'clock.", "És la una."),
            ex("ca-a1-123", "It's three o'clock.", "Són les tres."),
            ex("ca-a1-124", "It's half past three.", "Són dos quarts de quatre."),
            ex("ca-a1-125", "The class starts at five.", "La classe comença a les cinc."),
            ex("ca-a1-126", "I have two houses and two cars.", "{|Jo }tinc dues cases i dos cotxes."),
            ex("ca-a1-127", "Today is Monday.", "Avui és dilluns."),
            ex("ca-a1-128", "Tomorrow is Saturday.", "Demà és dissabte."),
            ex("ca-a1-129", "We work from Monday to Friday.", "{|Nosaltres }treballem de dilluns a divendres."),
            ex("ca-a1-130", "I'm thirty-one.", "{|Jo }tinc trenta-un anys."),
            ex("ca-a1-131", "There are twelve people.", "Hi ha dotze persones."),
            ex("ca-a1-132", "In the morning, I drink a coffee.", "Al matí, {|jo }bec un cafè."),
            ex("ca-a1-133", "At night, we watch television.", "A la nit, {|nosaltres }mirem la televisió."),
            ex("ca-a1-134", "The shop closes on Sundays.", "La botiga tanca els diumenges.", "La botiga tanca el diumenge."),
            ex("ca-a1-135", "It costs fifteen euros.", "Costa quinze euros."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-10",
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
            ex("ca-a1-136", "Who is it?", "Qui és?"),
            ex("ca-a1-137", "What's this?", "Què és això?"),
            ex("ca-a1-138", "Where's the bathroom?", "On és el lavabo?"),
            ex("ca-a1-139", "When does the train arrive?", "Quan arriba el tren?"),
            ex("ca-a1-140", "How do you say this in Catalan?", "Com es diu això en català?"),
            ex("ca-a1-141", "Why are you (tu) studying Catalan?", "Per què estudies català?"),
            ex("ca-a1-142", "Because I want to live here.", "Perquè {|jo }vull viure aquí."),
            ex("ca-a1-143", "How much does it cost?", "Quant costa?"),
            ex("ca-a1-144", "What time does the shop open?", "A quina hora obre la botiga?"),
            ex("ca-a1-145", "How many brothers do you (tu) have?", "Quants germans tens?"),
            ex("ca-a1-146", "How many sisters does she have?", "Quantes germanes té?", "Quantes germanes té ella?"),
            ex("ca-a1-147", "Which book do you (tu) want?", "Quin llibre vols?"),
            ex("ca-a1-148", "Which house is yours (tu)?", "Quina casa és la teva?"),
            ex("ca-a1-149", "Do you (vosaltres) speak Spanish?", "Parleu castellà?", "Parleu espanyol?", "Vosaltres parleu castellà?"),
            ex("ca-a1-150", "Who do you (tu) live with?", "Amb qui vius?"),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-11",
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
            ex("ca-a1-151", "My mother is from Tarragona.", "La meva mare és de Tarragona."),
            ex("ca-a1-152", "My father works in a bank.", "El meu pare treballa en un banc.", "El meu pare treballa a un banc."),
            ex("ca-a1-153", "Your (tu) brother is very tall.", "El teu germà és molt alt."),
            ex("ca-a1-154", "Your (tu) sisters are nice.", "Les teves germanes són simpàtiques."),
            ex("ca-a1-155", "His daughter lives in Paris.", "La seva filla viu a París."),
            ex("ca-a1-156", "Her son is six.", "El seu fill té sis anys."),
            ex("ca-a1-157", "Our house is small.", "La nostra casa és petita."),
            ex("ca-a1-158", "Our grandfather is eighty.", "El nostre avi té vuitanta anys."),
            ex("ca-a1-159", "Your (vosaltres) parents are at home.", "Els vostres pares són a casa.", "Els vostres pares estan a casa."),
            ex("ca-a1-160", "My grandparents live in a village.", "Els meus avis viuen en un poble.", "Els meus avis viuen a un poble."),
            ex("ca-a1-161", "My grandmother cooks very well.", "La meva àvia cuina molt bé."),
            ex("ca-a1-162", "I have an uncle and an aunt in Reus.", "{|Jo }tinc un oncle i una tia a Reus."),
            ex("ca-a1-163", "My cousin (f) has a dog.", "La meva cosina té un gos."),
            ex("ca-a1-164", "Her husband is a doctor.", "El seu marit és metge."),
            ex("ca-a1-165", "These are my children.", "Aquests són els meus fills."),
        ),
    ),
    Lesson(
        id = "ca-a1-lesson-12",
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
            ex("ca-a1-166", "I like coffee.", "M'agrada el cafè."),
            ex("ca-a1-167", "I like dogs.", "M'agraden els gossos."),
            ex("ca-a1-168", "I like reading.", "M'agrada llegir."),
            ex("ca-a1-169", "Do you (tu) like football?", "T'agrada el futbol?"),
            ex("ca-a1-170", "She likes cats.", "Li agraden els gats.", "A ella li agraden els gats."),
            ex("ca-a1-171", "We like the beach.", "Ens agrada la platja."),
            ex("ca-a1-172", "Do you (vosaltres) like this restaurant?", "Us agrada aquest restaurant?"),
            ex("ca-a1-173", "They like to travel.", "Els agrada viatjar.", "A ells els agrada viatjar.", "A elles els agrada viatjar."),
            ex("ca-a1-174", "I don't speak Russian.", "{|Jo }no parlo rus."),
            ex("ca-a1-175", "I don't like tea.", "No m'agrada el te."),
            ex("ca-a1-176", "I don't want anything.", "{|Jo }no vull res."),
            ex("ca-a1-177", "There's nobody here.", "Aquí no hi ha ningú.", "No hi ha ningú aquí."),
            ex("ca-a1-178", "I never drink wine.", "{|Jo }no bec mai vi.", "{|Jo }mai no bec vi.", "{|Jo }no bec vi mai."),
            ex("ca-a1-179", "I don't know.", "{|Jo }no ho sé."),
            ex("ca-a1-180", "Me neither.", "Jo tampoc."),
        ),
    ),
)
