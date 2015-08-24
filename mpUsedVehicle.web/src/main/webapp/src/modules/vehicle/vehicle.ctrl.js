(function (ng) {
    var mod = ng.module('vehicleModule');

    mod.controller('vehicleCtrl', ['CrudCreator', '$scope', 'vehicleService', 'vehicleModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'vehicle', 'Vehicle');
            this.fetchRecords();
        }]);
})(window.angular);
