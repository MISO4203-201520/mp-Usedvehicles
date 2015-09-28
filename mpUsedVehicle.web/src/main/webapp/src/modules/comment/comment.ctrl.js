(function (ng) {
    var mod = ng.module('commentModule');

    mod.controller('commentCtrl', ['CrudCreator', '$scope', 'commentService', 'productService', 'commentModel', function (CrudCreator, $scope, svc, productSvc , model) {
            CrudCreator.extendController(this, svc, $scope, model, 'comment', 'Comment');
            
            
            
                var current = productSvc.getCurrentProduct();
            
            
            
            this.fetchRecords();
        }]);
    


})(window.angular);
