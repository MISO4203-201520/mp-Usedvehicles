(function(ng){
    var mod = ng.module('productModule');
    
    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);   
            
            var current = {};
            
            this.setCurrentProduct = function (record) {
                current = record;
            };
            
            this.getCurrentProduct = function () {
                return current;
            };
            
    }]);

    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.askQuestion = function(question){
                this.saveRecord(question);
            };
     }]);
})(window.angular);