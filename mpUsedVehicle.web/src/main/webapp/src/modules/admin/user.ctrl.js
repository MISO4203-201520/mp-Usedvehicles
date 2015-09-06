(function (ng) {
    var mod = ng.module('userModule');

    mod.controller('userCtrl', ['CrudCreator', '$scope', 'userService', 'userModel', 
        function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'user', 'Users');
            
            this.fetchRecords();
        }]);
})(window.angular);
