function addWishlist() {
    return window.location.href = '/wishlist/addWishlist';
}

function addWish(wishListId) {
    return window.location.href = `/wishlist/${wishListId}/addWish`;
}

function editWish(wishListId, wishId) {
    return window.location.href = `/wishlist/${wishListId}/editWish/${wishId}`;
}

function closeForm(wishListId) {
    return window.location.href = `/wishlist/${wishListId}`;
}

function closeWishlist() {
    return window.location.href = `/wishlist`;
}
