function openAddModal() {
    document.getElementById('addSongModal').style.display = 'block';
}

function closeAddModal() {
    document.getElementById('addSongModal').style.display = 'none';
}

// Open the Edit Song Modal and populate it with the song data
function openEditModal(songId, title, trackNumber, duration) {
    document.getElementById('editSongModal').style.display = 'block';
    document.getElementById('editTitle').value = title;
    document.getElementById('editTrackNumber').value = trackNumber;
    document.getElementById('editDuration').value = duration;
    document.getElementById('editSongForm').action = '/admin/albums/' + albumId + '/songs/' + songId + '/edit';
}

function closeEditModal() {
    document.getElementById('editSongModal').style.display = 'none';
}

function openDeleteModal(songId) {
    document.getElementById('deleteSongModal').style.display = 'block';
    document.getElementById('deleteSongForm').action = '/admin/albums/' + albumId + '/songs/' + songId + '/delete';
}

function closeDeleteModal() {
    document.getElementById('deleteSongModal').style.display = 'none';
}