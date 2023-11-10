$(document).ready(function() {
			console.log("asd");
			filtrar();
});

function filtrar() {
    var fecha = document.getElementById('fecha').value;
    var sala = document.getElementById('sala').value;

    var url = "/horarios/listarHorarios" + "?fecha=" + fecha + "&sala=" + sala;
    if (fecha !== '') {
        fetch(url, {
           method: 'GET'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('La solicitud no se completó correctamente');
            }
            return response.text(); // Convierte la respuesta en texto
        })
        .then(data => {
            // Manipula el elemento 'mostrar' para mostrar la respuesta
            document.getElementById('mostrar').innerHTML = data;
        })
        .catch(error => {
            console.error(error);
        });
    }
}