(function(ng){
    var mod = ng.module('messageModule');
    
    mod.service('messageService', ['CrudCreator','messageContext',function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            
            //'Restangular' restangular
            this.askQuestion = function(question){
                this.saveRecord(question);
            };
            this.getQuestionsByProvider = function(idProvider){
                return this.api.one('questionsbyprovider', idProvider).get();
            };
            this.getQuestionsByUser = function(idUser){
                return this.api.one('questionsbyuser', idUser).get();
            };
            this.answerQuestion = function(question){
                restangular.all('messages/'+question.id).customPUT(question);
            };
            
            this.getMessagesByProvider = function(idProvider){
                return this.api.one('getmessagesbyprovider', idProvider).get();
            };
            this.getMessagesByClient = function(idClient){
                return this.api.one('getmessagesbyuser', idClient).get();
            };
            
            this.getClients = function(){
                return this.api.one('getclients').get();
            };
            
            this.getProviders = function(){
                return this.api.one('getproviders').get();
            };
            
            this.sendMessage = function(data){
                return this.api.one("newmessage/").customPOST(data);
            };
            
            this.getClientbyid = function(id){
                return this.api.one('getclientbyid', id).get();
            };
            
            this.getProviderbyid = function(id){
                return this.api.one('getproviderbyid', id).get();
            };
            
            
     }]);
})(window.angular);