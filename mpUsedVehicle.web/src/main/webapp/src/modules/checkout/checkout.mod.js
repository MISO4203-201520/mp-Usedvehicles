(function (ng) {
    var mod = ng.module('checkoutModule', ['ngCrud']);

    mod.constant('checkoutContext', 'orders');
    
    mod.constant('orderItem',{
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
                name:'cartItem',
                displayName: 'Cart Item',
                type:'Reference',
                service: 'cartItemService',
                options:[],
                required: true
            }]});
})(window.angular);
