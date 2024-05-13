$(document).ready(function() {
  $('#dataTable').DataTable({
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

      // Obter os valores de Preferência de contado
      var contactPreference = data[3]; // Índice da coluna de preferência de contato
      var pais = data[4]; // Índice da coluna de país

      // Aplicar cores com base nos valores
      if(contactPreference === 'EMAIL') {
        $('td:eq(3)', row).css('color', 'blue');
      } else if(contactPreference === 'TELEFONE') {
        $('td:eq(3)', row).css('color', 'red');
      }else if(contactPreference === 'WHATSAPP') {
        $('td:eq(3)', row).css('color', 'green');
      }

      // Aplicar cores com base nos valores
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