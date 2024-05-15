function validateEmail(email) {
 var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
 if (reg.test(email)){
 return true; }
 else{
 return false;
 }
}

let senha = document.getElementById('password');
let senhaC = document.getElementById('password2');

function validarSenha() {
  if (password.value != password2.value) {
    password2.setCustomValidity("Senhas diferentes!");
    password2.reportValidity();
    return false;
  } else {
    senhaC.setCustomValidity("");
    return true;
  }
}