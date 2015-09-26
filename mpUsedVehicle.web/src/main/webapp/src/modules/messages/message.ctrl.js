(function (ng) {
    var mod = ng.module('messageModule');

    mod.controller('messageCtrl', ['CrudCreator', '$scope', 'messageService', 'messageModel','$location', 'authService', function (CrudCreator, $scope, svc, model, $location, authSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'message');   
    
        this.answer='';
    
        $scope.getQuestionsByProvider = function () {
            svc.getQuestionsByProvider(authSvc.getCurrentUser().id).then(function (Questions) {
                    $scope.records = Questions;
            });
        };
        
        this.answerQuestion = function(record){
            
           //Tmp answer
            question={
                id: record.id,
                answer: this.answer
            };
            svc.answerQuestion(question);
        
            //clean question
            this.answer='';      
        };
            
        $scope.getQuestionsByProvider();

        }]);
})(window.angular);
