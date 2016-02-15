package ir.assignments.helpers;

import java.io.*;
import java.util.*;

/**
 * Created by Chris on 2/1/16.
 */

public class LogChecker {
    private static void getLongestText() {
        String logFolder = "logs";
        String fileName = "";
        File dir = new File(logFolder);
        File[] directoryListing = dir.listFiles();
        float numFiles =  directoryListing.length;
        Integer fileCount = 0;
        Integer maxLength = 0;
        String maxURL = "";
        if (directoryListing != null) {
            for (File logFile : directoryListing) {
                fileName = logFile.toString();
                if (logFile.isFile() && fileName.contains("log.txt") && !fileName.contains(".DS_Store")) {
                    try {
                        Scanner sc = new Scanner(logFile);
                        while (sc.hasNext()) {
                            String url = sc.nextLine().split(" ")[1];
                            Integer textLength = Integer.parseInt(sc.nextLine().split(":")[1].trim());
                            String urls = sc.nextLine();
                            if (textLength > maxLength && !url.contains(".csv")) {
                                maxLength = textLength;
                                maxURL = url;
                            }

                        }
                    } catch (FileNotFoundException e) {
                        System.err.println(e.toString());
                    }
                }
            }
        }

        System.out.println(maxURL + " " + maxLength);
    }

    public static List<String> getURLsFromLogs() {
        List<String> urls = new ArrayList<String>();
        String logFolder = "logs";
        String fileName = "";
        File dir = new File(logFolder);
        File[] directoryListing = dir.listFiles();
        Integer count = 0;
        if (directoryListing != null) {
            for (File logFile : directoryListing) {
                fileName = logFile.toString();
                if (logFile.isFile() && fileName.contains("log.txt") && !fileName.contains(".DS_Store")) {
                    try {
                        Scanner sc = new Scanner(logFile);
                        while (sc.hasNext()) {
                            String url = sc.nextLine().split(" ")[1];
                            String textLength = sc.nextLine();
                            String numURLs = sc.nextLine();
                            urls.add(url);
                            count += 1;
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println(e.toString());
                    }
                }
            }
        }
        return urls;
    }

    public static HashMap<Integer, String> getVisitedURLs() {
        HashMap<Integer, String> hashCodeMap = new HashMap<Integer, String>();
        String logFolder = "logs";
        String fileName = "";
        File dir = new File(logFolder);
        File[] directoryListing = dir.listFiles();
        Integer count = 0;
        if (directoryListing != null) {
            for (File logFile : directoryListing) {
                fileName = logFile.toString();
                if (logFile.isFile() && fileName.contains("log.txt") && !fileName.contains(".DS_Store")) {
                    try {
                        Scanner sc = new Scanner(logFile);
                        while (sc.hasNext()) {
                            List<String> logLine = Arrays.asList(sc.nextLine().split(": "));
                            String url = logLine.get(1).trim();
                            Integer hashCode = Integer.parseInt(logLine.get(2).trim());
                            hashCodeMap.put(hashCode, url);
                            count += 1;
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println(e.toString());
                    }
                }
            }
        }
        return hashCodeMap;
    }

    public static void getSubdomains(List<String> urls) {
        String subdomainFileName = "subdomainsTemp.txt";
        String subDomain = "";
        for (String url : urls) {
            try (BufferedWriter subDomainWriter = new BufferedWriter(new FileWriter(subdomainFileName, true))) {
                subDomain = url.split(".ics.uci.edu")[0];
                System.out.println(subDomain);
                if (subDomain.length() < 30) {
                    subDomainWriter.write(subDomain);
                    subDomainWriter.newLine();
                    subDomainWriter.flush();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
    public static void main(String[] args) {
        getVisitedURLs();
    }
}