function upDateBuyCount(buyCount,cartId,bookCount){

    if (buyCount >=1 && buyCount <= bookCount){
        window.location.href="cart.do?operate=upDateBuyCount&buyCount=" + buyCount + "&cartId=" + cartId;
    }
}

function getAllPrice(){
    return 10;
}
