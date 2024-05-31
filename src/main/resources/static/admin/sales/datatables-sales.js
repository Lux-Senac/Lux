$(document).ready(function() {
    $('#dataTable').DataTable({
        destroy: true,
        "processing": true,
        "serverSide": true,
        "searching": true,
        "ajax": {
            "url": "/admin/all-sales/json",
            "type": "GET",
            "headers": {
                "Accept": "application/json",
            },
            "data": function ( d ) {
                d.page = d.start / d.length;
                d.size = d.length;
                d.searchTerm = d.search.value;
            },
        },
        "columns": [
            { "data": "id" },
            { "data": "datavenda" },
            { "data": "precovenda" },
            { "data": "carro.name" },
            { "data": "cliente.nome" },
            { "data": "usuario.username" },
            { "data": null, "render": function(data, type, row) {
                    return '<a href="/admin/edit-sales?id=' + row.id + '">Editar</a> / ' +
                        '<a href="/admin/delete-sales?id=' + row.id + '" onclick="event.preventDefault(); deleteCar(this)">Excluir</a>';
                }
            }
        ],
    });
});

