(function (ng) {
    var mod = ng.module('messageModule');

    mod.controller('messageCtrl', ['CrudCreator', '$scope', 'messageService', 'messageModel','$location', 'authService', 'userService' , function (CrudCreator, $scope, svc, model, $location, authSvc, svcUser) {
            CrudCreator.extendController(this, svc, $scope, model, 'message');   
        
        this.answer='';
        this.user='';
        this.data = [];
        this.userlist = []
        var usersarr = [];
        
        $scope.getQuestionsByProvider = function () {
            svc.getQuestionsByProvider(authSvc.getCurrentUser().id).then(function (Questions) {
                    $scope.records = Questions;
                    
            });
        };
        $scope.getQuestionsByUser = function () {
            svc.getQuestionsByUser(authSvc.getCurrentUser().id).then(function (Questions) {
                    $scope.records = Questions;
            });
        };
        
        $scope.getUsers = function() {
        
                
        };
        
        this.messagesLoad = function (){
                userlist = [];
                if (userlist == "")
                {
                
                svc.getClients().then(function (Clients){
                    
                    for (var i = 0; i < Clients.length; i++)
                    {  
                        var users = {id: Clients[i].id, name: Clients[i].name,type: "Client"}
                        usersarr[i]= users;
                        
                    }
                    userlist = usersarr;
                    
                });
                }
                
                svc.getProviders().then(function (Providers){
                    
                    for (var i = 0; i < Providers.length; i++)
                    {  
                        var users = {id: Providers[i].id, name: Providers[i].name,type: "Provider"}
                        
                        userlist.push(users);
                    }
                    
                });
                
                svcUser.api.one('currentUser').get().then(function(user){
                    if (user.role === 'provider'){
                        svc.getMessagesByProvider(authSvc.getCurrentUser().id).then(function (Questions){
                   
                    data = Questions;
                    
                });
                        
                        
                    }else{
                        svc.getMessagesByProvider(authSvc.getCurrentUser().id).then(function (Questions){
                   
                    data = Questions;
                    
                });
                        
                    }
                });
                
                
            };
        
        
        
        this.answerQuestion = function(record){            
           //Tmp answer
           record.answer=this.answer;
           svc.answerQuestion(record);
            //clean question
            this.answer='';      
        };

        this.messagesSearch = function (){
                    var names = [];
                    $('#MessageInbox').modal('show');
                    for (var i = 0; i <userlist.length; i++)
                    {
                        names.push(userlist[i].name);
                    }
                    
                $(document).ready(function () {
                    $('input.typeahead').typeahead({
                        name: 'accounts',
                        local: names,
                    });
                }); 
                    for (var i = 0; i < data.length; i++)
                           {
                             var n = (i+1).toString();
                             var $Usuario = $("#Usuario"+n);
                             $Usuario.find('label').remove();
                             $Usuario.append("<label>"+data[i].client.name+": "+"</label>");

                             var $Asunto = $("#Asunto"+n);
                             $Asunto.find('label').remove();
                             $Asunto.append("<label>"+data[i].subject+" - " +"</label>");
                             
                             var $Preview = $("#Preview"+n);
                             $Preview.find('label').remove();
                             $Preview.append(" "+"<label>"+data[i].question.substr(0,20)+"</label>");

                             var $Fecha = $("#Date"+n);
                             $Fecha.find('label').remove();
                             $Fecha.append("<label >"+data[i].date+"</label>");
                           } 
                
                
                
                
            };
            
            
        this.messagesSend = function (){
                $('#WriteMessage').modal('show');
            };
            
        this.messagesRead = function (i){
                $('#ReadMessage').modal('show');
                
                var $Title = $("#MessageTitle");
                $Title.find('label').remove();
                $Title.append("<label>"+data[i].subject+"</label>");
                
                var $From = $("#MessageFrom");
                $From.find('label').remove();
                $From.append("<label class='col-sm-11'>"+data[i].client.name+"</label>");
                
                var $Message = $("#MessageRead");
                $Message.find('label').remove();
                $Message.append("<label>"+data[i].question+"</label>");
                
                var $Date = $("#MessageDate");
                $Date.find('label').remove();
                $Date.append("<label>Received "+data[i].date+"</label>");
                
                var $Subject = $("#ReplaySubject");
                $Subject.find('input').remove();
                $Subject.append("<input type='text' class='col-sm-9' value='RE: "+data[i].subject+"'>");
                
            };

        
        
        svcUser.api.one('currentUser').get().then(function(user) {
            console.log(user.role);
            $scope.role=user.role;
            if($scope.role==="provider"){
                console.log('getQuestionsByProvider');
               $scope.getQuestionsByProvider();
                this.user="provider";
            }else{
                console.log('getQuestionsByUser');
                $scope.getQuestionsByUser();
                this.user="client";
            }
            $scope.getUsers();
        });
        
        
        this.message='';
        this.sendMessage = function(){
                console.log("Sendmessage");
                
                newMessage={
                    question: this.message,
                    product:$scope.tmpRecord,
                    provider:$scope.tmpRecord.provider,
                    client:authSvc.getCurrentUser()
                };
                messageSvc.askQuestion(newQuestion);
                //Hide modal
                $('#myModalNorm').modal('hide');
                //clean question
                this.question='';
            };
        
        
        this.sendNewMessage =  function (){
                        svcUser.api.one('currentUser').get().then(function(user){
                            console.log(user.role);
                            
                            console.log("Sendmessage");
                            var UserId, UserType, message, subject, receivertype, providerreceiver, clientreceiver, provider, client;
                            message= $("#message").val();
                            subject= $("#Subject").val();
                            var UserName = $("#UserName").val();
                            
                            for (var i = 0; i <userlist.length; i++)
                            {
                                if (userlist[i].name === UserName){
                                   UserId = userlist[i].id;
                                   UserType = userlist[i].type;
                                   
                                   break;
                                }
                            }
                            
                            
                          
                            if (user.role === 'provider'){
                                if (UserType.toString() === 'provider'){
                                    console.log("send pro recv pro");
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype: UserType.toString(),
                                    providerreceiver: UserId,
                                    provider: authSvc.getCurrentUser(),
                                         
                                    };
                                }
                                else{
                                    console.log("send pro recv cli");
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype: UserType.toString(),
                                    clientreceiver: UserId,
                                    provider: authSvc.getCurrentUser(),
                                          
                            };
                                    }
                                
                            }
                             else
                             {
                                if (UserType.toString() === 'provider'){
                                    console.log("send cli recv pro");
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype:  UserType.toString(),
                                    providerreceiver: UserId,
                                    client: authSvc.getCurrentUser(),       
                            };
                                     providerreceiver= UserId;
                                        clientreceiver= '';
                                        provider='';   
                                        client=authSvc.getCurrentUser();
                                    }
                                
                                else
                                {   
                                    console.log("send cli recv cli");
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype:  UserType.toString(),
                                    clientreceiver: UserId,
                                    client: authSvc.getCurrentUser(),       
                                    };
                            
                                }
                             }   
                            console.log(newMessage);
                            svc.sendMessage(newMessage);
                            //Hide modal
                            $('#WriteMessage').modal('hide');
                            
                        
                    });
                    };
                
                
    }]);


})(window.angular);
