(function (ng) {
    var mod = ng.module('orderModule');

    mod.controller('orderCtrl', ['CrudCreator', '$scope', 'orderService', 'orderModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'order', 'My Orders');
            svc.getOrderByProvider().then(function (result) {
                    $scope.orders = [];
                    $scope.orders = result;
            });
            svcUser.api.one('currentUser').get().then(function(user) {
            console.log(user.role);
            $scope.role=user.role;
            if($scope.role==="provider"){
               $scope.getOrderByProvider(); 
            }else{
                $scope.getOrderByClient();
            }
        });   
    }]);
})(window.angular);
