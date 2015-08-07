/**
 * Created by Yoga 3 Pro on 30.07.2015.
 */
angular.module('myApp.admin')
    .controller('StatisticsController', ['$scope', '$http', '$filter', 'adminService', function($scope, $http, $filter, adminService) {
		$scope.temp = {};
		$scope.temp.tempDates = [];

		$scope.statisticTrainType = ['Users list', 'Dates of pass', 'Count of pass'];
		$scope.statisticListType = ['Trainings list', 'Dates of pass plus feedback', 'Count of pass'];
        $scope.currentType = true;
        $scope.currentType2 = false;

		$scope.one = function(){
			if($scope.currentType === true){

				$scope.currentType2 = false;
			}
			else{
				$scope.currentType2 = true;
			}
		}
		$scope.two = function(){
			if($scope.currentType2 === true){
				$scope.currentType = false;
			}
			else{
				$scope.currentType = true;

			}
		}



		$http.get('http://localhost:8080/user_controller/select_all_users_login').success(function(usersData) {
			$scope.users = usersData;
			console.log (usersData);

		});

		$http.get('http://localhost:8080/training_controller/names_list').success(function(trainingData) {
			$scope.trainings = trainingData;
		});

		$scope.save = function (userselect){
			userselect.dateFrom = $filter('date')($scope.temp.tempDates[0], 'yyyy-MM-dd');
			userselect.dateTo = $filter('date')($scope.temp.tempDates[1], 'yyyy-MM-dd');
			var myDate = new Date();
			var myDay;
			var myMonth;
			if (myDate.getUTCDate()<10){
				var d = "0";
				myDay = d.concat(myDate.getUTCDate().toString());
			}
			else myDay = myDate.getUTCDate().toString();
			myMonth = myDate.getMonth()+1;
			if (myDate.getUTCMonth()+1<10){
				var d = "0";
				myMonth = d.concat(myDate.getUTCMonth()+1);
				myMonth = myMonth.toString();
			}
			else myDay = myMonth.getUTCDate().toString();
			var a = myDate.getUTCFullYear().toString();
			a = a.concat("-", myMonth, "-", myDay);
			if (userselect.dateFrom>userselect.dateTo){
				alert ('You are wrong! Check the second date!');
				return;
			}
			if (userselect.dateTo > a || userselect.dateFrom > a){
				alert ('One of date later today!');
				return;
			}
			console.log($scope.currentType);
			console.log($scope.currentType2);
			userselect.whoOrWhat = ""
			if (userselect.type==='Users list'|| userselect.type ==='Trainings list'){
				userselect.type='1';
			}
			if (userselect.type==='Dates of pass' || userselect.type ==='Dates of pass plus feedback'){
				userselect.type='2';
			}
			if (userselect.type==='Count of pass'){
				userselect.type = '3';
			}
			if (userselect.type == null){
				alert ('Please select type of statistic!');
				return;
			}
			if (userselect.trainingName == null){
				userselect.trainingName = "";
			}
			if (userselect.userLogin == null){
				userselect.userLogin = "";
			}
			if (userselect.userLogin== null && userselect.trainingName == null){
				alert ('')
			}
			console.log(userselect.type);
			adminService.sendStatistics(userselect);
			};

    }]);