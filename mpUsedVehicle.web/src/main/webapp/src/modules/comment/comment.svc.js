(function(ng){
    var mod = ng.module('commentModule');
    
    mod.service('commentService', ['CrudCreator','commentContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            
            
            this.sendComment = function(comment){
                this.saveRecord(comment);
            };
            
    }]);


})(window.angular);
