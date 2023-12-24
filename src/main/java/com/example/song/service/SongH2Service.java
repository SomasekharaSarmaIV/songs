
package com.example.song.service;

import com.example.song.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.model.Song;
import com.example.song.model.SongRowMapper;

@Service
public class SongH2Service implements SongRepository {
  @Autowired
  private JdbcTemplate db;

  @Override
  public ArrayList<Song> getAllSongs() {
    return (ArrayList<Song>) db.query("select * from PLAYLIST", new SongRowMapper());

  }

  @Override
  public Song addSong(Song song) {
    db.update("insert into PLAYLIST(songName,lyricist,singer,musicDirector) values(?,?,?,?)", song.getSongName(),
        song.getLyricist(), song.getSinger(), song.getMusicDirector());
    return db.queryForObject("select * from PLAYLIST where songName=? and lyricist=? ", new SongRowMapper(),
        song.getSongName(), song.getLyricist());

  }

  @Override
  public Song getSongById(int songId) {
    try {
      return db.queryForObject("select * from PLAYLIST where songId=?", new SongRowMapper(), songId);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public Song updateSong(int songId, Song song) {
    if (song.getSongName() != null) {
      db.update("update PLAYLIST set songName=? where songId=?", song.getSongName(), songId);
    }
    if (song.getLyricist() != null) {
      db.update("update PLAYLIST set lyricist=? where songId=?", song.getLyricist(), songId);
    }
    if (song.getSinger() != null) {
      db.update("update PLAYLIST set singer=? where songId=?", song.getSinger(), songId);
    }
    if (song.getMusicDirector() != null) {
      db.update("update PLAYLIST set musicDirector=? where songId=?", song.getMusicDirector(), songId);
    }
    return getSongById(songId);
  }

  @Override
  public void deleteSong(int songId) {
    db.update("delete from PLAYLIST where songid=?", songId);
  }

}

