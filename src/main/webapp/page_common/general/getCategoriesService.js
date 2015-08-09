angular.module('myApp')
	.factory('getCategories', ['$http', function ($http) {
		return function () {
			return $http.get('http://localhost:8080/allCategories')
				.success(function (data) {
					data.unshift({id: 0, name: "All",pictureLink:"assets/category/all.jpeg"});
					return data;
				})
				.error(function (err) {
					return err;
				})
		};
	}])
	.factory('categoriesLocal', ["$q", "getCategories", function ($q, getCategories) {
		var categories = [];

		var api = {};
		var prom = $q.defer();


		api.getCategories = function () {

			if (!prom.promise.$$state.status) {
				getCategories().success(function (data) {

					prom.resolve(data);

				});
			}
			return prom.promise;
		}

		api.getCategoryNameById = function (id) {
			var pr = $q.defer();

			getCategories().then(function (result) {

				if (result.data.length > +id) {
					pr.resolve(result.data[id].name);

				} else {
					pr.reject("Oh Man, Oh Man");
				}


			}, function (error) {
				pr.reject(error)
			});

			return pr.promise;
		}
		return api;
	}]);