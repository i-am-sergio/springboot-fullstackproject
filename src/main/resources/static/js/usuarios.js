$(document).ready(function () {
  cargarUsuarios();
  $("#usuarios").DataTable();
  actualizarEmailUsuario();
});

async function cargarUsuarios() {
  const request = await fetch("api/usuarios", {
    method: "GET",
    headers: getHeaders(),
  });

  const usuarios = await request.json();

  let listUsuariosHTML = "";

  usuarios.forEach(usuario => {
    let botonEliminar = `<a href="#" onclick="eliminarUsuario(${usuario.id})" class="btn btn-danger btn-circle bt-sm">
                            <i class="fas fa-trash"></i>
                         </a>`;
    let telefonoHTML = usuario.telefono == null ? "-" : usuario.telefono;
    let usuarioHTML = `<tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.nombre} ${usuario.apellido}</td>
                        <td>${usuario.email}</td>
                        <td>${telefonoHTML}</td>
                        <td>${botonEliminar}</td>
                      </tr>`;
    listUsuariosHTML += usuarioHTML;
  });

  document.querySelector("#usuarios tbody").innerHTML = listUsuariosHTML;
}

function getHeaders(){
  return {
    "Accept": "application/json",
    "Content-Type": "application/json",
    "Authorization": localStorage.token
  };
}

async function eliminarUsuario(id){

  if(!confirm("¿Desea eliminar este usuario?")){
    return;
  }

  await fetch(`api/usuario/`+id, {
    method: "DELETE",
    headers: getHeaders(),
  });

  location.reload();
}


function actualizarEmailUsuario(){
  document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
}