angular.module('myApp.translations', ['pascalprecht.translate'], ['$translateProvider', function ($translateProvider) {

	$translateProvider.translations('en_EN', {
		'TO_APPROVE': 'To Approve'
	});
	// register english translation table
	$translateProvider.translations('ru_RU', {
		'TO_APPROVE': 'Подтверждение'
	});

	$translateProvider.preferredLanguage('en_EN');

}]);