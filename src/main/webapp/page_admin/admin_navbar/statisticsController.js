/**
 * Created by Yoga 3 Pro on 30.07.2015.
 */
angular.module('myApp.admin')
    .controller('StatisticsController', ['$scope', '$http', '$filter', 'adminService', function($scope, $http, $filter, adminService) {
		$scope.temp = {};
		$scope.temp.tempDates = [];

      /*  $scope.users = ['User1', 'User2' ];*/
        $scope.trainings = ['Training1', 'Training2'];
        $scope.types = ['Select Training', 'Select Listener'];
		$scope.partType = ['Count of pass', 'Dates of pass'];
		$scope.fullPass = ['Yes', 'No'];
        $scope.currentType = true;
        $scope.currentType2 = true;
        $scope.fullstat = true;
        $scope.partstat = true;

		$http.get('http://localhost:8080/user_controller/select_all_users_login').success(function(usersData) {
			$scope.users = usersData;
			console.log(usersData);
		});

		/*$http.get('http://localhost:8080/').success(function(trainingData) {
			$scope.trainings = trainingData;
			console.log(trainingData);
		});*/

		$scope.save = function (userselect){
			userselect.startDate = $filter('date')($scope.temp.tempDates[0], 'yyyy-MM-dd');
			userselect.endDate = $filter('date')($scope.temp.tempDates[1], 'yyyy-MM-dd');
			console.log(userselect);
			adminService.sendStatistics(userselect);
			};

    }]);