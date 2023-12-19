window.onload = function (){
    var vue = new Vue({
        el:"#cart_div",
        data:{
            cart:{}
        },
        methods:{
            getCart:function (){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'cartInfo'
                    }
                })
                    .then(function (value){
                        var cart = value.data;
                        vue.cart = cart;
                    })
                    .catch(function (reason){})
            },
            upDateBuyCount:function (buyCount,cartId,bookCount){
                if (buyCount<=0 || buyCount >bookCount){
                    return;
                }
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'upDateBuyCount',
                        cartId:cartId,
                        buyCount:buyCount
                    }
                })
                    .then(function (value){
                        vue.getCart();

                    })
                    .catch(function (reason){})
            }
        },
        mounted:function (){
            this.getCart();
        }
    })
}

//传入购买的数量，购物车的id，图书的库存






