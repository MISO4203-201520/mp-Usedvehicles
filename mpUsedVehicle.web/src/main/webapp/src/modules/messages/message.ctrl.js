(function (ng) {
    var mod = ng.module('messageModule');

    mod.controller('messageCtrl', ['CrudCreator', '$scope', 'messageService', 'messageModel','$location', 'authService', function (CrudCreator, $scope, svc, model, $location, authSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'message');

        }]);
})(window.angular);
