angular.module('myApp.notifications', ['ngWebSocket'])
.factory('MyData', ['$websocket', function($websocket) {
      // Open a WebSocket connection
      var dataStream = $websocket('ws://localhost:8080/demo/actions');

      var collection = [];

      dataStream.onMessage(function(message) {
        collection.push(JSON.parse(message.data));
      });

      var methods = {
        collection: collection,
        get: function() {
          dataStream.send(JSON.stringify({ action: 'get' }));
        }
      };

      return methods;
    }]).controller('SomeController', ['$scope', 'MyData', function ($scope, MyData) {
      $scope.MyData = MyData;
    }]);
  