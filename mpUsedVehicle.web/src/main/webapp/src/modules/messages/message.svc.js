(function(ng){
    var mod = ng.module('messageModule');
    
    mod.service('messageService', ['CrudCreator','messageContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
    }]);

    mod.service('messageService', ['CrudCreator','messageContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.askQuestion = function(question){
                this.saveRecord(question);
            };
            this.getQuestionsByProvider = function(idProvider){
                return this.api.one('questionsbyprovider', idProvider).get();
            };
            this.answerQuestion = function(question){
                console.log(question.id+ question.answer);
                //this.put(question);
            };
     }]);
})(window.angular);