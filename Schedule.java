public class Schedule {
    // 7 days a week, 24 hours a day (or adjust to specific time slots)
    private boolean[][] availabilityMatrix;

    public Schedule() {
        this.availabilityMatrix = new boolean[7][24];
        // By default, initialize all slots to false (busy) or true (free)
    }

    public void setAvailability(int day, int hour, boolean isFree) {
        if (isValidSlot(day, hour)) {
            availabilityMatrix[day][hour] = isFree;
        }
    }

    public boolean checkAvailability(int day, int hour) {
        if (isValidSlot(day, hour)) {
            return availabilityMatrix[day][hour];
        }
        return false;
    }

    private boolean isValidSlot(int day, int hour) {
        return day >= 0 && day < 7 && hour >= 0 && hour < 24;
    }
}