angular.module('myApp.translations', ['pascalprecht.translate', 'ngCookies'], ['$translateProvider', function ($translateProvider) {

	$translateProvider.translations('en_EN', {

		/*depends on context */

		'DEPENDS_HAS': 'Has',
		'DEPENDS_ANSWERED': 'answered the questions',

		'DEPENDS_NO_CATEGORY': 'No',
		'DEPENDS_COURSES_AVAILABLE': 'Courses Available',

		'DEPENDS_COURSES': 'Courses',

		'DEPENDS_DOES': 'Does',
		'DEPENDS_TEACH': 'teach well',

		///////////////////////

		'ABOUT': 'About',
		'ABSENT': 'Absent?',
		'ADDITIONAL_INFO': 'Additional info(for admin)',
		'ADD_COACH': 'Add coach',
		'ADD_DATE_CAPITAL' : 'Add Date',
		'ADD_EXTERNAL_COACH': 'Add external coach',
		'ADD_EXTERNAL_PARTICIPANT': 'Add external participant',
		'ADD_FILE': 'Add file',
		'ADD_LESSON': 'Add lesson',
		'ADD_LESSON_CAPITAL': 'Add Lesson',
		'ADD_MANUALLY': 'Add dates manually',
		'ADD_PARTICIPANT': 'Add Participant',
		'ADMIN': 'Admin',
		'ALL' : 'All',
		'ALL_COMPLAINTS_TO': 'All complaints to',
		'ANYTHING_ELSE': 'Anything else?',
		'APPLY': 'Apply',
		'APPROVE_COURSE': 'Approve course',
		'ARCHIVE': 'Archive',
		'ASSESSMENT': 'Assesment',
		'ATTACHMENTS': 'Attachments',
		'A_TO_Z': 'A to Z',
		'BEST_COURSES_HERE': 'Best Courses Here',
		'BLOG': 'Blog',
		'BROWSE': 'Browse',
		'CANCEL': 'Cancel',
		'CAREERS': 'Careers',
		'CATEGORY': 'Category',
		'CHOOSE_TRAINING': 'Choose training',
		'CLICK_ADD_PICTURE': 'Click to add picture',
		'CLICK_TO_ADD_DATE': 'click to add date',
		'CLICK_TO_ENTER': 'click to enter',
		'COACH': 'Coach',
		'COACH_ARCHIVE': 'Coach Archive',
		'COACH_COLON': 'Coach:',
		'COACH_EMAIL': 'Coach email:',
		'COACH_FEEDBACKS': 'Coach Feedbacks',
		'COACH_LOGIN': 'Coach login:',
		'COACH_NAME': 'Coach name:',
		'COACH_PASSWORD': 'Coach password:',
		'COACH_PHONE': 'Coach phone number:',
		'CONTINUE_TO_INFO': 'Continue To Info',
		'COURSE': 'Course',
		'COURSE_DESCRIPTION': 'Write your course description here...',
		'COURSES_NEED_ATTENTION': 'Courses that need your attention',
		'COURSES_SUIT_INTERESTS': 'Courses That Suit Your Interests',
		'COURSE_TYPE': 'Course type:',
		'CREATED_NO_COURSES': 'You haven\'t created any courses yet.',
		'CREATE_COURSE': 'Create Course',
		'DATE': 'Date',
		'DATE_COLON': 'Date:',
		'DELETE_LESSON': 'Delete lesson',
		'DID_YOU_LIKE': 'Did you, personally, like to learn from',
		'EDIT': 'edit',
		'EDIT_COURSE': 'Edit Course',
		'EDIT_DATE_AND_PLACE': 'Edit date & place',
		'EDIT_DATE_COLON': 'Edit date:',
		'EMAIL': 'E-Mail',
		'EN': 'EN',
		'END_DATE': 'End date:',
		'ENTER_COURSE_NAME': 'Enter course name',
		'ENGLISH_SPECIFIC': 'English Specific',
		'FEEDBACK': 'Feedback',
		'FEEDBACKS': 'Feedbacks',
		'FEEDBACK_MAIN': 'The main is emphasized?', //*//
		'FR': 'FR',
		'FROM': 'From',
		'GENERATE_STATISTICS': 'Generate statistics',
		'HARDNESS': 'Is the hardness easily explained?', //*//
		'HAS_ATTENDED': 'Has he attended the lectures?',
		'HELP': 'Help',
		'IS_CREATIVE': 'is creative?',
		'IS_ERUDITE': 'is an erudite person?',
		'IS_KIND': 'is a kind person?',
		'IS_MOTIVATED': 'Is the student motivated?',
		'IS_PATIENT': 'is a patient coach?',
		'IS_WILLING': 'Is he willing to learn?',
		'JUST_FOR YOU': 'Just For You',
		'LANGUAGE': 'Language',
		'LANGUAGE_COLON': 'Language: ',
		'LEAVE': 'Leave',
		'LEAVE_FEEDBACK': 'Leave Feedback',
		'LEAVE_YOUR_FEEDBACK': 'Leave your feedback',
		'LESSON': 'Lesson',
		'LESSONS_LOWERCASE': 'lessons',
		'LEVEL': 'Level',
		'LISTENER': 'Listener',
		'LOGIN': 'login',
		'LOG_IN': 'Log in',
		'LOG_OUT': 'Log out',
		'MANAGE_DATES': 'Manage dates',
		'ME': 'me',
		'MOOD': 'Mood',
		'MY_COURSES': 'My Courses',
		'NAME': 'Name',
		'NO_CATEGORY': 'no category',
		'NO_COURSES_SOON': 'You haven\'t got any courses soon.',
		'NO_FEEDBACKS': 'No feedbacks yet',
		'NO_FILES_UPLOADED': 'No files uploaded yet',
		'NO_PARTICIPANTS': 'No participants yet',
		'NO_SUGGESTIONS': 'no suggestions',
		'NEWS': 'News',
		'NUMBER_OF_LESSONS' : 'Number of lessons',
		'NUMBER_OF_PARTICIPANTS': 'Number of participants: ',
		'OMISSIONS': 'Omissions',
		'ON': 'On',
		'ON_COACH': 'On Coach',
		'ON_STUDENT': 'On Student',
		'PARTICIPANTS': 'Participants',
		'PARTICIPANT_EMAIL': 'Participant email:',
		'PARTICIPANT_LOGIN': 'Participant login:',
		'PARTICIPANT_NAME': 'Participant name:',
		'PARTICIPANT_PHONE': 'Participant phone number:',
		'PASSWORD': 'Password',
		'PHONE': 'Phone',
		'PICK_CATEGORY': 'Pick The Category',
		'PLACE_COLON': 'Place:',
		'PRIVACY': 'Privacy',
		'PROFILE': 'Profile',
		'QUICK_LOOK': 'Quick Look',
		'RATE_EFFECTIVENESS': 'Rate the effectiveness!',
		'RATING': 'Rating',
		'READ_ALL': 'Mark all as read',
		'REASON': 'Reason',
		'RECOMMENDED_FOR': 'Recommended for:',
		'REMEMBER_ME': 'Remember Me',
		'REPEAT_ON': 'Repeat on:',
		'REQUEST': 'Request',
		'REQUEST_FEEDBACK': 'Request Feedback',
		'REQUEST_ON': 'Request feedback on',
		'RU': 'RU',
		'SEARCH': 'Search ...',
		'SELECT': 'select',
		'SELECT_ALL': 'Select All',
		'SELECT_PERSON': 'Select person:',
		'SELECT_TRAINING': 'Select training:',
		'SELECT_TYPE': 'Select type:',
		'SETTINGS': 'Settings',
		'SET_REPEAT': 'Set repeating model',
		'SORRY_NO_DATES': 'Sorry, no dates are known yet',
		'SORRY_NO_DESCRIPTION': 'Sorry, no description for this course.',
		'SORT_BY': 'Sort by',
		'START_DATE': 'Start date:',
		'START_DATE_CAPITAL': 'Start Date',
		'START_DATE_LOWERCASE': 'start date',
		'STATE': 'State',
		'STATISTICS': 'Statistics',
		'STATUS': 'Status',
		'STUDENT': 'Student',
		'STUDENT_ARCHIVE': 'Student Archive',
		'STUDENT_FEEDBACKS': 'Student Feedbacks',
		'SUBSCRIBE': 'Subscribe',
		'TERMS': 'Terms',
		'TIMETABLE': 'Timetable',
		'TOP_RATED_COURSES': 'Top Rated Courses',
		'TO_APPROVE': 'To Approve',
		'TRAINING': 'Training',
		'TRY_TO_FIND': 'Try to find the perfect One...',
		'TURN_ON_SMS': 'Turn SMS notifications on?',
		'UPLOAD_FILE': 'Upload file',
		'UPLOAD_FILES': 'Upload Files',
		'USER': 'User',
		'USERNAME': 'Username',
		'USING_NEW': 'Using new is easy with', //*//
		'WANT_TO_CREATE': 'Want To Create One?',
		'WAS_COMMUNICATIVE': 'Was the student communicative?',
		'WAS_COURSE_CLEAR': 'Was the course clear?',
		'WAS_FOCUSED': 'Was the student focused on the result?',
		'WAS_FRESH': 'Was the material fresh?',
		'WAS_INTERESTING': 'Was it interesting?',
		'WILL_RECOMMEND': 'Will you recommend this course?',
		'WHAT_TO_ADD': 'You can write here',
		'WHEN': 'When',
		'WHERE': 'Where',
		'YOU_MASTER': 'You\'re the Master!',
	});
	// register english translation table
	$translateProvider.translations('ru_RU', {
		/*depends on context */

		'DEPENDS_HAS': 'Ответил ли',
		'DEPENDS_ANSWERED': 'на Ваши вопросы?',

		'DEPENDS_NO_CATEGORY': 'Курсов в категории',
		'DEPENDS_COURSES_AVAILABLE': 'нет',

		'DEPENDS_COURSES': 'Курсов',

		'DEPENDS_DOES': 'Хорошо ли',
		'DEPENDS_TEACH': 'объясняет материал?',

		///////////////////////

		'ABOUT': 'О нас',
		'ABSENT': 'Отсутствовал?',
		'ADDITIONAL_INFO': 'Дополнительная информация(для администратора)',
		'ADD_COACH': 'Добавить тренера',
		'ADD_DATE_CAPITAL' : 'Добавить дату',
		'ADD_EXTERNAL_COACH': 'Добавить внешнего тренера',
		'ADD_EXTERNAL_PARTICIPANT': 'Добавить внешнего слушателя',
		'ADD_FILE': 'Прикрепить файл',
		'ADD_LESSON': 'Добавить занятие',
		'ADD_LESSON_CAPITAL': 'Добавить занятие',
		'ADD_MANUALLY': 'Добавить даты вручную',
		'ADD_PARTICIPANT': 'Добавить участника',
		'ADMIN': 'Админ',
		'ALL' : 'Все',
		'ALL_COMPLAINTS_TO': 'Для жалоб и предложений обращаться к',
		'ANYTHING_ELSE': 'Хотите что-нибудь добавить?',
		'APPLY': 'Применить',
		'APPROVE_COURSE': 'Одобрить курс',
		'ARCHIVE': 'Архив',
		'ASSESSMENT':  'Оценка',
		'ATTACHMENTS': 'Прикрепленные файлы',
		'A_TO_Z': 'от А до Я',
		'BEST_COURSES_HERE': 'Здесь Вы найдете лучшие курсы',
		'BLOG': 'Блог',
		'BROWSE': 'Обзор',
		'CANCEL': 'Отмена',
		'CAREERS': 'Карьеры',
		'CATEGORY': 'Категория',
		'CHOOSE_TRAINING': 'Выбрать тренинг',
		'CLICK_ADD_PICTURE': 'Нажмите, чтобы добавить картинку',
		'CLICK_TO_ADD_DATE': 'нажмите, чтобы добавить дату',
		'CLICK_TO_ENTER': 'нажмите сюда',
		'COACH': 'Тренер',
		'COACH_ARCHIVE': 'Архив тренера',
		'COACH_COLON': 'Тренер:',
		'COACH_EMAIL': 'Электронный адрес:',
		'COACH_LOGIN': 'Логин:',
		'COACH_NAME': 'ФИО:',
		'COACH_PASSWORD': 'Пароль:',
		'COACH_PHONE': 'Телефон:',
		'CONTINUE_TO_INFO': 'Читать далее',
		'COURSE': 'Курс',
		'COURSE_DESCRIPTION': 'Введите здесь описание вашего курса',
		'COURSES_NEED_ATTENTION': 'Ждут Вашего одобрения:',
		'COURSES_SUIT_INTERESTS': 'Рекомендованные Вам курсы',
		'COURSE_TYPE': 'Тип курсов:',
		'CREATED_NO_COURSES': 'Вы еще не создали ни одного курса.',
		'CREATE_COURSE': 'Добавить курс',
		'DATE': 'Дата',
		'DATE_COLON': 'Дата:',
		'DELETE_LESSON': 'Удалить занятие',
		'DID_YOU_LIKE': 'Понравилось ли Вам то, как преподавал(а)',
		'EDIT': 'изменить',
		'EDIT_COURSE': 'Изменить курс',
		'EDIT_DATE_AND_PLACE': 'Изменить время/место',
		'EDIT_DATE_COLON': 'Изменить дату:',
		'EMAIL': 'E-Mail',
		'EN': 'EN',
		'END_DATE': 'Дата окончания:',
		'ENTER_COURSE_NAME': 'Название тренинга',
		'FEEDBACK': 'Отзыв',
		'FEEDBACKS': 'Отзывы',
		'FEEDBACK_MAIN': 'Выделены ли главные моменты занятия?', //*//
		'FR': 'FR',
		'FROM': 'От',
		'GENERATE_STATISTICS': 'Сгенерировать статистику',
		'HARDNESS': 'Хорошо ли был приподнесен материал?', //*//
		'HAS_ATTENDED': 'Посещены ли были занятия?',
		'HELP': 'Помощь',
		'IS_CREATIVE': 'Был(а) ли сотрудник креативен(на)?',
		'IS_ERUDITE': 'Был(а) ли сотрудник эрудирован(на)?',
		'IS_KIND': 'Был(а) ли сотрудник приятен(а)?',
		'IS_MOTIVATED': 'Показал ли сотрудник заинтересованность/мотивированность?',
		'IS_PATIENT': 'Проявляет ли тренер терпение?',
		'IS_WILLING': 'Проявляет ли сотрудник желание обучаться?',
		'JUST_FOR YOU': 'Идеально подойдет Вам',
		'LANGUAGE': 'Язык',
		'LANGUAGE_COLON': 'Язык: ',
		'LEAVE': 'Покинуть курс',
		'LEAVE_FEEDBACK': 'Оставить отзыв',
		'LEAVE_YOUR_FEEDBACK': 'Оставьте свой отзыв',
		'LESSON': 'Занятие',
		'LESSONS_LOWERCASE': 'занятий',
		'LEVEL': 'Уровень',
		'LISTENER': 'Слушатель',
		'LOGIN': 'Логин',
		'LOG_IN': 'Войти',
		'LOG_OUT': 'Выйти',
		'MANAGE_DATES': 'Выбрать даты',
		'ME': 'Я',
		'MOOD': 'Настроение',
		'MY_COURSES': 'Мои курсы',
		'NAME': 'Имя',
		'NO_CATEGORY': 'Нет категории',
		'NO_COURSES_SOON': 'В скором времени у вас нет занятий, отдыхайте.',
		'NO_FEEDBACKS': 'Нет отзывов',
		'NO_FILES_UPLOADED': 'Нет файлов',
		'NO_PARTICIPANTS': 'Нет участников',
		'NO_SUGGESTIONS': 'Нет подходящих участников',
		'NEWS': 'Новости',
		'NUMBER_OF_LESSONS' : 'Количество занятий',
		'NUMBER_OF_PARTICIPANTS': 'Количество участников: ',
		'OMISSIONS': 'Пропуски',
		'ON': 'На',
		'ON_COACH': 'На тренера',
		'ON_STUDENT': 'На сотрудника',
		'PARTICIPANTS': 'Участники',
		'PARTICIPANT_EMAIL': 'Электронный адрес:',
		'PARTICIPANT_LOGIN': 'Логин:',
		'PARTICIPANT_NAME': 'ФИО:',
		'PARTICIPANT_PHONE': 'Телефон:',
		'PASSWORD': 'Пароль',
		'PHONE': 'Телефон',
		'PICK_CATEGORY': 'Выбрать категорию',
		'PLACE_COLON': 'Место:',
		'PRIVACY': 'Конфиденциальность',
		'PROFILE': 'Профиль',
		'QUICK_LOOK': 'Быстрый поиск',
		'RATE_EFFECTIVENESS': 'Оцените эффективность!',
		'RATING': 'Рейтинг',
		'READ_ALL': 'Отметить как прочитанное',
		'REASON': 'Причина',
		'RECOMMENDED_FOR': 'Кого может интересовать этот курс:',
		'REMEMBER_ME': 'Запомнить меня',
		'REPEAT_ON': 'Повторять по:',
		'REQUEST': 'Запрос',
		'REQUEST_FEEDBACK': 'Запросить отзыв',
		'REQUEST_ON': 'Запрос на',
		'RU': 'RU',
		'SEARCH': 'Поиск ...',
		'SELECT': 'выбрать',
		'SELECT_ALL': 'Выбрать все',
		'SELECT_PERSON': 'Выбрать сотрудника:',
		'SELECT_TRAINING': 'Выбрать тренинг:',
		'SELECT_TYPE': 'Выбрать тип:',
		'SETTINGS': 'Настройки',
		'SET_REPEAT': 'Задать режим повторения',
		'SORRY_NO_DATES': 'Дат курса еще нет, приносим извинения.',
		'SORRY_NO_DESCRIPTION': 'Описание курса отсутствует, приносим извинения.',
		'SORT_BY': 'Сортировать по',
		'START_DATE': 'Дата начала:',
		'START_DATE_CAPITAL': 'Дата начала',
		'START_DATE_LOWERCASE': 'Дата начала',
		'STATE': 'Статус',
		'STATISTICS': 'Статистика',
		'STATUS': 'Статус',
		'STUDENT': 'Cтудент',
		'STUDENT_ARCHIVE': 'Архив сотрудника',
		'STUDENT_FEEDBACKS': 'Отзывы о сотруднике',
		'SUBSCRIBE': 'Подписаться',
		'TERMS': 'Условия',
		'TIMETABLE': 'Расписание',
		'TOP_RATED_COURSES': 'Топ Курсы',
		'TO_APPROVE': 'Обновления',
		'TRAINING': 'Тренинг',
		'TRY_TO_FIND': 'Найдите "Тот Самый"...',
		'TURN_ON_SMS': 'Включить SMS-оповещения?',
		'UPLOAD_FILE': 'Добавить файл',
		'UPLOAD_FILES': 'Добавить файлы',
		'USER': 'Сотрудник',
		'USERNAME': 'Логин',
		'USING_NEW': 'Легко ли было применять на практике полученные знания?',
		'WANT_TO_CREATE': 'Желаете создать новый курс?',
		'WAS_COMMUNICATIVE': 'Был ли сотрудник коммуникативен(на)?',
		'WAS_COURSE_CLEAR': 'Все ли было понятно?',
		'WAS_FOCUSED': 'Был ли сотрудник нацелен на хороший результат?',
		'WAS_FRESH': 'Была ли информация акутальной?',
		'WAS_INTERESTING': 'Интересно ли был приподнесен материал?',
		'WILL_RECOMMEND': 'Порекомендуете ли вы кому-нибудь этот курс?',
		'WHAT_TO_ADD': 'Можете писать здесь',
		'WHEN': 'Когда',
		'WHERE': 'Где',
		'YOU_MASTER': 'Вы тренер!',
	});

	$translateProvider.translations('fr_FR', {
		/*depends on context */

		'DEPENDS_HAS': 'Est-ce que',
		'DEPENDS_ANSWERED': 'a repondu à vos questions?',

		'DEPENDS_NO_CATEGORY': 'Pas de',
		'DEPENDS_COURSES_AVAILABLE': 'cours disponibles',

		'DEPENDS_COURSES': 'Cours',

		'DEPENDS_DOES': 'Est-ce que',
		'DEPENDS_TEACH': 'explique bien?',

		///////////////////////

		'ABOUT': 'Infos',
		'ABSENT': 'Absent?',
		'ADDITIONAL_INFO': 'Commentaire pour administrateur',
		'ADD_COACH': 'Ajouter un tuteur',
		'ADD_DATE_CAPITAL' : 'Ajouter une date',
		'ADD_EXTERNAL_COACH': 'Ajouter un tuteur externe',
		'ADD_EXTERNAL_PARTICIPANT': 'Ajouter un écouteur externe',
		'ADD_FILE': 'Joindre un fichier',
		'ADD_LESSON': 'Ajouter un leçon',
		'ADD_LESSON_CAPITAL': 'Ajouter un leçon',
		'ADD_MANUALLY': 'Ajouter les dates manuellement',
		'ADD_PARTICIPANT': 'Ajouter un participant',
		'ADMIN': 'Administrateur',
		'ALL' : 'Tout',
		'ALL_COMPLAINTS_TO': 'En cas de plaintes, addressez vous à',
		'ANYTHING_ELSE': 'Quelque chose d\'autre?',
		'APPLY': 'Appliquer',
		'APPROVE_COURSR': 'Approuver le cours',
		'ARCHIVE': 'Archives',
		'ASSESSMENT': 'Evaluation',
		'ATTACHMENTS': 'Fichiers joints',
		'A_TO_Z': 'A à Z',
		'BEST_COURSES_HERE': 'Ici vous trouverez les meilleurs cours',
		'BLOG': 'Blog',
		'BROWSE': 'Cours',
		'CANCEL': 'Annuler',
		'CAREERS': 'Carrières',
		'CATEGORY': 'Catégorie',
		'CHOOSE_TRAINING': 'Choisir un cours',
		'CLICK_ADD_PICTURE': 'Cliquer pour joindre une image',
		'CLICK_TO_ADD_DATE': 'cliquer pour ajouter une date',
		'CLICK_TO_ENTER': 'cliquer pour taper',
		'COACH': 'Tuteur',
		'COACH_ARCHIVE': 'Archives de tuteur',
		'COACH_COLON': 'Tuteur:',
		'COACH_EMAIL': 'E-mail:',
		'COACH_LOGIN': 'Login:',
		'COACH_NAME': 'Nom:',
		'COACH_PASSWORD': 'Mot-de-passe:',
		'COACH_PHONE': 'Numèro de téléphone:',
		'CONTINUE_TO_INFO': 'En lire plus',
		'COURSE': 'Cours',
		'COURSE_DESCRIPTION': 'Ecrivez la description de votre cours ici...',
		'COURSES_NEED_ATTENTION': 'Cours à confirmer',
		'COURSES_SUIT_INTERESTS': 'Cours recommandés pour vous',
		'COURSE_TYPE': 'Type de cours:',
		'CREATED_NO_COURSES': 'Vous n\'avez pas encore créé de cours.',
		'CREATE_COURSE': 'Ajouter un cours',
		'DATE': 'Date',
		'DATE_COLON': 'Date:',
		'DELETE_LESSON': 'Supprimer un leçon',
		'DID_YOU_LIKE': 'Avez-vous aimé la maniere d\'expliquer?',
		'EDIT': 'éditer',
		'EDIT_COURSE': 'Editer le cours',
		'EDIT_DATE_AND_PLACE': 'Editer des date/endroit',
		'EDIT_DATE_COLON': 'Editer une date:',
		'ENTER_COURSE_NAME': 'Le nom de cours',
		'EMAIL': 'E-Mail',
		'EN': 'EN',
		'END_DATE': 'Date de terminaison:',
		'FEEDBACKS': 'Commentaires',
		'FEEDBACK': 'Commentaire',
		'FEEDBACK_MAIN': 'Est-ce que les moments principaux ont été touchés?',
		'FR': 'FR',
		'FROM': 'Auteur',
		'GENERATE_STATISTICS': 'Générer la statistique',
		'HARDNESS': 'Est-ce que tout était clair?', //*//
		'HAS_ATTENDED': 'L\'étudiant a-t-il été présent?',
		'HELP': 'Aide',
		'IS_CREATIVE': 'A-t-il/elle été créatif(ve)?',
		'IS_ERUDITE': 'Est-il/elle érudit(e)?',
		'IS_KIND': 'Est-il/elle gentil(le)?',
		'IS_MOTIVATED': 'Est-ce que le student était motivé?',
		'IS_PATIENT': 'Le tuteur était-il/elle patient(e)?',
		'IS_WILLING': 'Est-ce que l\'étudiant a démontré l\'envie d\'apprendre?',
		'JUST_FOR YOU': 'Juste pour vous',
		'LANGUAGE': 'Langage',
		'LANGUAGE_COLON': 'Langage: ',
		'LEAVE': 'Quitter',
		'LEAVE_FEEDBACK': 'Ajouter une commentaire',
		'LEAVE_YOUR_FEEDBACK': 'Ajoutez votre commentaire',
		'LEVEL': 'Niveau',
		'LESSON': 'Leçon',
		'LESSONS_LOWERCASE': 'leçons',
		'LISTENER': 'Ecouteur',
		'LOGIN': 'login',
		'LOG_IN': 'Se connecter',
		'LOG_OUT': 'Se déconnecter',
		'MANAGE_DATES': 'Gérer des dates',
		'ME': 'Moi',
		'MY_COURSES': 'Mes Cours',
		'NAME': 'Nom',
		'NO_CATEGORY': 'pas de catégorie',
		'NO_COURSES_SOON': 'Vous n\'avez pas de cours bientôt.',
		'NO_FEEDBACKS': 'Pas de commentaires',
		'NO_FILES_UPLOADED': 'Pas de fichiers joints',
		'NO_PARTICIPANTS': 'Pas de participants',
		'NO_SUGGESTIONS': 'pas de suggestions',
		'NEWS': 'Actualités',
		'NUMBER_OF_LESSONS' : 'Nombre de leçons',
		'NUMBER_OF_PARTICIPANTS': 'Nombre de participants: ',
		'OMISSIONS': 'Omissions',
		'ON': 'Sur',
		'ON_COACH': 'Sur le tuteur',
		'ON_STUDENT': 'Sur l\'étudiant',
		'PARTICIPANTS': 'Participants',
		'PARTICIPANT_EMAIL': 'E-mail:',
		'PARTICIPANT_LOGIN': 'Login:',
		'PARTICIPANT_NAME': 'Nom:',
		'PARTICIPANT_PHONE': 'Numèro de téléphone:',
		'PASSWORD': 'Mot-de-passe',
		'PHONE': 'Numèro de téléphone',
		'PICK_CATEGORY': 'Choisir une catégorie',
		'PLACE_COLON': 'Endroit:',
		'PRIVACY': 'Confidentialité',
		'PROFILE': 'Profile',
		'QUICK_LOOK': 'Rechercher',
		'RATE_EFFECTIVENESS': 'Evaluez l\'efficacité!',
		'RATING': 'Evaluation',
		'READ_ALL': 'Marquer comme lu',
		'REASON': 'Raison',
		'RECOMMENDED_FOR': 'Recommandé pour:',
		'REMEMBER_ME': 'Se souvenir de moi',
		'REPEAT_ON': 'Répéter:',
		'REQUEST': 'Demande',
		'REQUEST_FEEDBACK': 'Demander un commentaire',
		'REQUEST_ON': 'Demande de',
		'RU': 'RU',
		'SEARCH': 'Rechercher ...',
		'SELECT': 'sélectionner',
		'SELECT_ALL': 'Sélectionner tout',
		'SELECT_PERSON': 'Sélectionner une personne:',
		'SELECT_TRAINING': 'Sélectionner un cours:',
		'SELECT_TYPE': 'Sélectionner le type:',
		'SETTINGS': 'Paramètres',
		'SET_REPEAT': 'Détérminer la façon de la répétition',
		'SORRY_NO_DATES': 'Désolé, les dates ne sont pas encore déterminées.',
		'SORRY_NO_DESCRIPTION': 'Désolé, pas de description de cours.',
		'SORT_BY': 'Filtrer par',
		'START_DATE': 'Date de commencement:',
		'START_DATE_CAPITAL': 'Date de commencement',
		'START_DATE_LOWERCASE': 'Date de commencement',
		'STATISTICS': 'Statistique',
		'STATUS': 'Statut',
		'STUDENT': 'Etudiant',
		'STUDENT_ARCHIVE': 'Archives d\'étudiant',
		'STUDENT_FEEDBACKS': 'Commentaires sur étudiant',
		'SUBSCRIBE': 'S\'inscrire',
		'TERMS': 'Termes',
		'TIMETABLE': 'Horaire',
		'TOP_RATED_COURSES': 'Cours prestigieux',
		'TO_APPROVE': 'Confirmation',
		'TRAINING': 'Cours',
		'TRY_TO_FIND': 'Trouvez le Meilleur...',
		'TURN_ON_SMS': 'Recevoir les notifications par SMS?',
		'UPLOAD_FILE': 'Joindre un fichier',
		'UPLOAD_FILES': 'Joindre des fichiers',
		'USER': 'Utilisateur',
		'USERNAME': 'Login',
		'USING_NEW': 'Est-ce que c\'était facile d\'appliquer de nouvelles connaissances en pratique',
		'WANT_TO_CREATE': 'Voulez-vous en ajouter un?',
		'WAS_COMMUNICATIVE': 'Est-ce que l\'étudiant était communicatif(ve)',
		'WAS_COURSE_CLEAR': 'Etait le cours clair?',
		'WAS_FOCUSED': 'Est-ce que l\'étudiant était concentré(e)?',
		'WAS_FRESH': 'L\'information, était-elle actuelle?',
		'WAS_INTERESTING': 'Le cours, était-il intéressant?',
		'WILL_RECOMMEND': 'Recommandereriez-vous ce cours à quelqu\'un?',
		'WHAT_TO_ADD': 'Ecrivez ici',
		'WHEN': 'Quand',
		'WHERE': 'Où',
		'YOU_MASTER': 'Vous en êtes tuteur!',
	});

	$translateProvider.preferredLanguage('en_EN');
	$translateProvider.useCookieStorage();

}]);