package soundsystem;

import java.util.List;

public class BlankDisc implements CompactDisc {
    private String title;
    private String artist;
    private List<String> tracks;

    public BlankDisc() {}

    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public BlankDisc(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
        if (tracks != null && tracks.size() > 0) {
            for (String track : tracks) {
                System.out.println("-Track: " + track);
            }
        }
    }

    public void playTrack(int trackNumber) {
        System.out.println("----------------------------------------------");
        System.out.println("Playing tracker: " + tracks.get(trackNumber -1));
        System.out.println("----------------------------------------------");
    }
}
