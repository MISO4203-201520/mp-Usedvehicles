(function(ng){
    var mod = ng.module('productModule');
    
    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);   
            

            this.askQuestion = function(question){
                this.saveRecord(question);
            };
            
            var selectedProductId = 'ini';
            this.getSelectedProductId = function () {
                return selectedProductId;
            };
            this.setSelectedProductId = function (productId) {
                selectedProductId=productId;
            };
            
            
    }]);


//    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
//           CrudCreator.extendService(this, context);
//            this.askQuestion = function(question){
//                this.saveRecord(question);
//            };
//     }]);

 
})(window.angular);