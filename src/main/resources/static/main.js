function addWishlist() {
    return window.location.href = '/wishlist/addWishlist';
}

function addWish(wishListId) {
    return window.location.href = `/wishlist/${wishListId}/addWish`;
}

function editWish(wishListId, wishId) {
    return window.location.href = `/wishlist/${wishListId}/editWish/${wishId}`;
}