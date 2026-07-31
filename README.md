# SET08103 – World Population Reports (Resit)

[![Build Status](https://github.com/MohsinAlli/Sem/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/MohsinAlli/Sem/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

**Module:** SET08103 Software Engineering Methods  
**Assessment:** Resit Coursework – Individual Project  
**Student:** Mohsin Alli  

## Requirements Met

**0 requirements of 8 have been implemented, which is 0%.**

Update this section and the table below as you complete each report.

| ID | Name | Met | Screenshot |
|----|------|-----|------------|
| 1 | All the countries in the world organised by largest population to smallest. | No | |
| 2 | All the cities in the world organised by largest population to smallest. | No | |
| 3 | All the capital cities in the world organised by largest population to smallest. | No | |
| 4 | The top N populated cities in the world where N is provided by the user. | No | |
| 5 | The population of people, people living in cities, and people not living in cities in each country. | No | |
| 6 | Population of the world / continent / region / country / district / city. | No | |
| 7 | Number of people who speak Chinese, English, Spanish (greatest to smallest) including % of world population. | No | |
| 8 | (Additional / supporting reports as implemented) | No | |

> Replace the screenshots column with actual images once reports are working.  
> Incorrect claims in this table may be treated as academic misconduct.

## Project Setup

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker
- MySQL (world database): https://downloads.mysql.com/docs/world-db.zip

### Build
```bash
mvn clean package
```

### Run
```bash
java -jar target/sem-1.0-SNAPSHOT.jar
```

### Docker
```bash
docker build -t sem .
docker run --rm sem
```

## GitFlow Branches
- `master` – production-ready code
- `develop` – integration branch
- `release` – release preparation

## Zube / Project Management
Kanban board and product backlog are managed in Zube.io linked to this repository.

## AI Declaration
See the coursework declaration in the Moodle submission / this repository if generative AI tools were used.
