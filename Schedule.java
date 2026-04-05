public class Schedule {
    
    private boolean[][] availabilityMatrix;

    public Schedule() {
        this.availabilityMatrix = new boolean[7][24];
        
    }

    public void setAvailability(int day, int hour, boolean isFree) {
        if (isValidSlot(day, hour)) {
            availabilityMatrix[day][hour] = isFree;
        }
    }

    public boolean isAvailableForEvent(int dayIndex, int startHour, int endHour) {
        if (dayIndex < 0 || dayIndex > 6) return false;
        
        for (int i = startHour; i < endHour; i++) {
            if (availabilityMatrix[dayIndex][i] == true) {
                return false; 
            }
        }
        return true; 
    }

    private boolean isValidSlot(int day, int hour) {
        return day >= 0 && day < 7 && hour >= 0 && hour < 24;
    }
}
