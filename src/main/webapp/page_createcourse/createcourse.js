'use strict';
angular.module('myApp.createcourse', ['ngRoute', 'ui.bootstrap.datetimepicker', 'ui.bootstrap', 'ui.bootstrap.dropdown'])
	.config(['$routeProvider', function ($routeProvider) {
		$routeProvider.when('/createcourse', {
			templateUrl: 'page_createcourse/createcourse.html',
			controller: 'CreateCourseController'
		});
	}])
	.directive('preloadable', function () {
		return {
			link: function (scope, element) {
				element.addClass("empty");
				element.bind("load", function (e) {
					element.removeClass("empty");
				});
			}
		}
	})
	.directive('closeOnClick', [function () {
		return {
			restrict: 'A',
			scope: false,
			link: function (scope, elem, attrs) {
				var toggledElement = angular.element('#' + attrs.closeOnClick);

				elem.click(function () {
					setTimeout(function () {
						toggledElement.trigger('click');
					}, 0)
				})

			}
		};
	}])
;