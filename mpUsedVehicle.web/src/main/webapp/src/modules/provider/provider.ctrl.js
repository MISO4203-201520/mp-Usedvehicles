(function (ng) {
    var mod = ng.module('providerModule');

    mod.controller('providerCtrl', ['CrudCreator', '$scope', 'providerService', 'providerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'provider', 'Provider');
            
             $scope.findProviderbyBrand = function () {
                console.log("$scope.records" + $scope.records.length);
                console.log("Ingresa brandFilter" + $scope.brandFilter);
               svc.findProviderbyBrand($scope.brandFilter).then(function (ProviderBrand) {
                        $scope.records = [];
                        $scope.records.push(ProviderBrand);
                        console.log("Ingresa ProviderBrand" + ProviderBrand.name);
                    });

            };
            $scope.findProviderbyModel = function () {
                console.log("$scope.records" + $scope.records.length);
                console.log("Ingresa modelFilter" + $scope.modelFilter);
               svc.findProviderbyModel($scope.modelFilter).then(function (ProviderModel) {
                        $scope.records = [];
                        $scope.records.push(ProviderModel);
                        console.log("Ingresa ProviderModel" + ProviderModel.name);
                    });

            };
            $scope.findProviderbyCity = function () {
                console.log("$scope.records" + $scope.records.length);
                console.log("Ingresa cityFilter" + $scope.cityFilter);
               svc.findProviderbyCity($scope.cityFilter).then(function (ProviderCity) {
                        $scope.records = [];
                        $scope.records.push(ProviderCity);
                        console.log("Ingresa ProviderCity" + ProviderCity.name);
                    });

            };

            $scope.findProviderbyPrice = function () {
                console.log("$scope.records" + $scope.records.length);
                console.log("Ingresa lowerFilter" + $scope.lowerFilter);
                console.log("Ingresa upperFilter" + $scope.upperFilter);
               svc.findProviderbyPrice($scope.lowerFilter, $scope.upperFilter).then(function (ProviderPrice) {
                        $scope.records = [];
                        $scope.records.push(ProviderPrice);
                        console.log("Ingresa ProviderPrice" + ProviderPrice.name);
                    });

            };
            this.fetchRecords();
        }]);
    
    mod.controller('providersCtrl', ['CrudCreator', '$scope', 'providerService', 'providerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'provider', 'Providers');

    }]);
})(window.angular);
