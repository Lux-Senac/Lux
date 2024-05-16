function validarFormulario() {
    var username = document.forms[0]["username"].value;
    var email = document.forms[0]["email"].value;
    var password = document.forms[0]["password"].value;
    var password2 = document.forms[0]["password2"].value; 
    var tipo = document.forms[0]["tipo"].value;
    var date = document.forms[0]["datavenda"].value;
    var venda = document.forms[0]["precovenda"].value;
    var name = document.forms[0]["nome"].value;
    var sobrenome = document.forms[0]["sobrenome"].value;
    var reserva = document.forms[0]["tiporeservareserva"].value;
    var statusreserva = document.forms[0]["statusreserva"].value;
    var datareserva = document.forms[0]["datareserva"].value;
    var preferenciacontato = document.forms[0]["preferenciacontato"].value;
    var contato = document.forms[0]["contato"].value;
    var cep = document.forms[0]["cep"].value;
    var pais = document.forms[0]["pais"].value;
    var nameCar = document.forms[0]["nome"].value;
    var motor = document.forms[0]["motor"].value;
    var cil = document.forms[0]["cil"].value;
    var acel = document.forms[0]["acel"].value;
    var hp = document.forms[0]["hp"].value;
    var torque = document.forms[0]["torque"].value;
    var velmax = document.forms[0]["velmax"].value;
    var doors = document.forms[0]["doors"].value;
    var seats = document.forms[0]["seats"].value;
    var val = document.forms[0]["val"].value;
    var price = document.forms[0]["price"].value;
    var title = document.forms[0]["title"].value;
    var page = document.forms[0]["page"].value;



    var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (username == "" || email == "" || password == "" || password2 == "" || tipo == "" || date == "" || venda == "" || name == "" || reserva == "" || statusreserva == "" || datareserva == ""
        || sobrenome == "" || preferenciacontato == "" || contato == "" || cep == "" || pais == "" || nameCar == "" || motor == "" || cil == "" || acel == "" || hp == "" || torque == "" || 
        velmax == "" || doors == "" || seats == "" || val == "" || price == "" || title == "" || page == "") {
        alert("Todos os campos devem ser preenchidos");
        return false;
    }

    if (password != password2) {
        alert("As senhas não correspondem");
        return false;
    }

    if (!regex.test(email)) {
        alert("Por favor, insira um endereço de e-mail válido.");
        return false;
    }

    return true;
}