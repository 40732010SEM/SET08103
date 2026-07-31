package com.napier.sem;

public class App
{
    public static void main(String[] args)
    {
        Database db = new Database();
        db.connect("localhost:3306", 3000);

        System.out.println("\n=== 1. All Countries by Population ===\n");
        db.displayCountries(db.getAllCountriesByPopulation());

        System.out.println("\n=== 2. All Cities by Population ===\n");
        db.displayCities(db.getAllCitiesByPopulation());

        System.out.println("\n=== 3. All Capital Cities by Population ===\n");
        db.displayCapitalCities(db.getAllCapitalCitiesByPopulation());

        System.out.println("\n=== 4. Top 10 Cities by Population ===\n");
        db.displayCities(db.getTopNCities(10));

        System.out.println("\n=== 5. Population in/out of cities per Country ===\n");
        db.displayPopulationReports(db.getPopulationByCountry());

        System.out.println("\n=== Language Speakers (Chinese, English, Spanish) ===\n");
        db.displayLanguages(db.getLanguageSpeakers());

        System.out.println("\n=== World Population ===");
        System.out.println("World Population: " + String.format("%,d", db.getWorldPopulation()));

        db.disconnect();
    }
}