(function(ng){
    var mod = ng.module('messageModule');
    
    mod.service('messageService', ['CrudCreator','messageContext', 'Restangular',function(CrudCreator, context, restangular){
            CrudCreator.extendService(this, context);
            this.askQuestion = function(question){
                this.saveRecord(question);
            };
            this.getQuestionsByProvider = function(idProvider){
                return this.api.one('questionsbyprovider', idProvider).get();
            };
            this.answerQuestion = function(question){
                restangular.all('messages/'+question.id).customPUT(question);
            };
     }]);
})(window.angular);