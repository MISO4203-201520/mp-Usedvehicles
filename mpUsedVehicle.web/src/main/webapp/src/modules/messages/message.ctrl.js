(function (ng) {
    var mod = ng.module('messageModule');

    mod.controller('messageCtrl', ['CrudCreator', '$scope', 'messageService', 'messageModel','$location', 'authService', function (CrudCreator, $scope, svc, model, $location, authSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'message');
            
        $scope.getQuestionsByProvider = function () {
            svc.getQuestionsByProvider(authSvc.getCurrentUser().id).then(function (Questions) {
                        $scope.records = [];
                        $scope.records.push(Questions);
                        console.log("Ingresa Questions" + Questions.id);
            });
        };
            
        $scope.getQuestionsByProvider();

        }]);
})(window.angular);
