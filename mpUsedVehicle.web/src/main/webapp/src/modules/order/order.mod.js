(function (ng) {
    var mod = ng.module('orderModule', ['ngCrud']);

    mod.constant('orderContext', 'orders');
    
    mod.constant('orderModel',{
        fields:[{
                name:'amount',
                displayName: 'Amount',
                type:'Integer',
                required: 'true'
            }, {
                name:'amountWithTaxes',
                displayName: 'Amount With Taxes',
                type:'Integer',
                required: 'true'
            }, {
                name:'orderStatus',
                displayName: 'Status',
                type:'String',
                required: 'true'
            }, {
                name:'cartItem',
                displayName: 'Cart Item',
                type:'Reference',
                service: 'cartItemService',
                options:[],
                required: true
            }]});
})(window.angular);
