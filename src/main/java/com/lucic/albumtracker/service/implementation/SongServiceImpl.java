package com.lucic.albumtracker.service.implementation;

import com.lucic.albumtracker.dto.SongDTO;
import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.entity.SongEntity;
import com.lucic.albumtracker.exception.NotFoundException;
import com.lucic.albumtracker.mapper.SongMapper;
import com.lucic.albumtracker.repository.AlbumRepository;
import com.lucic.albumtracker.repository.ArtistRepository;
import com.lucic.albumtracker.repository.SongRepository;
import com.lucic.albumtracker.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    private final SongMapper songMapper;


    @Override
    public List<SongDTO> getSongsByAlbumId(UUID albumId) {
        List<SongEntity> songs = songRepository.findByAlbumId(albumId);
        return songs.stream()
                .map(songMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SongDTO getSongById(UUID songId) {
        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() -> new NotFoundException("Song not found"));
        return songMapper.toDto(song);
    }

    @Override
    public SongDTO createSong(UUID albumId, SongDTO songDTO) {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("Album not found"));

        Set<UUID> artistIds = songDTO.getArtistIds();
        Set<ArtistEntity> artists = new HashSet<>(artistRepository.findAllById(artistIds));


        SongEntity songEntity = songMapper.toEntity(songDTO);
        songEntity.setAlbum(album);
        songEntity.setArtists(artists);

        songEntity = songRepository.save(songEntity);
        return songMapper.toDto(songEntity);
    }

    @Override
    public SongDTO updateSong(UUID songId, SongDTO songDTO) {
        SongEntity existingSong = songRepository.findById(songId)
                .orElseThrow(() -> new NotFoundException("Song not found"));

        Set<UUID> artistIds = songDTO.getArtistIds();
        Set<ArtistEntity> artists = new HashSet<>(artistRepository.findAllById(artistIds));

        existingSong.setTitle(songDTO.getTitle());
        existingSong.setTrackNumber(songDTO.getTrackNumber());
        existingSong.setDuration(songDTO.getDuration());
        existingSong.setArtists(artists);

        if (songDTO.getAlbumId() != null) {
            AlbumEntity album = albumRepository.findById(songDTO.getAlbumId())
                    .orElseThrow(() -> new NotFoundException("Album not found"));
            existingSong.setAlbum(album);
        }

        existingSong = songRepository.save(existingSong);

        return songMapper.toDto(existingSong);
    }

    @Override
    public void deleteSong(UUID songId) {
        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() -> new NotFoundException("Song not found"));
        songRepository.delete(song);
    }
}

