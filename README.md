# BilMeet 🎓🤝

BilMeet is a desktop social synchronization hub designed to solve the constant "When are you free?" struggle for Bilkent University students. Unlike traditional social media, BilMeet focuses on turning dead hours between classes into opportunities for meaningful connection by matching academic schedules with personal interests.

This project was developed as part of CS102 – Algorithms and Programming II (Spring 2025/2026) by the Bug Slayers.

---

## Project Overview

The primary goal of BilMeet is to help Bilkent students coordinate social and productive activities during their free hours between classes. Since checking availability manually can be time-consuming, BilMeet automates this process by combining schedule matching with interest compatibility. The platform is exclusive to Bilkent students, encouraging a safer and more community-focused environment.

---

## System Features

### User Management
* Account creation is restricted to official university email addresses.
* Profiles include details such as name, department, and specific interest tags.
* Users can customize their presence with color-coded avatars.

### Schedule and Calendar
* The interactive calendar is the core of BilMeet's synchronization.
* Users can manually enter their class schedules and edit their availability at any time.
* The system provides a weekly calendar view with hourly availability blocks.

### Hangout Request System
* Users can create hangout requests by specifying the event name, location, time, and participant quota.
* Requests include activity tags to help interested students find specific events.
* The system manages quota checks and participant lists for every event.

### Messaging System
* Real-time communication is supported through private messaging between friends.
* Event-specific group chats are automatically available for all participants of a session.
* Full conversation history is maintained for both private and group interactions.

### Smart Matching and Search
* A specialized algorithm calculates match scores based on interest compatibility.
* The search tab allows users to discover others, with results sorted by how well their interests align.

---

## Tech Stack

* Language: Java
* GUI Framework: JavaFX (designed with Scene Builder)
* Database: Firebase (for real-time data and authentication)
* Build Tool: Maven
* Version Control: GitKraken and GitHub

---

## The Team (Bug Slayers)

Initial work was distributed as follows, though the final stage involved collective integration:

* Nehir Bakdur: Social and Friendship System (Search, Friend Requests).
* Asmin Deniz Kılıçoğlu: Event and Hangout System (Core Backend Algorithms).
* Onur Balcı: Messaging System (Private and Group Chats).
* İpek Terzioğlu: Authentication and Main Flow (Login, Navigation).
* Leena Nasser Al Hroub: Data Management, Profile, and Schedule Logic.

---

## Installation

1. Download and install Maven from the official website.
2. Add Maven to your system environment variables.
3. Clone the repository and open it in your IDE.
4. Under the Maven tab, navigate to plugins, select javafx, and click run.
