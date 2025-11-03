# Mahajana Sampatha Lottery Scraper

A **Java-based application** that scrapes Mahajana Sampatha lottery results from an HTML file and saves them into a MySQL database. Built using **Jsoup** for HTML parsing and **JDBC** for database interaction.

---

## Features

- Parses Mahajana Sampatha lottery results from a local HTML file (`mahajana.html`).
- Extracts:
  - Draw number
  - Draw date
  - Winning letter
  - Winning numbers (6 numbers)
- Inserts results into a MySQL database.
- Modular design with separate classes:
  - `Main.java` – Entry point
  - `LotteryScraper.java` – Handles HTML parsing
  - `DatabaseManager.java` – Handles database connections and inserts

---

## Prerequisites

- Java 8 or higher
- MySQL database
- Libraries:
  - [Jsoup 1.17.2](https://jsoup.org/)
  - [MySQL Connector/J 8.0.33](https://dev.mysql.com/downloads/connector/j/)

---

## Setup Instructions

1️⃣ **Configure Database Credentials**  
Create a `config.properties` file in the project root (this file is **not uploaded to GitHub**) with the following content:

```properties
DB_URL=jdbc:mysql://your-server-ip:3306/your_database
DB_USER=your_username
DB_PASSWORD=your_password
```

---

2️⃣ **Prepare Database Table**
```sql
CREATE TABLE mahajana_sampatha_results_thashmika (
    draw_no VARCHAR(20),
    draw_date DATE,
    winning_letter VARCHAR(5),
    winning_number_1 INT,
    winning_number_2 INT,
    winning_number_3 INT,
    winning_number_4 INT,
    winning_number_5 INT,
    winning_number_6 INT
);
```

## Project Structure
```
LotteryScraper/
├── src/
│   ├── Main.java
│   ├── LotteryScraper.java
│   └── DatabaseManager.java
├── lib/
│   ├── jsoup-1.17.2.jar
│   └── mysql-connector-java-8.0.33.jar
├── mahajana.html
├── config.properties
└── README.md
```

## Technologies Used
- Java
- Jsoup
- MySQL
- JDBC
