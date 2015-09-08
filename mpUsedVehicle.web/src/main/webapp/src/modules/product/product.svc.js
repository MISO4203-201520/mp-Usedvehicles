(function(ng){
    var mod = ng.module('productModule');
    
    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);   
            

            this.askQuestion = function(question){
                this.saveRecord(question);
            };
            
    }]);


//    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
//           CrudCreator.extendService(this, context);
//            this.askQuestion = function(question){
//                this.saveRecord(question);
//            };
//     }]);

 
})(window.angular);