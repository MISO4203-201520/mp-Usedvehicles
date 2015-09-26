(function (ng) {
    var mod = ng.module('providerModule');

    mod.controller('providerCtrl', ['CrudCreator', '$scope', 'providerService', 'providerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'provider', 'Provider');
            this.fetchRecords();
        }]);
    
})(window.angular);
