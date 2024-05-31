$(document).ready(function() {
    $('#dataTable').DataTable({
        destroy: true,
        "processing": true,
        "serverSide": true,
        "searching": true,
        "ajax": {
            "url": "/admin/all-users/json",
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
            { "data": "username" },
            { "data": "email" },
            { "data": "urlavatar", "render": function(data, type, row) {
                    return '<img src="' + data + '" width="50px" height="50px">';
                }
            },
            { "data": "tipo" },
            { "data": "cliente", "render": function(data, type, row) {
                    return data ? data.nome + ' ' + data.sobrenome : 'Sem cliente associado';
                }
            },
            { "data": null, "render": function(data, type, row) {
                    return '<a href="/admin/edit-user?userid=' + row.id + '">Editar</a> / ' +
                        '<a href="/admin/delete-user?id=' + row.id + '" onclick="event.preventDefault(); deleteCar(this)">Excluir</a>';
                }
            }
        ]

    });
});

