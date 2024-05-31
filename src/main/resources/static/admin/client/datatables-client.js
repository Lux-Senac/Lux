$(document).ready(function() {
    $('#dataTable').DataTable({
        destroy: true,
        "processing": true,
        "serverSide": true,
        "searching": true,
        "ajax": {
            "url": "/admin/all-clients/json",
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
            { "data": "nome", "render": function(data, type, row) {
                    return row.nome + ' ' + row.sobrenome;
                }
            },
            { "data": "email" },
            { "data": "preferenciacontato" },
            { "data": "pais" },
            { "data": null, "render": function(data, type, row) {
                    return '<a href="/admin/edit-client?id=' + row.id + '">Editar</a> / ' +
                        '<a href="/admin/delete-client?id=' + row.id + '" onclick="event.preventDefault(); deleteCar(this)">Excluir</a>';
                }
            }
        ],

        "createdRow": function( row, data, dataIndex ) {
            // Obter os valores de Preferência de contado
            var contactPreference = data.preferenciacontato;
            var pais = data.pais;

            // Aplicar cores com base nos valores
            if(contactPreference === 'EMAIL') {
                $('td:eq(3)', row).css('color', 'blue');
            } else if(contactPreference === 'TELEFONE') {
                $('td:eq(3)', row).css('color', 'red');
            }else if(contactPreference === 'WHATSAPP') {
                $('td:eq(3)', row).css('color', 'green');
            }

            if(pais === 'BRASIL') {
                $('td:eq(4)', row).css('color', 'green');
            } else if(pais === 'ARGENTINA') {
                $('td:eq(4)', row).css('color', 'blue');
            } else if(pais === 'CHILE') {
                $('td:eq(4)', row).css('color', 'red');
            } else if(pais === 'URUGUAI') {
                $('td:eq(4)', row).css('color', 'yellow');
            } else if(pais === 'PARAGUAI') {
                $('td:eq(4)', row).css('color', 'orange');
            } else if(pais === 'PERU') {
                $('td:eq(4)', row).css('color', 'purple');
            } else if(pais === 'COLÔMBIA') {
                $('td:eq(4)', row).css('color', 'pink');
            } else if(pais === 'VENEZUELA') {
                $('td:eq(4)', row).css('color', 'brown');
            }
        }
    });
});

