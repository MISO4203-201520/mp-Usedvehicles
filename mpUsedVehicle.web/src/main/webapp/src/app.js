(function (ng) {

    var mainApp = ng.module('mainApp', [
        //'ngCrudMock',
        'authModule',
        'cartItemModule',
        'clientModule',
        'productModule',
        'providerModule',
        'vehicleModule',
        'ngRoute',
        'ngCrud'
    ]);

    mainApp.config(['$routeProvider', 'CrudTemplateURL', 'CrudCtrlAlias', function ($routeProvider, tplUrl, alias) {
            $routeProvider
                .when('/client', {
                    templateUrl: tplUrl,
                    controller: 'clientCtrl',
                    controllerAs: alias
                })
                .when('/provider', {
                    templateUrl: tplUrl,
                    controller: 'providerCtrl',
                    controllerAs: alias
                })
                .when('/vehicle', {
                    templateUrl: tplUrl,
                    controller: 'vehicleCtrl',
                    controllerAs: alias
                })
                .otherwise('/');
        }]);
})(window.angular);
