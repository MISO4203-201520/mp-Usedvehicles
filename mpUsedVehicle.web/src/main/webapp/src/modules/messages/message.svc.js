(function(ng){
    var mod = ng.module('messageModule');
    
    mod.service('messageService', ['CrudCreator','messageContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.askQuestion = function(question){
                this.saveRecord(question);
            };
            this.getQuestionsByProvider = function(idProvider){
                var res=this.api.one('questionsbyprovider', idProvider).get();
                return res;
            };
            this.answerQuestion = function(question){
                this.saveRecord(question);
            };
     }]);
})(window.angular);