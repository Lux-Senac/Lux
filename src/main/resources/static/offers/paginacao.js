document.addEventListener('DOMContentLoaded', (event) => {
    const pagination = document.querySelector('.pagination');
    let currentPage = parseInt(pagination.dataset.currentPage);
    const pageItems = pagination.querySelectorAll('.page-item');
    const maxVisiblePages = 5;

    function updatePagination() {
        pageItems.forEach((item, index) => {
            if (index === 0 || index === pageItems.length - 1) return;
            const pageNumber = index;
            if (pageNumber >= currentPage && pageNumber < currentPage + maxVisiblePages) {
                item.style.display = 'block';
            } else {
                item.style.display = 'none';
            }
        });
    }

    pageItems.forEach((item, index) => {
        if (index === 0 || index === pageItems.length - 1) return;
        item.addEventListener('click', () => {
            currentPage = index;
            updatePagination();
        });
    });

    updatePagination();
});
