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

function renderMainPage() {
    return window.location.href = `/wishlist`;
}

function renderWillGivePage() {
    return window.location.href = `/wishlist/willGive`;
}

function shareWishList(wishListId) {
    const shareWindow = document.getElementById('share-window');
    const shareLink = document.getElementById('share-link');
    if (shareWindow.style.visibility === 'hidden' || shareWindow.style.visibility === ''){
        shareWindow.style.visibility = 'visible';
        shareWindow.style.height = 'fit-content';
        shareLink.innerText = `http://localhost:8080/wishlist/present/${wishListId}`;
    } else {
        shareWindow.style.visibility = 'hidden';
        shareWindow.style.height = '0';
    }
}

