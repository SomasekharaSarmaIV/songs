
package com.example.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.song.service.SongH2Service;
import com.example.song.model.Song;

// Write your code here

@RestController
public class SongController {
  @Autowired
  private SongH2Service ss;

  @GetMapping("/songs")
  public ArrayList<Song> getAllSongs() {
    return ss.getAllSongs();
  }

  @PostMapping("/songs")
  public Song addSong(@RequestBody Song song) {
    return ss.addSong(song);
  }

  @GetMapping("/songs/{songId}")
  public Song getSongById(@PathVariable("songId") int songId) {
    return ss.getSongById(songId);
  }

  @PutMapping("/songs/{songId}")
  public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song) {
    return ss.updateSong(songId, song);
  }
  @DeleteMapping("/songs/{songId}")
  public void deleteSong(@PathVariable("songId") int songId){
    ss.deleteSong(songId);
  }
}
