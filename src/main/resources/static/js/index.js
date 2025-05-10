const apiUrl = '/api/articulos';

async function loadArticles() {
    const res = await fetch(apiUrl);
    const articles = await res.json();
    const container = document.getElementById('article-list');
    if (!articles.length) {
        container.innerHTML = '<p>No hay artículos disponibles.</p>';
        return;
    }
    container.innerHTML = '';
    articles.forEach(a => {
        const div = document.createElement('div');
        div.style.border = '1px solid #ccc';
        div.style.padding = '1rem';
        div.style.marginBottom = '1rem';
        div.innerHTML = `
            <h3>${a.nombre}</h3>
            <p>${a.descripcion}</p>
            <p>Precio: ${a.precio.toFixed(2)} € | Stock: ${a.stock} | Activo: ${a.activo ? 'Sí' : 'No'}</p>
        `;
        container.appendChild(div);
    });
}

document.addEventListener('DOMContentLoaded', loadArticles);