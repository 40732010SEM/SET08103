package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class Database
{
    private Connection connection;

    public void connect(String location, int delay)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        for (int i = 0; i < 10; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                Thread.sleep(delay);
                connection = DriverManager.getConnection(
                    "jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                    "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect attempt " + i + ": " + sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted");
            }
        }
    }

    public void disconnect()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Error closing connection");
            }
        }
    }

    // Report 1: All countries by population DESC
    public ArrayList<Country> getAllCountriesByPopulation()
    {
        try
        {
            Statement stmt = connection.createStatement();
            String sql = "SELECT country.Code, country.Name, country.Continent, country.Region, " +
                "country.Population, city.Name AS Capital FROM country " +
                "LEFT JOIN city ON country.Capital = city.ID ORDER BY country.Population DESC";
            ResultSet rset = stmt.executeQuery(sql);
            ArrayList<Country> list = new ArrayList<>();
            while (rset.next())
            {
                Country c = new Country();
                c.code = rset.getString("Code");
                c.name = rset.getString("Name");
                c.continent = rset.getString("Continent");
                c.region = rset.getString("Region");
                c.population = rset.getInt("Population");
                c.capital = rset.getString("Capital");
                list.add(c);
            }
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("Failed countries: " + e.getMessage());
            return null;
        }
    }

    public void displayCountries(ArrayList<Country> countries)
    {
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }
        System.out.println(String.format("%-5s %-40s %-15s %-25s %12s %-20s",
            "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : countries)
        {
            if (c == null) continue;
            System.out.println(String.format("%-5s %-40s %-15s %-25s %,12d %-20s",
                c.code, c.name, c.continent, c.region, c.population,
                c.capital != null ? c.capital : "N/A"));
        }
    }

    // Report 2: All cities by population DESC
    public ArrayList<City> getAllCitiesByPopulation()
    {
        try
        {
            Statement stmt = connection.createStatement();
            String sql = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code ORDER BY city.Population DESC";
            ResultSet rset = stmt.executeQuery(sql);
            ArrayList<City> list = new ArrayList<>();
            while (rset.next())
            {
                City c = new City();
                c.name = rset.getString("Name");
                c.country = rset.getString("Country");
                c.district = rset.getString("District");
                c.population = rset.getInt("Population");
                list.add(c);
            }
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("Failed cities: " + e.getMessage());
            return null;
        }
    }

    public void displayCities(ArrayList<City> cities)
    {
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }
        System.out.println(String.format("%-35s %-40s %-25s %12s",
            "Name", "Country", "District", "Population"));
        for (City c : cities)
        {
            if (c == null) continue;
            System.out.println(String.format("%-35s %-40s %-25s %,12d",
                c.name, c.country, c.district, c.population));
        }
    }

    // Report 3: All capital cities by population DESC
    public ArrayList<CapitalCity> getAllCapitalCitiesByPopulation()
    {
        try
        {
            Statement stmt = connection.createStatement();
            String sql = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM country JOIN city ON country.Capital = city.ID ORDER BY city.Population DESC";
            ResultSet rset = stmt.executeQuery(sql);
            ArrayList<CapitalCity> list = new ArrayList<>();
            while (rset.next())
            {
                CapitalCity c = new CapitalCity();
                c.name = rset.getString("Name");
                c.country = rset.getString("Country");
                c.population = rset.getInt("Population");
                list.add(c);
            }
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("Failed capitals: " + e.getMessage());
            return null;
        }
    }

    public void displayCapitalCities(ArrayList<CapitalCity> capitals)
    {
        if (capitals == null)
        {
            System.out.println("No capitals");
            return;
        }
        System.out.println(String.format("%-35s %-40s %12s", "Name", "Country", "Population"));
        for (CapitalCity c : capitals)
        {
            if (c == null) continue;
            System.out.println(String.format("%-35s %-40s %,12d",
                c.name, c.country, c.population));
        }
    }

    // Report 4: Top N cities
    public ArrayList<City> getTopNCities(int n)
    {
        try
        {
            Statement stmt = connection.createStatement();
            String sql = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC LIMIT " + n;
            ResultSet rset = stmt.executeQuery(sql);
            ArrayList<City> list = new ArrayList<>();
            while (rset.next())
            {
                City c = new City();
                c.name = rset.getString("Name");
                c.country = rset.getString("Country");
                c.district = rset.getString("District");
                c.population = rset.getInt("Population");
                list.add(c);
            }
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("Failed top N cities: " + e.getMessage());
            return null;
        }
    }

    // Report 5: Population living / not living in cities per country
    public ArrayList<PopulationReport> getPopulationByCountry()
    {
        try
        {
            Statement stmt = connection.createStatement();
            String sql = "SELECT country.Name, country.Population AS Total, " +
                "COALESCE(SUM(city.Population), 0) AS CityPop " +
                "FROM country LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Code, country.Name, country.Population " +
                "ORDER BY country.Population DESC";
            ResultSet rset = stmt.executeQuery(sql);
            ArrayList<PopulationReport> list = new ArrayList<>();
            while (rset.next())
            {
                PopulationReport p = new PopulationReport();
                p.name = rset.getString("Name");
                p.totalPopulation = rset.getLong("Total");
                p.cityPopulation = rset.getLong("CityPop");
                p.nonCityPopulation = p.totalPopulation - p.cityPopulation;
                if (p.totalPopulation > 0)
                {
                    p.cityPercentage = (p.cityPopulation * 100.0) / p.totalPopulation;
                    p.nonCityPercentage = (p.nonCityPopulation * 100.0) / p.totalPopulation;
                }
                list.add(p);
            }
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("Failed population report: " + e.getMessage());
            return null;
        }
    }

    public void displayPopulationReports(ArrayList<PopulationReport> reports)
    {
        if (reports == null)
        {
            System.out.println("No data");
            return;
        }
        System.out.println(String.format("%-40s %15s %15s %8s %15s %8s",
            "Name", "Total", "City Pop", "City %", "Non-City", "Non-City %"));
        for (PopulationReport p : reports)
        {
            if (p == null) continue;
            System.out.println(String.format("%-40s %,15d %,15d %7.1f%% %,15d %7.1f%%",
                p.name, p.totalPopulation, p.cityPopulation, p.cityPercentage,
                p.nonCityPopulation, p.nonCityPercentage));
        }
    }

    // Language report
    public ArrayList<LanguageReport> getLanguageSpeakers()
    {
        try
        {
            Statement stmt = connection.createStatement();
            ResultSet worldRs = stmt.executeQuery("SELECT SUM(Population) AS WorldPop FROM country");
            long worldPop = 0;
            if (worldRs.next()) worldPop = worldRs.getLong("WorldPop");

            String sql = "SELECT countrylanguage.Language, " +
                "SUM(country.Population * countrylanguage.Percentage / 100) AS Speakers " +
                "FROM countrylanguage JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE countrylanguage.Language IN ('Chinese', 'English', 'Spanish') " +
                "GROUP BY countrylanguage.Language ORDER BY Speakers DESC";
            ResultSet rset = stmt.executeQuery(sql);
            ArrayList<LanguageReport> list = new ArrayList<>();
            while (rset.next())
            {
                LanguageReport l = new LanguageReport();
                l.language = rset.getString("Language");
                l.speakers = Math.round(rset.getDouble("Speakers"));
                l.percentage = worldPop > 0 ? (l.speakers * 100.0) / worldPop : 0;
                list.add(l);
            }
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("Failed languages: " + e.getMessage());
            return null;
        }
    }

    public void displayLanguages(ArrayList<LanguageReport> languages)
    {
        if (languages == null)
        {
            System.out.println("No data");
            return;
        }
        System.out.println(String.format("%-15s %15s %10s", "Language", "Speakers", "% of World"));
        for (LanguageReport l : languages)
        {
            if (l == null) continue;
            System.out.println(String.format("%-15s %,15d %9.2f%%",
                l.language, l.speakers, l.percentage));
        }
    }

    // World population
    public long getWorldPopulation()
    {
        try
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(Population) FROM country");
            if (rs.next()) return rs.getLong(1);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}