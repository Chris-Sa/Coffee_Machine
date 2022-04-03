enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    int levelNum;

    DangerLevel(int LevelNum) {
        this.levelNum = LevelNum;
    }

    public int getLevel() {
        return levelNum;
    }
}
