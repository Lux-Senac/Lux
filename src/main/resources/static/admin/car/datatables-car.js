$(document).ready(function() {
    $('#dataTable').DataTable({
        destroy: true,
        "processing": true,
        "serverSide": true,
        "searching": true,
        "ajax": {
            "url": "/admin/all-cars/json",
            "type": "GET",
            "data": function ( d ) {
                d.page = d.start / d.length;
                d.size = d.length;
                d.searchTerm = d.search.value;
            },
        },
        "columns": [
            { "data": "id" },
            { "data": "name" },
            { "data": "price" },
            { "data": "image", "render": function(data, type, row) {
                    return '<img src="' + data + '" height="50">';
                }
            },
            { "data": "page" },
            { "data": "carPage", "render": function(data, type, row) {
                    return '<a href="/carros/detalhes-carro?id=' + row.id + '">Link</a>';
                }
            },
            { "data": null, "render": function(data, type, row) {
                    return '<a href="/admin/edit-car?id=' + row.id + '">Editar</a> / ' +
                        '<a href="/admin/delete-car?id=' + row.id + '" onclick="event.preventDefault(); deleteCar(this)">Excluir</a>';
                }
            }
        ]
    });
});

