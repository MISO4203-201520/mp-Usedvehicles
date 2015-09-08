(function(ng){
    var mod = ng.module('commentModule');
    
    mod.service('commentService', ['CrudCreator','commentContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            
            
            var self = this;
            
            this.getCommentsByProduct = function (productId) {
                return true;
            };
            
    }]);


})(window.angular);
