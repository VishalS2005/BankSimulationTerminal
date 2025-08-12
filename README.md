# 🏦 Bank Simulation (Java + JavaFX)

A desktop bank simulation with an interactive Java terminal and file‑driven data management. Create accounts, perform transactions, and manage customer records with sortable, printable views.

---

## 📌 Overview

This project simulates core retail banking flows with a focus on clarity and automation.

- Opening accounts with typed products and context‑specific fields
- Randomized account numbers for each customer
- Deposits, withdrawals, and closures by account number
- Account Manager that imports data and activities from text files and prints customer information in multiple formats
- Javadoc and JUnit test cases included

---

## 🚀 Features

### Account Opening

Provide:
- First name, last name, date of birth
- Account type:
  - Checking
  - College Checking
  - Savings
  - Money Market
  - Certificate of Deposit (CD)
- Initial deposit
- Branch location

Context‑specific options:
- College Checking → select campus
- Certificate of Deposit → select term and open date

---

### Deposit / Withdraw / Close

- System generates a randomized account number per customer
- With an account number, users can:
  - 💰 Deposit a specified amount
  - 💸 Withdraw a specified amount
  - ❌ Close the account

---

### Account Manager

Reads and displays data from plain-text sources:
- `accounts.txt` → customer and account info
- `activities.txt` → transactions and events

Print formats:
- 👤 By account holder
- 🏢 By branch
- 💳 By account type
- 📄 By account statements
- 📦 Archive (closed accounts)

---

## 📂 Project Paths

- Run the GUI:  
  `BankSimulationJavaFX/src/main/java/com/example/project3/Main.java`

- View Javadoc:  
  `BankSimulationJavaFX/Javadoc/index.html`

- Review test cases:  
  `Project3TestCases.pdf`

---

## 🛠️ How to Run

Built and tested using **IntelliJ IDEA** with **Java 17** and **JavaFX 17.0.12**.

### Prerequisites

- Java Development Kit (JDK) 17
- IntelliJ IDEA (Community or Ultimate)

---

### IntelliJ Setup

1. **Set Project SDK** to JDK 17  
   _File > Project Structure > Project > SDK_
   
2. Run the `RunProject2.java` class:
src.banking.RunProject2


## 📑 Data Files

Place these in the correct folder (usually root or resources):
- `accounts.txt` → initial account data
- `activities.txt` → transactions and closures

---

## 📚 Documentation

- View generated Javadoc:  
`BankSimulationJavaFX/Javadoc/index.html`

- Generate fresh Javadoc (CLI):
```bash
javadoc -d Javadoc -sourcepath src/main/java -subpackages com.example.project3
```

---

## Contributing
Vishal Saravanan, GitHub: VishalS2005
Yining Chen, GitHub: wekantakabotdis
Professor Lily Chang, Rutgers University Computer Science Department

---

## License
GNU GENERAL PUBLIC LICENSE
Version 3, 29 June 2007

---

## Contact
vishalsaran2021@gmail.com
chenyiningchris@gmail.com
