const apiUrl = '/api/articulos';

async function loadArticles() {
    const res = await fetch(apiUrl);
    const articles = await res.json();
    const container = document.getElementById('article-list');
    container.innerHTML = '';
    articles.forEach(a => {
        const div = document.createElement('div');
        div.innerHTML = `
            <strong>${a.nombre}</strong> — ${a.descripcion}
            <br/>Precio: ${a.precio} | Stock: ${a.stock} | Activo: ${a.activo}
            <br/>
            <button onclick="showEditForm(${a.id})">Editar</button>
            <button onclick="deleteArticle(${a.id})">Eliminar</button>
            <div id="edit-form-${a.id}" style="margin:10px 0;"></div>
            <hr/>
        `;
        container.appendChild(div);
    });
}

document.getElementById('create-article-form').addEventListener('submit', async e => {
    e.preventDefault();
    const body = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        precio: parseFloat(document.getElementById('precio').value),
        stock: parseInt(document.getElementById('stock').value),
        activo: document.getElementById('activo').checked
    };
    await fetch(apiUrl, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(body)
    });
    e.target.reset();
    loadArticles();
});
async function deleteArticle(id) {
    if (!confirm('¿Eliminar este artículo?')) return;
    await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
    loadArticles();
}

function showEditForm(id) {
    const cont = document.getElementById(`edit-form-${id}`);
    cont.innerHTML = `
        <h4>Editar artículo</h4>
        <input type="text" id="nombre-${id}" placeholder="Nombre" />
        <input type="text" id="descripcion-${id}" placeholder="Descripción" />
        <input type="number" id="precio-${id}" step="0.01" placeholder="Precio" />
        <input type="number" id="stock-${id}" placeholder="Stock" />
        <label>Activo: <input type="checkbox" id="activo-${id}" /></label>
        <button onclick="updateArticle(${id})">Guardar</button>
    `;
    
    fetch(`${apiUrl}/${id}`)
        .then(r => r.json())
        .then(a => {
            document.getElementById(`nombre-${id}`).value = a.nombre;
            document.getElementById(`descripcion-${id}`).value = a.descripcion;
            document.getElementById(`precio-${id}`).value = a.precio;
            document.getElementById(`stock-${id}`).value = a.stock;
            document.getElementById(`activo-${id}`).checked = a.activo;
        });
}

async function updateArticle(id) {
    const body = {
        nombre: document.getElementById(`nombre-${id}`).value,
        descripcion: document.getElementById(`descripcion-${id}`).value,
        precio: parseFloat(document.getElementById(`precio-${id}`).value),
        stock: parseInt(document.getElementById(`stock-${id}`).value),
        activo: document.getElementById(`activo-${id}`).checked
    };
    await fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(body)
    });
    loadArticles();
}

loadArticles();