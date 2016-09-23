angular
    .module('student', [])
    .controller('studentCtrl', function($scope, $http) {
        $scope.addStudent = function() {
            $http.post('http://localhost:8080/student', $scope.studentName);
        }

        $scope.deleteStudent = function() {
            $http.delete('http://localhost:8080/student')
        }
    });
