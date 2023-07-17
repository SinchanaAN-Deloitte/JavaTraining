/*Q1. You are designing a music streaming service that has a Song class. The class has
variables for the song's name, ar:st, album, and duration. Write a Java program that allows
users to create playlists, add songs to their playlists, remove songs from their playlists, and
play their playlists.*/


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Song {
    private String name;
    private String artist;
    private String album;
    private int duration;

    public Song(String name, String artist, String album, int duration) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return name + " - " + artist + " (" + album + ") [" + duration + "s]";
    }
}

class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }


    public void addSong(Song song) {
        // Add songs to the playlist
        songs.add(song);
        System.out.println("Added \"" + song.getName() + "\" to the playlist.");
    }

    public void removeSong(Song song) {
        if (songs.remove(song)) {
            System.out.println("Removed \"" + song.getName() + "\" from the playlist.");
        } else {
            System.out.println("Song not found in the playlist.");
        }
    }

    public void play() {
        // Play the playlist
        System.out.println("Playing playlist: " + name);

        for (Song song : songs) {
            System.out.println("Now playing: " + song);
        }
    }

    public List<Song> getSongs() {
        return songs;
    }
}

public class MusicStreamingService {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Music Streaming Service!");

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Hello, " + username + "!");

        //Playlist
        Playlist playlist = new Playlist("My Playlist");

        while (true) {

            System.out.println("\nMenu:");
            System.out.println("1. Add song to playlist");
            System.out.println("2. Remove song from playlist");
            System.out.println("3. Play playlist");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    //songs to the playlist
                    System.out.print("Enter song name: ");
                    String songName = scanner.nextLine();

                    System.out.print("Enter artist name: ");
                    String artistName = scanner.nextLine();

                    System.out.print("Enter album name: ");
                    String albumName = scanner.nextLine();

                    System.out.print("Enter song duration in seconds: ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Song song = new Song(songName, artistName, albumName, duration);
                    playlist.addSong(song);
                    break;

                case 2:
                    //Removing the song from the playlist
                    System.out.print("Enter song name to remove: ");
                    String songToRemove = scanner.nextLine();

                    boolean songRemoved = false;
                    for (Song s : playlist.getSongs()) {
                        if (s.getName().equalsIgnoreCase(songToRemove)) {
                            playlist.removeSong(s);
                            songRemoved = true;
                            break;
                        }
                    }

                    if (!songRemoved) {
                        System.out.println("Song not found in the playlist.");
                    }
                    break;

                case 3:
                    playlist.play();
                    break;

                case 4:
                    System.out.println("Thank you for using the Music Streaming Service!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
