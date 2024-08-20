function openAddModal() {
    document.getElementById('addSongModal').style.display = 'block';
}

function closeAddModal() {
    document.getElementById('addSongModal').style.display = 'none';
}

function openEditModal(songId, title, trackNumber, duration, albumId, selectedArtists) {
    document.getElementById('editSongModal').style.display = 'block';
    document.getElementById('editTitle').value = title;
    document.getElementById('editTrackNumber').value = trackNumber;
    document.getElementById('editDuration').value = duration;
    document.getElementById('editSongForm').action = "/admin/albums" + albumId + "/songs/edit/" + songId;

    let artistSelect = document.getElementById('editArtists');
    for (let i = 0; i < artistSelect.options.length; i++) {
        artistSelect.options[i].selected = false;
    }

    selectedArtists.forEach(artistId => {
        for (let i = 0; i < artistSelect.options.length; i++) {
            if (artistSelect.options[i].value == artistId) {
                artistSelect.options[i].selected = true;
            }
        }
    });
}

function closeEditModal() {
    document.getElementById('editSongModal').style.display = 'none';
}

function openDeleteModal(albumId, songId) {
    document.getElementById('deleteSongModal').style.display = 'block';
    document.getElementById('deleteSongForm').action = "/admin/albums/" + albumId + "/songs/delete/" + songId;
}

function closeDeleteModal() {
    document.getElementById('deleteSongModal').style.display = 'none';
}