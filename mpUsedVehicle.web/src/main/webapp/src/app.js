(function (ng) {

    var mainApp = ng.module('mainApp', [
//        'ngCrudMock',
        'authModule',
        'cartItemModule',
        'clientModule',
        'productModule',
        'providerModule',
        'vehicleModule',
        'commentModule',
        'messageModule',
        'checkoutModule',
        'ngRoute',
        'ngCrud',
        'xeditable'
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
                .when('/comment', {
                    templateUrl: tplUrl,
                    controller: 'commentCtrl',
                    controllerAs: alias
                })
                .when('/detail', {
                        templateUrl: 'src/modules/product/detail.tpl.html',
                        controller: 'commentCtrl',
                        controllerAs: 'ctrl'
                })
                .when('/vehicle', {
                    templateUrl: tplUrl,
                    controller: 'vehicleCtrl',
                    controllerAs: alias
                }).when('/catalog', {
                        templateUrl: 'src/modules/product/product.tpl.html',
                        controller: 'productCtrl',
                        controllerAs: 'ctrl'
                }).when('/shoppingCart', {
                    templateUrl: 'src/modules/cartItem/templates/ShoppingCart.html',
                    controller: 'cartItemCtrl',
                    controllerAs: 'ctrl'
                }).when('/checkout', {
                    templateUrl: 'src/modules/checkout/templates/Checkout.html',
                    controller: 'checkoutCtrl',
                    controllerAs: 'ctrl'
                })
                .otherwise('/catalog');
        }]);
    
    mainApp.config(['authServiceProvider', function (auth) {
            auth.setValues({
                apiUrl: 'users',
                successPath: '/catalog',
                loginPath: '/login',
                registerPath: '/register',
                logoutRedirect: '/login',
                loginURL: 'login',
                registerURL: 'register',
                logoutURL: 'logout',
                nameCookie: 'userCookie'
            });
        }]);

    mainApp.run(function (editableOptions) {
        editableOptions.theme = 'bs3'; // bootstrap3 theme. For Xeditable plugin Angular
    });
})(window.angular);
