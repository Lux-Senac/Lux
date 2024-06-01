const idClientInput = document.getElementById('id_client');
const clientDetails = document.getElementById('clientDetails');
const clientsError = document.getElementById('clientsError');

idClientInput.addEventListener('blur', () => {
    const idClient = idClientInput.value;

    if (!idClient) {
        clientsError.textContent = 'Por favor, insira um ID de cliente válido.';
        return;
    }

    fetch(`/admin/all-clients/${idClient}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro: ${response.status}`);
            }
            return response.json();
        })
        .then(client => {
            clientDetails.textContent = 'Nome: ' + client.nome + ' ' + client.sobrenome + ' Email: ' + client.email;
            clientsError.textContent = '';
        })
        .catch(error => {
            clientsError.textContent = 'Erro ao buscar o cliente: ' + error.message;
        });
});


const idCarInput = document.getElementById('id_car');
const carDetails = document.getElementById('carDetails');
const carError = document.getElementById('carError');

idCarInput.addEventListener('blur', () => {
    const idCar = idCarInput.value;

    if (!idCar) {
        carError.textContent = 'Por favor, insira um ID de carro válido.';
        return;
    }

    fetch(`/admin/carros/${idCar}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro: ${response.status}`);
            }
            return response.json();
        })
        .then(car => {
            carDetails.textContent = 'Carro: ' + car.name + ' Preço: ' + car.price;
            carError.textContent = '';
        })
        .catch(error => {
            carError.textContent = 'Erro ao buscar o carro: ' + error.message;
        });
});