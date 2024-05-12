$(document).ready(function() {
  $('#dataTable').DataTable({
    // ... suas outras configurações do DataTables ...

    "createdRow": function( row, data, dataIndex ) {
      // Obter os valores do tipo e status da reserva
      var reservationType = data[4]; // Índice da coluna do tipo de reserva
      var reservationStatus = data[5]; // Índice da coluna do status da reserva

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