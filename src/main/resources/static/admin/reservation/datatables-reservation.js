$(document).ready(function() {
    $('#dataTable').DataTable({
        destroy: true,
        "processing": true,
        "serverSide": true,
        "searching": true,
        "ajax": {
            "url": "/admin/all-reservation/json",
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
            { "data": 'datareserva' },
            { "data": "client", "render": function(data, type, row){
                    return row.client.nome + ' ' + row.client.sobrenome;
                }
            },
            { "data": "car", "render": function(data, type, row) {
                    return row.car.name;
                }
            },
            { "data": "tiporeserva" },
            { "data": "statusreserva" },
            { "data": null, "render": function(data, type, row) {
                    return '<a href="/admin/edit-reservation?id=' + row.id + '">Editar</a> / ' +
                        '<a href="/admin/delete-reservation?id=' + row.id + '" onclick="event.preventDefault(); deleteCar(this)">Excluir</a>';
                }
            }
        ],

        "createdRow": function( row, data, dataIndex ) {
            // Obter os valores do tipo e status da reserva
            var reservationType = data.tiporeserva;
            var reservationStatus = data.statusreserva;

            console.log(reservationType, reservationStatus); // Adicionado para depuração

            // Aplicar cores com base nos valores
            if (reservationType === 'TESTDRIVE') {
                $('td:eq(4)', row).css('color', 'blue');
            } else if (reservationType === 'RESERVA') {
                $('td:eq(4)', row).css('color', 'orange');
            }

            if (reservationStatus === 'ESPERA') {
                $('td:eq(5)', row).css('color', 'yellow');
            } else if (reservationStatus === 'APROVADO') {
                $('td:eq(5)', row).css('color', 'green');
            } else if (reservationStatus === 'REJEITADO') {
                $('td:eq(5)', row).css('color', 'red');
            }
        }

    });
});

