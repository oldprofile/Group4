angular.module('myApp.translations', ['pascalprecht.translate'], ['$translateProvider', function ($translateProvider) {

	$translateProvider.translations('en_EN', {
		'COACH': 'Coach',
		'COURSES_NEED_ATTENTION': 'Courses that need your attention',
		'END_DATE': 'End date:',
		'GENERATE_STATISTICS': 'Generate statistics',
		'LISTENER': 'Listener',
		'NAME': 'Name',
		'NEWS': 'News',
		'READ_ALL': 'Read All',
		'SELECT': 'select',
		'SELECT_PERSON': 'Select person:',
		'SELECT_TRAINING': 'Select training:',
		'SELECT_TYPE': 'Select type:',
		'START_DATE': 'Start date:',
		'STATISTICS': 'Statistics',
		'STATUS': 'Status',
		'TO_APPROVE': 'To Approve',
		'TRAINING': 'Training',
	});
	// register english translation table
	$translateProvider.translations('ru_RU', {
		'TO_APPROVE': 'Подтверждение'
	});

	$translateProvider.preferredLanguage('en_EN');

}]);