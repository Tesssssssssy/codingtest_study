package week1.kim.hash;

public class Ex5_Song implements Comparable<Ex5_Song> {
    int id;
    int play;

    public Ex5_Song(int id, int play) {
        this.id = id;
        this.play = play;
    }

    @Override
    public int compareTo(Ex5_Song other) {
        if (this.play == other.play) {
            return this.id - other.id;
        }
        return other.play - this.play;
    }
}
