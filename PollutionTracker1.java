import java.util.*;

public class PollutionTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<PollutionData> dataList = new ArrayList<>();

        System.out.println("=== Pollution Tracker System ===");

        while (true) {
            try {
                // Event Handling - User Menu
                System.out.println("\n1. Add Data\n2. View Data\n3. Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        // Add new data with validation
                        System.out.print("Enter Location: ");
                        String loc = sc.nextLine().trim();

                        System.out.print("Enter Type (Air/Water): ");
                        String type = sc.nextLine().trim();
                        if (!type.equalsIgnoreCase("Air") && !type.equalsIgnoreCase("Water")) {
                            System.out.println("❌ Invalid type! Please enter 'Air' or 'Water'.");
                            break;
                        }

                        System.out.print("Enter Pollution Level: ");
                        double level = Double.parseDouble(sc.nextLine().trim());
                        if (level < 0) {
                            System.out.println("❌ Pollution level cannot be negative.");
                            break;
                        }

                        PollutionData data = new PollutionData(loc, type, level);
                        dataList.add(data);

                        System.out.println("✅ Data Recorded Successfully.");
                        System.out.println("📊 Status: " + PollutionUtils.assessPollution(type, level));
                        break;

                    case 2:
                        // View all recorded data
                        if (dataList.isEmpty()) {
                            System.out.println("ℹ️ No data available. Please add entries first.");
                        } else {
                            System.out.println("\n=== Pollution Records ===");
                            for (PollutionData d : dataList) {
                                System.out.printf("📍 %s - %s: %.2f (%s)%n",
                                    d.getLocation(), d.getType(), d.getLevel(),
                                    PollutionUtils.assessPollution(d.getType(), d.getLevel()));
                            }
                        }
                        break;

                    case 3:
                        // Exit the application
                        System.out.println("Thank you for using Pollution Tracker. Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("❌ Invalid choice. Please enter 1, 2, or 3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter numbers for choices and levels.");
            } catch (Exception e) {
                System.out.println("❌ An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}

/**
 * PollutionUtils - Utility class to assess pollution levels based on defined thresholds.
 */
class PollutionUtils {
    public static String assessPollution(String type, double level) {
        if (type.equalsIgnoreCase("Air")) {
            if (level <= 50) return "Good";
            else if (level <= 100) return "Moderate";
            else return "Hazardous";
        } else if (type.equalsIgnoreCase("Water")) {
            if (level <= 1.0) return "Safe";
            else if (level <= 3.0) return "Moderate";
            else return "Contaminated";
        }
        return "Unknown";
    }
}

/**
 * PollutionData - Model class to store pollution records.
 */
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
