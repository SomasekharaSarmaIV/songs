// Write your code here
package com.example.song.repository;

import java.util.*;
import com.example.song.model.Song;

public interface SongRepository {
      public ArrayList<Song> getAllSongs();

      public Song addSong(Song song);

      public Song getSongById(int songId);

      public Song updateSong(int songId, Song song);

      public void deleteSong(int songId);

}
