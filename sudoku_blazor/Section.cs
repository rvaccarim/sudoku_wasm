namespace sudoku_blazor {
    public class Section {
        public Section(int id, int score) {
            ID = id;
            Score = score;
        }

        public int ID { get; }
        public int Score { get; }
    }
}
