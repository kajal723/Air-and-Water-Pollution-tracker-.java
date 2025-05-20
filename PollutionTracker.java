import java.util.*;

public class PollutionTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<PollutionData> dataList = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add Data\n2. View Data\n3. Exit");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.print("Enter Location: ");
                String loc = sc.next();
                System.out.print("Enter Type (Air/Water): ");
                String type = sc.next();
                System.out.print("Enter Pollution Level: ");
                double level = sc.nextDouble();

                PollutionData data = new PollutionData(loc, type, level);
                dataList.add(data);
                System.out.println("Status: " + PollutionUtils.assessPollution(type, level));
            } else if (choice == 2) {
                for (PollutionData d : dataList) {
                    System.out.println(d.getLocation() + " - " + d.getType() + ": " +
                        d.getLevel() + " (" + PollutionUtils.assessPollution(d.getType(), d.getLevel()) + ")");
                }
            } else {
                break;
            }
        }
        sc.close();
    }
}
 class PollutionUtils {
    public static String assessPollution(String type, double level) {
        if (type.equalsIgnoreCase("Air")) {
            if (level <= 50) return "Good";
            else if (level <= 100) return "Moderate";
            else return "Hazardous";
        } else if (type.equalsIgnoreCase("Water")) {
            if (level <= 1.0) return "Safe";
            else if (level <= 3.0) return "Moderate";
            else  
            return "Contaminated";
        }
        return "Unknown";
    }
}
 class PollutionData {
    private String location;
    private String type; // "Air" or "Water"
    private double level;

    public PollutionData(String location, String type, double level) {
        this.location = location;
        this.type = type;
        this.level = level;
    }

    public String getLocation() { return location; }
    public String getType() { return type; }
    public double getLevel() { return level; }
}
