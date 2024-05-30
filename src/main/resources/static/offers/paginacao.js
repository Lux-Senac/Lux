document.addEventListener('DOMContentLoaded', (event) => {
    const pagination = document.querySelector('.pagination');
    const pageItems = pagination.querySelectorAll('.page-item');
    const maxVisiblePages = 5;
    let currentPage = 1;

    function updatePagination() {
        pageItems.forEach((item, index) => {
            if (index === 0 || index === pageItems.length - 1) return; // Ignora os botões 'Previous' e 'Next'
            const pageNumber = index;
            if (pageNumber >= currentPage && pageNumber < currentPage + maxVisiblePages) {
                item.style.display = 'block';
            } else {
                item.style.display = 'none';
            }
        });
    }

    // Adiciona evento de clique para cada item da paginação
    pageItems.forEach((item, index) => {
        if (index === 0 || index === pageItems.length - 1) return; // Ignora os botões 'Previous' e 'Next'
        item.addEventListener('click', () => {
            currentPage = index;
            updatePagination();
        });
    });

    updatePagination();
});