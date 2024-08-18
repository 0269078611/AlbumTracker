function openCreateModal() {
    document.getElementById('createModal').style.display = 'block';
}

function closeCreateModal() {
    document.getElementById('createModal').style.display = 'none';
}

function openUpdateModal(id, name) {
    var updateModal = document.getElementById('editModal');

    let GenreId = id.toString();
    document.getElementById('editForm').action = "/admin/genres/edit/" + GenreId;
    document.getElementById('updatedGenreName').value = name;
    updateModal.style.display = 'block';
}

function closeUpdateModal() {
    document.getElementById('updateModal').style.display = 'none';
}

function openDeleteModal(id) {
    var deleteModal = document.getElementById('deleteModal');
    document.getElementById('deleteForm').action = "/admin/genres/delete/" + id;
    document.getElementById('deleteGenreId').value = id;
    deleteModal.style.display = 'block';
}

function closeDeleteModal() {
    document.getElementById('deleteModal').style.display = 'none';
}

window.onclick = function(event) {
    if (event.target === document.getElementById('createModal')) {
        closeCreateModal();
    }
    if (event.target === document.getElementById('updateModal')) {
        closeUpdateModal();
    }
    if (event.target === document.getElementById('deleteModal')) {
        closeDeleteModal();
    }
}